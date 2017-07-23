package at.doml.taskmanager.components

import org.scalajs.dom.document
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Message")
object Message {

    private lazy val messageText = document.getElementById("message-text")
    private lazy val messageElement = document.getElementById("message")

    @JSExport
    def showError(message: String): Unit = {
        messageText.textContent = message

        if (!messageElement.classList.contains("error-message")) {
            messageElement.classList.add("error-message")
        }

        messageElement.removeAttribute("hidden")
    }

    @JSExport
    def hide(): Unit = {
        messageElement.setAttribute("hidden", "hidden")
    }
}
