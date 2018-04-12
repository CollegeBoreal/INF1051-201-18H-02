
import java.sql.Date
import java.time.LocalDate

import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.api._
package object models {

  implicit val localDateToDate = MappedColumnType.base[LocalDate, Date](
    l => Date.valueOf(l),
    d => d.toLocalDate
  )

  val users = TableQuery[Users]
  val partners = TableQuery[Partners]
  val receivers = TableQuery[Receivers]
  val transferts = TableQuery[Transferts]




}
