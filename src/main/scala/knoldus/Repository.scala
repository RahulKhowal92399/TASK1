package knoldus

import slick.jdbc.H2Profile.api._
import slick.lifted.TableQuery

import scala.concurrent.Future

trait DAOComponent {

  //def insert(customer: Customer): Future[Int]

  def update(id: String, customer: Customer): Future[Int]

//  def delete(id: String): Future[Int]
//
//  def list: Future[Seq[Customer]]
//
//  def findById(id: String): Future[Option[Customer]]
//
//  def count: Future[Int]

}

class Repository(database: Database) extends DAOComponent {
  val customerTable = TableQuery[customerTable]

  database.run(customerTable.schema.create)

  private def filterQuery(id: String): Query[customerTable, Customer, Seq] =
    customerTable.filter(_.customerId === id)

//  override def insert(customer: Customer): Future[Int] = {
//    database.run(customerTable += customer)
//  }

  override def update(id: String, entity: Customer): Future[Int] =
    database.run(filterQuery(id).update(entity))

//  override def delete(id: String): Future[Int] =
//    database.run(filterQuery(id).delete)
//
//  override def list: Future[Seq[Customer]] = {
//    val query = for {
//      customer <- customerTable
//    } yield customer
//    database.run(query.result)
//  }
//
//  override def findById(id: String): Future[Option[Customer]] =
//    database.run(
//      customerTable
//        .filter(_.customerId === id)
//        .take(1)
//        .result
//        .headOption)
//
//  override def count: Future[Int] =
//    database.run(customerTable.length.result)
}

