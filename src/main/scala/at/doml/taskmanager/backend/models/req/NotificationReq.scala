package at.doml.taskmanager.backend.models.req

import at.doml.taskmanager.backend.models.NotificationType
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
final class NotificationReq(userId: Int,
                            target: Int,
                            text: String,
                            `type`: NotificationType.Value) extends js.Object
