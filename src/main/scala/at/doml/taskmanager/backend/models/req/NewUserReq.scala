package at.doml.taskmanager.backend.models.req

import at.doml.taskmanager.backend.models.UserRole
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
final class NewUserReq(username: String,
                       firstName: String,
                       lastName: String,
                       password: String,
                       role: UserRole.Value) extends js.Object
