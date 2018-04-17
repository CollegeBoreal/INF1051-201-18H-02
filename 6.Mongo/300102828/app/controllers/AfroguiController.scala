package controllers

import javax.inject._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

case class AfroguiData(name: String, age: Int)

// NOTE: Add the following to conf/routes to enable compilation of this class:
/*
GET     /afrogui        controllers.AfroguiController.afroguiGet
POST    /afrogui        controllers.AfroguiController.afroguiPost
*/

/**
 * Afrogui form controller for Play Scala
 */
class AfroguiController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val afroguiForm = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(AfroguiData.apply)(AfroguiData.unapply)
  )

  def afroguiGet() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.afrogui.form(afroguiForm))
  }

  def afroguiPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    afroguiForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.afrogui.form(formWithErrors))
      },
      afroguiData => {
        /* binding success, you get the actual value. */       
        /* flashing uses a short lived cookie */ 
        Redirect(routes.AfroguiController.afroguiGet()).flashing("success" -> ("Successful " + afroguiData.toString))
      }
    )
  }
}
