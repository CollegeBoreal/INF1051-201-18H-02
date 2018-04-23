package controllers

import javax.inject._

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import models._

import scala.concurrent.{ExecutionContext, Future}


// NOTE: Add the following to conf/routes to enable compilation of this class:
/*
GET     /receiver        controllers.ReceiverController.receiverGet
POST    /receiver        controllers.ReceiverController.receiverPost
*/

/**
  * User form controller for Play Scala
  */
class RecieverController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                                   mcc: MessagesControllerComponents
                                  )(implicit ec: ExecutionContext)
  extends MessagesAbstractController(mcc) with HasDatabaseConfigProvider[JdbcProfile] {

  val receiverForm = Form(
    mapping(
      "id" -> number,
      "lastName" -> text,
      "firstName" -> text,
      "tel" -> text,
    )(Reciever.apply)(Reciever.unapply)
  )

  def recieverSearch(name: String) = Action.async { implicit request =>
    val resultingRecievers: Future[Seq[Reciever]] = db.run(recievers.filter(_.lastName === name).result)
    resultingRecievers.map(x =>Ok(views.html.reciever.list(x)))
  }

  def receiverGet() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.reciever.form(recieverForm))
  }

  def receiverPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    receiverForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.reciever.form(formWithErrors))
      },
      recieverData => {
        /* binding success, you get the actual value. */
        /* flashing uses a short lived cookie */
        val recieverId = (recievers returning recievers.map(_.id)) += recieverData
        db.run(recieverId)
        Redirect(routes.ReceiverController.recieverGet()).flashing("success" -> ("Successful " + recieverData.toString))
      }
    )
  }
}

