package at.doml.taskmanager.components

import org.scalajs.dom
import org.scalajs.dom.document
import scala.scalajs.js

abstract class Table[E <: js.Any](element: dom.raw.Element) extends Element(element) {

    def this(id: String) = this(document.getElementById(id))

    val header: String

    protected def fillRow(item: E): String

    def fillTable(items: js.Array[E]): Unit = {
        element.innerHTML = "<tr>" + header + "</tr>"

        for (index <- 0 until items.length) {
            val rowClass = if (index % 2 == 0) {
                "even-row"
            } else {
                "odd-row"
            }

            element.innerHTML += s"""<tr class="$rowClass">""" + this.fillRow(items(index)) + "</tr>"
        }
    }

    def isEmpty(): Boolean = element.getElementsByTagName("tr").length == 0
}
