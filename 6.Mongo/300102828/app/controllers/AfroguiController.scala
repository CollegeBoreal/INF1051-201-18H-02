package controllers

import javax.inject._

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.{JsArray, JsObject, Json}
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class AfroguiData(name: String, age: Int)

// NOTE: Add the following to conf/routes to enable compilation of this class:
/*
GET     /afrogui        controllers.AfroguiController.afroguiGet
POST    /afrogui        controllers.AfroguiController.afroguiPost
*/

/**
 * Afrogui form controller for Play Scala
 */
class AfroguiController @Inject()
(mcc: MessagesControllerComponents,
val reactiveMongoApi: ReactiveMongoApi
)
  extends AbstractController(mcc)
   with MongoController
    with ReactiveMongoComponents
{

  val afroguiForm = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(AfroguiData.apply)(AfroguiData.unapply)
  )

  def collection: Future[JSONCollection] =
    database.map(_.collection[JSONCollection]("persons"))
 def index = Action { Ok("works")
   def findByName(name: String) = Action.async {
     // let's do our query
     val cursor: Future[Cursor[JsObject]] = collection.map {
       // find all people with name `name`
       _.find(Json.obj("name" -> name)).
         // sort them by creation date
         sort(Json.obj("created" -> -1)).
         // perform the query and get a cursor of JsObject
         cursor[JsObject](ReadPreference.primary)
     }

     // gather all the JsObjects in a list
     val futurePersonsList: Future[List[JsObject]] =
       cursor.flatMap(_.collect[List]())

     // transform the list into a JsArray
     val futurePersonsJsonArray: Future[JsArray] =
       futurePersonsList.map { persons => Json.arr(persons) }

     // everything's ok! Let's reply with the array
     futurePersonsJsonArray.map { persons =>
       Ok(persons)
     }
   }
 }

}

