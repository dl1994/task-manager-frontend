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
    private var selectedButton: Element = _
    private lazy val userLoginText = new Element("user-login-text")
    private lazy val notifications = new Element("notifications")
    private lazy val notificationsTable = new Table("notifications-table") {
        override val header: String = "" // TODO

        override protected def fillRow(item: Nothing): String = ??? // TODO
    }
    private lazy val noNewNotifications = new Element("no-new-notifications")
    private lazy val notificationsCount = new Element("notifications-count")
    private lazy val notificationsDiv = new Element("notifications-div")

    def me(): User = user

    def setMe(me: User): Unit = {
        this.user = me
        userLoginText.text(s"${me.firstName} ${me.lastName}")
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
        selectedButton.removeClass("nav-button-selected")
        selectedButton.addClass("nav-button")
        selectedButton = new Element(buttonId)
        selectedButton.addClass("nav-button-selected")
        selectedButton.removeClass("nav-button")
    }

    @JSExport
    def showNotifications(): Unit = {
        notifications.toggle()
        // TODO load notifications
    }

    @JSExport
    def dismissNotification(id: Integer): Unit = {
        // TODO backend

        val notification = document.getElementById(s"notification-$id")

        notification.parentNode.removeChild(notification)

        if (notificationsTable.isEmpty()) {
            notificationsTable.hide()
            noNewNotifications.show()
        }
    }

    private def showButtonsForCurrentUser(): Unit = {
        if (user.role == UserRole.ROLE_ADMIN.toString) {
            document.getElementById("nav-administration").removeAttribute("hidden")
        }
    }

    private def setNotificationCount(): Unit = {
        Notifications.unseenNotificationsCount().onComplete { (count, status) =>
            if (status == 200) {
                notificationsCount.text(count.toString)

                if (count > 0) {
                    notificationsDiv.addClass("notifications-div-highlight")
                }
            } else {
                // TODO
            }
        }
    }
}

trait Navbar {

    val pageButtonId: String

    final def init(): Unit = {
        window.onload = _ => {
            Navbar.selectedButton = new Element(pageButtonId)
            Navbar.selectButton(pageButtonId)
            Users.me().onComplete { (user, status) =>
                status match {
                    case 200 => {
                        Navbar.setMe(user)
                        Navbar.showButtonsForCurrentUser()
                        Navbar.setNotificationCount()

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
}
