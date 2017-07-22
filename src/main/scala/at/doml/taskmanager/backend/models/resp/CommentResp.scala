package at.doml.taskmanager.backend.models.resp

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@JSGlobal
@js.native
final class CommentResp extends js.Object {

    val id: Int = js.native
    val posterId: Int = js.native
    val text: String = js.native
    val postTimestamp: Long = js.native
    val lastEditTimestamp: Long = js.native
}
