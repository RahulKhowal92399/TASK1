package knoldus

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import slick.jdbc.H2Profile.api._

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn

object Api extends App{
  implicit val actorSystem: ActorSystem = ActorSystem("Actorsystem")
  implicit val Materializer: ActorMaterializer =
    ActorMaterializer()(actorSystem)
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher
  val database =  Database.forConfig("database")
  val customerRepository = new Repository(database)
  val customerRoutes = new Routes(customerRepository)
  val routes: Route = customerRoutes.routes

  val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(routes, "localhost", 8000)
  println(s"Server online at http://localhost:8000/\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => actorSystem.terminate())
}
