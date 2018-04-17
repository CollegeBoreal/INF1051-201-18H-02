package controllers

import javax.inject._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

case class PartnerData(name: String, age: Int)

// NOTE: Add the following to conf/routes to enable compilation of this class:
/*
GET     /partner        controllers.PartnerController.partnerGet
POST    /partner        controllers.PartnerController.partnerPost
*/

/**
 * Partner form controller for Play Scala
 */
class PartnerController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val partnerForm = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(PartnerData.apply)(PartnerData.unapply)
  )

  def partnerGet() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.partner.form(partnerForm))
  }

  def partnerPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    partnerForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.partner.form(formWithErrors))
      },
      partnerData => {
        /* binding success, you get the actual value. */       
        /* flashing uses a short lived cookie */ 
        Redirect(routes.PartnerController.partnerGet()).flashing("success" -> ("Successful " + partnerData.toString))
      }
    )
  }
}
