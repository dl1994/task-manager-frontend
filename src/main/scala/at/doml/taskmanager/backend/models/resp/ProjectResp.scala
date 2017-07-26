package at.doml.taskmanager.backend.models.resp

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@JSGlobal
@js.native
final class ProjectResp extends js.Object {

    val id: Int = js.native
    val ownerId: Int = js.native
    val name: String = js.native
    val description: String = js.native
    val status: String = js.native
}
