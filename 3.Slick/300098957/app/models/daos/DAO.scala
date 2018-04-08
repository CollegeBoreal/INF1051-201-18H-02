package models.daos

import org.slf4j.LoggerFactory
import scala.concurrent.Future

/**
  * Trait DAO defining methods for all dao's
  */
trait DAO[DTO, Identifier] {

  lazy val logger = LoggerFactory.getLogger(getClass.getName)

  def find(id: Identifier): Future[Option[DTO]]

  def upsert(t: DTO): Future[Option[DTO]]

  def delete(id: Identifier): Future[Int]

}

