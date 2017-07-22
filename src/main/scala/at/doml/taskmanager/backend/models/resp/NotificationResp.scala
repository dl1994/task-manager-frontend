package at.doml.taskmanager.backend.models.resp

import at.doml.taskmanager.backend.models.{NotificationStatus, NotificationType}
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@JSGlobal
@js.native
final class NotificationResp extends js.Object {

    val id: Int = js.native
    val userId: Int = js.native
    val text: String = js.native
    val timestamp: Long = js.native
    val status: NotificationStatus.Value = js.native
    val `type`: NotificationType.Value = js.native
    val target: Int = js.native
}
