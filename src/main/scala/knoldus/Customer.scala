package knoldus

import slick.jdbc.H2Profile.api._
import slick.lifted.{PrimaryKey, ProvenShape, Rep, Tag}

case class Customer(customerId: String, customerName: String, customerEmail: String)

class customerTable(tag: Tag) extends Table[Customer](tag, "Customer") {
  def customerId: Rep[String] = column[String]("CUSTOMER-ID")

  def customerName: Rep[String] = column[String]("CUSTOMER-NAME")

  def customerEmail: Rep[String] = column[String]("CUSTOMER-EMAIL")

  def primaryKey: PrimaryKey = primaryKey("Rahul", customerId)

  override def * : ProvenShape[Customer] = (customerId, customerName, customerEmail) <> (Customer.tupled, Customer.unapply)
}

