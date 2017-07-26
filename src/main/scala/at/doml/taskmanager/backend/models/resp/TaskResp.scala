package at.doml.taskmanager.backend.models.resp

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@JSGlobal
@js.native
final class TaskResp extends js.Object {

    val id: Int = js.native
    val priority: Int = js.native
    val subject: String = js.native
    val assigneeId: Number = js.native
    val createdTimestamp: Number = js.native
    val startedTimestamp: Number = js.native
    val dueTimestamp: Number = js.native
    val finishedTimestamp: Number = js.native
    val status: String = js.native
}
