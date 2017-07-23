package at.doml.taskmanager.pages

import at.doml.taskmanager.Config
import at.doml.taskmanager.backend.controllers.{App, Users}
import at.doml.taskmanager.components.{Message, Page}
import org.scalajs.dom.raw.{Element, HTMLFormElement}
import org.scalajs.dom.{document, window}
import scala.scalajs.js.URIUtils
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Index")
object Index extends Page {

    override def init(): Unit = {
        window.onload = _ => {
            Users.me().onComplete { (_, status) =>
                if (status == 200) {
                    window.location.href = Config.ROUTES("projects")
                }
            }
        }
    }

    @JSExport
    def login(): Unit = {
        val form = document.getElementById("login-form").asInstanceOf[HTMLFormElement]
        val username = this.getUrlEncodedFormField(form, "username")
        val password = this.getUrlEncodedFormField(form, "password")
        val data = s"username=$username&password=$password"

        App.login(data).onComplete { (_, status) =>
            if (status == 200) {
                window.location.href = Config.ROUTES("projects")
            } else {
                val fields = form.getElementsByClassName("form-field")

                for (index <- 0 until fields.length) {
                    val field: Element = fields(index).asInstanceOf[Element]

                    if (!field.classList.contains("form-error")) {
                        field.classList.add("form-error")
                    }
                }

                this.resetFormField(form, "username")
                this.resetFormField(form, "password")

                Message.showError("Wrong username or password!")
            }
        }
    }

    private def getUrlEncodedFormField(form: HTMLFormElement, field: String): String = {
        URIUtils.encodeURI(form(field).asInstanceOf[HTMLFormElement]("value").asInstanceOf[String])
    }

    private def resetFormField(form: HTMLFormElement, field: String): Unit = {
        form(field).asInstanceOf[HTMLFormElement]("value") = ""
    }
}
