package knoldus

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import play.api.libs.json.{Json, OFormat}

class Routes(repository: Repository) extends PlayJsonSupport {
  implicit val customerFormat: OFormat[Customer] = Json.format[Customer]

  val routes: Route = pathPrefix("customer") {
    path("add-customer") {
      (post & entity(as[Customer])) { customer =>
        onSuccess(repository.insert(customer)) {
          result => complete(result)
        }

//      path("list-all") {
//        get {
//          onSuccess(repository.list) { result =>
//            complete(result)
//          }
        }
      }
      path(Segment) { customerId =>
        (put & entity(as[Customer])) { customer =>
          onSuccess(repository.update(customerId, customer)) {
            result => complete(result)
          }
//        }
//          get {
//            onSuccess(repository.findById(customerId)) {
//              case None => complete("No record exists")
//              case Some(result) => complete(result)
//            }
          }
//          delete {
//            onSuccess(repository.delete(customerId)) {
//              result => complete(result)
//            }
//          }
      }
  }
}
