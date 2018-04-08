package models

import controllers.PartnerData
import slick.jdbc.MySQLProfile.api._

// Definition of the PARTNERS table
class Partners(tag: Tag) extends Table[PartnerData](tag, "Partner") {
  def id = column[Int]("idPartner", O.PrimaryKey) // This is the primary key column
  def name = column[String]("Parter_Name")
  def location = column[String]("Location")
  def city = column[String]("City")
  def tel = column[String]("Tel")
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, name, location, city, tel) <> (PartnerData.tupled, PartnerData.unapply)
}
