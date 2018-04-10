package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class  Partners(id: Int
                 ,Name: String
                 ,Location: String
                 ,City: String
                 ,tel: String
                )


// Definition of the SUPPLIERS table
class Partners(tag: Tag) extends Table[Partners](tag, "Partner") {
  def id = column[Int]("id", O.PrimaryKey) // This is the primary key column
  def lastName = column[String]("Name")
  def firstName = column[String]("Location")
  def email = column[String]("City")
  def tel = column[String]("Tel")


  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, Name, Location,tel, ) <> (Partner.tupled, Partner.unapply)
}

