package at.doml.taskmanager

import at.doml.taskmanager.backend.models.req.CommentReq
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("test2")
object Test {

    @JSExport
    def hello(): Unit = {
        println(Config.BACKEND_PATH)
        println(Config.MAX_PRIORITY)
        println(Config.ROUTES("index"))
        println(new CommentReq("str"))
    }
}
