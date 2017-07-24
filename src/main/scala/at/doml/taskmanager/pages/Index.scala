package at.doml.taskmanager.pages

import at.doml.taskmanager.Config
import at.doml.taskmanager.backend.controllers.{App, Users}
import at.doml.taskmanager.components.{Form, Message, Page}
import org.scalajs.dom.window
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Index")
object Index extends Page {

    private lazy val loginForm = new Form("login-form", List("username", "password"))

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
        App.login(loginForm).onComplete { (_, status) =>
            if (status == 200) {
                window.location.href = Config.ROUTES("projects")
            } else {
                loginForm.errorHighlight()
                loginForm.reset()

                Message.showError("Wrong username or password!")
            }
        }
    }
}
