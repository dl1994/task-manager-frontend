package at.doml.taskmanager.backend.models.resp

import at.doml.taskmanager.backend.models.TaskStatus
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@JSGlobal
@js.native
final class TaskResp extends js.Object {

    val id: Int = js.native
    val priority: Int = js.native
    val subject: String = js.native
    val assigneeId: Int = js.native
    val createdTimestamp: Long = js.native
    val startedTimestamp: Long = js.native
    val dueTimestamp: Long = js.native
    val finishedTimestamp: Long = js.native
    val status: TaskStatus.Value = js.native
}
