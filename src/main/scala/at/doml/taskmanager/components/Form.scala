package at.doml.taskmanager.components

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.raw.HTMLFormElement
import scala.scalajs.js.URIUtils

final class Form(id: String, fieldNames: List[String]) {

    private val form = document.getElementById(id).asInstanceOf[HTMLFormElement]
    private val fields: Map[String, FormField] = fieldNames.map(
        name => (name, new FormField(form(name).asInstanceOf[HTMLFormElement]))
    ).toMap
    private val inputs: Seq[Element] = {
        val elements = form.getElementsByTagName("input")

        for (index <- 0 until elements.length) yield {
            new Element(elements(index).asInstanceOf[dom.raw.Element])
        }
    }

    def apply(fieldName: String): String = fields(fieldName).value()

    def update(fieldName: String, value: String): Unit = fields(fieldName).value(value)

    def reset(): Unit = fields.values.foreach(_.reset())

    def serialize(): String = fields.map(f => f._1 + "=" + URIUtils.encodeURI(f._2.value())).mkString("&")

    def errorHighlight(): Unit = fields.values.foreach(_.addClass("form-error"))

    def enable(): Unit = inputs.foreach(_.enable())

    def disable(): Unit = inputs.foreach(_.disable())
}
