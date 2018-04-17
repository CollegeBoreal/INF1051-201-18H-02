package controllers

import javax.inject._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

case class TransfertData(name: String, age: Int)

// NOTE: Add the following to conf/routes to enable compilation of this class:
/*
GET     /transfert        controllers.TransfertController.transfertGet
POST    /transfert        controllers.TransfertController.transfertPost
*/

/**
 * Transfert form controller for Play Scala
 */
class TransfertController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val transfertForm = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(TransfertData.apply)(TransfertData.unapply)
  )

  def transfertGet() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.transfert.form(transfertForm))
  }

  def transfertPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    transfertForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.transfert.form(formWithErrors))
      },
      transfertData => {
        /* binding success, you get the actual value. */       
        /* flashing uses a short lived cookie */ 
        Redirect(routes.TransfertController.transfertGet()).flashing("success" -> ("Successful " + transfertData.toString))
      }
    )
  }
}
