package at.doml.taskmanager.backend

import at.doml.taskmanager.components.Message
import org.scalajs.dom.ext.AjaxException
import scala.concurrent.{ExecutionContext, Future}

class Response[R](future: Future[(R, Int)])(implicit context: ExecutionContext) {

    def onComplete(action: (R, Int) => Unit): Unit = {
        this.future.onComplete { r =>
            if (r.isSuccess) {
                action.tupled(r.get)
            } else {
                val exception = r.failed.get.asInstanceOf[AjaxException]

                if (!exception.isTimeout) {
                    action(null.asInstanceOf[R], exception.xhr.status)
                } else {
                    Message.showError("Unable to connect to server.")
                }
            }
        }
    }
}
