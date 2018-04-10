
import slick.lifted.TableQuery

package object models {
  val users = TableQuery[Users]
  val partners = TableQuery[Partners]
  val receivers = TableQuery[Receivers]



}
