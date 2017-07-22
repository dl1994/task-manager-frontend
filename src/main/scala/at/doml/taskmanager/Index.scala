package at.doml.taskmanager

import at.doml.taskmanager.backend.controllers.{App, Users}
import org.scalajs.dom.raw.{Element, HTMLFormElement}
import org.scalajs.dom.{document, window}
import scala.scalajs.js.URIUtils
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Index")
object Index {

    @JSExport
    def init(): Unit = {
        Users.me().onComplete { (_, status) =>
            if (status == 200) {
                window.location.href = Config.ROUTES("projects")
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
            status match {
                case 200 => window.location.href = Config.ROUTES("projects")
                case _ => {
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
    }

    private def getUrlEncodedFormField(form: HTMLFormElement, field: String): String = {
        URIUtils.encodeURI(form(field).asInstanceOf[HTMLFormElement]("value").asInstanceOf[String])
    }

    private def resetFormField(form: HTMLFormElement, field: String): Unit = {
        form(field).asInstanceOf[HTMLFormElement]("value") = ""
    }
}
