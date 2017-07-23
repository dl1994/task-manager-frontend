package at.doml.taskmanager.pages

import at.doml.taskmanager.backend.controllers.Users
import at.doml.taskmanager.backend.models.req.UserReq
import at.doml.taskmanager.components.{Navbar, Page}
import org.scalajs.dom.document
import org.scalajs.dom.raw.{Element, HTMLFormElement}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Settings")
object Settings extends Page with Navbar {

    override val pageButtonId: String = "nav-settings"

    override def onNavbarInitSuccess(): Unit = fillPersonalInfoForm()

    private def fillPersonalInfoForm(): Unit = {
        val form = document.getElementById("edit-personal-info-form").asInstanceOf[HTMLFormElement]

        this.setFormField(form, "firstName")(Navbar.me().firstName)
        this.setFormField(form, "lastName")(Navbar.me().lastName)
    }

    private def setFormField(form: HTMLFormElement, field: String)(value: String): Unit = {
        form(field).asInstanceOf[HTMLFormElement]("value") = value
    }

    @JSExport
    def editPersonalInformation(): Unit = {
        document.getElementById("pi-edit").setAttribute("hidden", "hidden")
        document.getElementById("pi-edit-cancel").removeAttribute("hidden")

        val inputs = document.getElementById("edit-personal-info-form").getElementsByTagName("input")

        for (index <- 0 until inputs.length) {
            inputs(index).asInstanceOf[Element].removeAttribute("disabled")
        }
    }

    @JSExport
    def cancelPersonalInformationEdit(): Unit = {
        val inputs = document.getElementById("edit-personal-info-form").getElementsByTagName("input")

        for (index <- 0 until inputs.length) {
            inputs(index).asInstanceOf[Element].setAttribute("disabled", "disabled")
        }

        this.fillPersonalInfoForm()

        document.getElementById("pi-edit-cancel").setAttribute("hidden", "hidden")
        document.getElementById("pi-edit").removeAttribute("hidden")
    }

    @JSExport
    def savePersonalInformation(): Unit = {
        val form = document.getElementById("edit-personal-info-form").asInstanceOf[HTMLFormElement]
        val inputs = form.getElementsByTagName("input")

        for (index <- 0 until inputs.length) {
            inputs(index).asInstanceOf[Element].setAttribute("disabled", "disabled")
        }

        val req = new UserReq(
            firstName = getFormField(form, "firstName"),
            lastName = getFormField(form, "lastName")
        )

        Users.changeInfo(req).onComplete { (user, status) =>
            if (status == 200) {
                Navbar.setMe(user)
            } else {
                // TODO show error message
            }

            this.cancelPersonalInformationEdit()
        }
    }

    private def getFormField(form: HTMLFormElement, field: String): String = {
        form(field).asInstanceOf[HTMLFormElement]("value").asInstanceOf[String]
    }
}
