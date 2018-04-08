package services

import javax.inject.Inject

import controllers.PartnerData
import models._
import daos._

import scala.concurrent.{ExecutionContext, Future}

class PartnerService @Inject()
        (partnerDAO: PartnerDAO)(implicit ec: ExecutionContext) {
  def find(id: Int): Future[PartnerData] = partnerDAO.find(id).map{
    case None => sys.error("No such data")
    case Some(x) => x
  }

  def upsert(partnerData: PartnerData): Future[PartnerData] = partnerDAO.upsert(partnerData).map {
    case None => sys.error("No such data")
    case Some(x) => x
  }
}

