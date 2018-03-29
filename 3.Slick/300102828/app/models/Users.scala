package models

import com.sun.jndi.cosnaming.IiopUrl.Address
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
case class  User(id: Int
                ,lastName: String
                ,firstName: String
                ,email: String
                ,password: String
                ,address: String
                ,tel: String
                )


// Definition of the SUPPLIERS table
class Users(tag: Tag) extends Table[User](tag, "User") {
  def id = column[Int]("idUser", O.PrimaryKey) // This is the primary key column
  def lastName = column[String]("LastName")
  def firstName = column[String]("FirstName")
  def email = column[String]("E-mail")
  def password  = column[String]("Password")
  def address = column[String]("Address")
  def tel = column[String]("Tel")
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, lastName, firstName, email, password, address,tel) <> (User.tupled, User.unapply)
}


