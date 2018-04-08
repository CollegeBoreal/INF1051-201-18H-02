package models.daos

import javax.inject.Inject

import controllers.PartnerData
import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._


/**
  *
  *
  */
class PartnerDAO @Inject()
  (protected val dbConfigProvider: DatabaseConfigProvider)
  (implicit ec: ExecutionContext)
  extends DAO[PartnerData, Int] with HasDatabaseConfigProvider[JdbcProfile] {

  /**
    * Default find
    * @param id
    * @return
    */
  def find(id: Int): Future[Option[PartnerData]] = db.run ( partners.filter(_.id === id).result ).map(_.headOption)

  /**
    * Upsert a new partner
    * @param partner
    * @return
    */
  def upsert(partner: PartnerData): Future[Option[PartnerData]] = db.run ( (partners returning partners).insertOrUpdate(partner) )

  /**
    * Remove association when the partner is removed
    * @param id
    * @return
    */
  def delete(id: Int): Future[Int] = db.run( partners.filter(_.id === id).delete )


}

