package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class  Receiver(id: Int
                 ,lastName: String
                 ,firstName: String
                 ,tel: String

                )


// Definition of the SUPPLIERS table
class Reveivers(tag: Tag) extends Table[User](tag, "User") {
  def id = column[Int]("id", O.PrimaryKey) // This is the primary key column
  def lastName = column[String]("LastName")
  def firstName = column[String]("FirstName")
  def tel = column[String]("Tel")


  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, lastName, firstName,tel, ) <> (User.tupled, User.unapply)
}
