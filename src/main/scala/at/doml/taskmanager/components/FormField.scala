package at.doml.taskmanager.components

import org.scalajs.dom.raw.HTMLFormElement

final class FormField(element: HTMLFormElement) extends Element(element) {

    def value(): String = element("value").asInstanceOf[String]

    def value(value: String): Unit = element("value") = value

    def reset(): Unit = this.value("")
}
