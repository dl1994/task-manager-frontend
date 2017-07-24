package at.doml.taskmanager.components

import org.scalajs.dom
import org.scalajs.dom.document

final class Table(element: dom.raw.Element) extends Element(element) {

    def this(id: String) = this(document.getElementById(id))

    def isEmpty(): Boolean = element.getElementsByTagName("tr").length == 0
}
