package at.doml.taskmanager.components

import org.scalajs.dom.document
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Modal")
object Modal {

    private lazy val rawModal = document.getElementById("modal")
    private lazy val modal = new Element(rawModal)

    @JSExport
    def init(): Unit = {
        document.addEventListener("DOMContentLoaded", useCapture = false, listener = (_: js.Any) => {
            rawModal.addEventListener("click", useCapture = false, listener = (_: js.Any) => this.hide())

            val children = rawModal.children

            for (index <- 0 until children.length) {
                children(index).addEventListener("click", useCapture = false, listener = (_: js.Any) => {})
            }
        })
    }

    @JSExport
    def show(): Unit = {
        modal.show()
    }

    @JSExport
    def hide(): Unit = {
        modal.hide()
    }
}
