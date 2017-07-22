package at.doml.taskmanager.backend.models.resp

import at.doml.taskmanager.backend.models.ProjectParticipantRole
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@JSGlobal
@js.native
final class ProjectParticipantResp extends js.Object {

    val projectId: Int = js.native
    val participantId: Int = js.native
    val role: ProjectParticipantRole.Value = js.native
}
