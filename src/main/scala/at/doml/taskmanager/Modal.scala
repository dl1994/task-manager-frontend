package at.doml.taskmanager

import org.scalajs.dom.document
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Modal")
object Modal {

    private lazy val modal = document.getElementById("modal")

    @JSExport
    def initModal(): Unit = {
        document.addEventListener("DOMContentLoaded", useCapture = false, listener = (_: js.Any) => {
            modal.addEventListener("click", useCapture = false, listener = (_: js.Any) => this.hide())

            val children = modal.children

            for (index <- 0 until children.length) {
                children(index).addEventListener("click", useCapture = false, listener = (_: js.Any) => {})
            }
        })
    }

    @JSExport
    def show(): Unit = {
        modal.removeAttribute("hidden")
    }

    @JSExport
    def hide(): Unit = {
        modal.setAttribute("hidden", "hidden")
    }
}
