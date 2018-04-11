package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class  Transfert(id: Int
                    ,date_transfert: String
                    ,amount: String
                     )


// Definition of the SUPPLIERS table
class Transferts(tag: Tag) extends Table[Transfert](tag, "Transfert") {
  def id = column[Int]("idTransfert", O.PrimaryKey,O.AutoInc) // This is the primary key column
  def date_transfert = column[String]("Date_Transfert")
  def amount = column[String]("Amount")



  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id,date_transfert,amount) <> (Transfert.tupled, Transfert.unapply)
}

