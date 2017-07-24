package at.doml.taskmanager.pages

import at.doml.taskmanager.backend.controllers.Users
import at.doml.taskmanager.backend.models.req.UserReq
import at.doml.taskmanager.components.{Element, Form, Navbar, Page}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Settings")
object Settings extends Page with Navbar {

    override val pageButtonId: String = "nav-settings"
    private lazy val personalInfoForm = new Form("edit-personal-info-form", List("firstName", "lastName"))
    private lazy val editButton = new Element("pi-edit")
    private lazy val cancelEditButton = new Element("pi-edit-cancel")

    override def onNavbarInitSuccess(): Unit = fillPersonalInfoForm()

    private def fillPersonalInfoForm(): Unit = {
        personalInfoForm("firstName") = Navbar.me().firstName
        personalInfoForm("lastName") = Navbar.me().lastName
    }

    @JSExport
    def editPersonalInformation(): Unit = {
        editButton.noDisplay()
        cancelEditButton.display()
        personalInfoForm.enable()
    }

    @JSExport
    def cancelPersonalInformationEdit(): Unit = {
        personalInfoForm.disable()
        this.fillPersonalInfoForm()
        cancelEditButton.noDisplay()
        editButton.display()
    }

    @JSExport
    def savePersonalInformation(): Unit = {
        personalInfoForm.disable()

        val req = new UserReq( // TODO
            firstName = personalInfoForm("firstName"),
            lastName = personalInfoForm("lastName")
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
}
