package controllers

import javax.inject._

import akka.actor.TypedActor.Receiver
import com.sun.istack.internal.tools.DefaultAuthenticator.Receiver
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import models._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}


// NOTE: Add the following to conf/routes to enable compilation of this class:
/*
GET     /receiver        controllers.ReceiverController.receiverGet
POST    /receiver        controllers.ReceiverController.receiverPost
*/

/**
  * User form controller for Play Scala
  */
class receiverController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                               mcc: MessagesControllerComponents
                              )(implicit ec: ExecutionContext)
  extends MessagesAbstractController(mcc) with HasDatabaseConfigProvider[JdbcProfile] {

  val receiverForm = Form(
    mapping(
      "id" -> number,
      "lastName" -> text,
      "firstName" -> text,
      "tel" -> text,
    )(Receiver.apply)(Receiver.unapply)
  )

  def receiverSearch(name: String) = Action.async { implicit request =>
    val resultingUsers: Future[Seq[Receiver]] = db.run(receivers.filter(_.lastName === name).result)
    resultingUsers.map(users => Ok(views.html.receiver.list(receivers)))
  }

  def receiverGet() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.receiver.form(receiverForm))
  }

  def receiverPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    receiverForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.receiver.form(formWithErrors))
      },
      receiverData => {
        /* binding success, you get the actual value. */       
        /* flashing uses a short lived cookie */ 
        Redirect(routes.ReceiverController.receiverGet()).flashing("success" -> ("Successful " + receiverData.toString))
      }
    )
  }
}
