package at.doml.taskmanager.components

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Message")
object Message {

    private lazy val messageText = new Element("message-text")
    private lazy val messageElement = new Element("message")

    @JSExport
    def showError(message: String): Unit = {
        messageText.text(message)
        messageElement.addClass("error-message")
        messageElement.show()
    }

    @JSExport
    def hide(): Unit = {
        messageElement.hide()
    }
}
