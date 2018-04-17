package controllers

import javax.inject._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

case class RecieverData(name: String, age: Int)

// NOTE: Add the following to conf/routes to enable compilation of this class:
/*
GET     /reciever        controllers.RecieverController.recieverGet
POST    /reciever        controllers.RecieverController.recieverPost
*/

/**
 * Reciever form controller for Play Scala
 */
class RecieverController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val recieverForm = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(RecieverData.apply)(RecieverData.unapply)
  )

  def recieverGet() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.reciever.form(recieverForm))
  }

  def recieverPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    recieverForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.reciever.form(formWithErrors))
      },
      recieverData => {
        /* binding success, you get the actual value. */       
        /* flashing uses a short lived cookie */ 
        Redirect(routes.RecieverController.recieverGet()).flashing("success" -> ("Successful " + recieverData.toString))
      }
    )
  }
}
