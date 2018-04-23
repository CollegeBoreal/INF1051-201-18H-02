package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class  Reciever(id: Int
                     ,lastName: String
                     ,firstName: String
                     ,tel: String
                    )


// Definition of the SUPPLIERS table
class Recievers(tag: Tag) extends Table[Reciever](tag, "Receiver") {
  def id = column[Int]("idReceiver", O.PrimaryKey,O.AutoInc) // This is the primary key column
  def lastName = column[String]("LastName")
  def firstName = column[String]("FirstName")
  def tel = column[String]("Tel")


  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, lastName, firstName,tel) <> (Reciever.tupled, Reciever.unapply)
}
