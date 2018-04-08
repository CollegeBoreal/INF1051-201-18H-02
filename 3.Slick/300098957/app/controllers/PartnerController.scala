package controllers

import javax.inject._

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import services.PartnerService

import scala.concurrent.{ExecutionContext, Future}

case class PartnerData(id: Int
                   , name: String
                   , location: String
                   , city: String
                   , tel: String
                  )

// NOTE: Add the following to conf/routes to enable compilation of this class:
/*
GET     /partner        controllers.PartnerController.partnerGet
POST    /partner        controllers.PartnerController.partnerPost
*/

/**
 * Partner form controller for Play Scala
 */
class PartnerController @Inject()(
                                   mcc: MessagesControllerComponents
                                 , partnerService: PartnerService
                                 )
                                 (implicit ec: ExecutionContext) extends MessagesAbstractController(mcc) {

  val partnerForm = Form(
    mapping(
      "id" -> number,
      "name" -> text,
      "location" -> text,
      "city" -> text,
      "tel" -> text
    )(PartnerData.apply)(PartnerData.unapply)
  )

  def partnerSearch(id: Int) = Action.async { implicit request =>
    val resultingPartners: Future[PartnerData] = partnerService.find(id)
    resultingPartners.map { partner =>
      Ok(views.html.partner.list(partner))
    }
  }

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
        partnerService.upsert(partnerData = partnerData) // TODO check I am not working
        /* flashing uses a short lived cookie */ 
        Redirect(routes.PartnerController.partnerGet()).flashing("success" -> ("Successful " + partnerData.toString))
      }
    )
  }
}
