package at.doml.taskmanager.components

import org.scalajs.dom
import org.scalajs.dom.document

class Element(element: dom.raw.Element) {

    def this(id: String) = this(document.getElementById(id))

    def text(text: String): Unit = element.textContent = text

    def setAttribute(name: String): Unit = {
        if (!element.hasAttribute(name)) {
            element.setAttribute(name, name)
        }
    }

    def addClass(`class`: String): Unit = {
        if (!element.classList.contains(`class`)) {
            element.classList.add(`class`)
        }
    }

    def removeClass(`class`: String): Unit = {
        element.classList.remove(`class`)
    }

    def enable(): Unit = {
        element.removeAttribute("disabled")
    }

    def disable(): Unit = this.setAttribute("disabled")

    def noDisplay(): Unit = {
        // TODO improve
        element.setAttribute("style", "display: none;")
    }

    def display(): Unit = {
        // TODO improve
        element.removeAttribute("style")
    }

    def show(): Unit = {
        element.removeAttribute("hidden")
    }

    def hide(): Unit = this.setAttribute("hidden")

    def toggle(): Unit = {
        if (element.hasAttribute("hidden")) {
            this.show()
        } else {
            this.hide()
        }
    }
}
