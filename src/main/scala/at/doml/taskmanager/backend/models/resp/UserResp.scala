package at.doml.taskmanager.backend.models.resp

import at.doml.taskmanager.backend.models.UserRole
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@JSGlobal
@js.native
class UserResp extends js.Object {

    val id: Int = js.native
    val username: String = js.native
    val firstName: String = js.native
    val lastName: String = js.native
    val role: UserRole.Value = js.native
}
