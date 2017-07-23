package at.doml.taskmanager.components

import at.doml.taskmanager.Config
import at.doml.taskmanager.backend.controllers.{App, Notifications, Users}
import at.doml.taskmanager.backend.models.UserRole
import at.doml.taskmanager.backend.models.resp.Aliases.User
import org.scalajs.dom.{document, window}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Navbar")
object Navbar {

    private var user: User = _
    private var selectedButtonId: String = _

    def me(): User = user

    def setMe(me: User): Unit = {
        this.user = me
        document.getElementById("user-login-text").textContent = s"${me.firstName} ${me.lastName}"
    }

    @JSExport
    def logout(): Unit = {
        App.logout().onComplete { (_, status) =>
            if (status == 200) {
                user = null
                window.location.href = Config.ROUTES("login")
            } else {
                // TODO
            }
        }
    }

    @JSExport
    def goTo(page: String): Unit = {
        window.location.href = Config.ROUTES(page)
    }

    @JSExport
    def selectButton(buttonId: String): Unit = {
        val oldButton = document.getElementById(selectedButtonId)

        if (oldButton.classList.contains("nav-button-selected")) {
            oldButton.classList.remove("nav-button-selected")
            oldButton.classList.add("nav-button")
        }

        val newButton = document.getElementById(buttonId)

        newButton.classList.add("nav-button-selected")
        newButton.classList.remove("nav-button")

        selectedButtonId = buttonId
    }

    @JSExport
    def showNotifications(): Unit = {
        val notifications = document.getElementById("notifications")

        if (notifications.hasAttribute("hidden")) {
            notifications.removeAttribute("hidden")
        } else {
            notifications.setAttribute("hidden", "hidden")
        }
        // TODO load notifications
    }

    @JSExport
    def dismissNotification(id: Integer): Unit = {
        // TODO backend

        val notification = document.getElementById(s"notification-$id")

        notification.parentNode.removeChild(notification)

        val table = document.getElementById("notifications-table")

        if (table.getElementsByTagName("tr").length == 0) {
            table.setAttribute("hidden", "hidden")
            document.getElementById("no-new-notifications").removeAttribute("hidden")
        }
    }
}

trait Navbar {

    val pageButtonId: String

    final def init(): Unit = {
        window.onload = _ => {
            Navbar.selectedButtonId = pageButtonId
            Navbar.selectButton(pageButtonId)
            Users.me().onComplete { (user, status) =>
                status match {
                    case 200 => {
                        if (user.role == UserRole.ROLE_ADMIN.toString) {
                            document.getElementById("nav-administration").removeAttribute("hidden")
                        }

                        Navbar.setMe(user)

                        this.setNotificationCount()
                        this.onNavbarInitSuccess()
                    }
                    case 401 => window.location.href = Config.ROUTES("login")
                    case _ => {
                        // TODO
                    }
                }
            }
        }
    }

    def onNavbarInitSuccess(): Unit

    private def setNotificationCount(): Unit = {
        Notifications.unseenNotificationsCount().onComplete { (count, status) =>
            if (status == 200) {
                document.getElementById("notifications-count").textContent = count.toString

                if (count > 0) {
                    document.getElementById("notifications-div").classList.add("notifications-div-highlight")
                }
            } else {
                // TODO
            }
        }
    }
}
