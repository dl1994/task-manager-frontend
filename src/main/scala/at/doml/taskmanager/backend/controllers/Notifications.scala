package at.doml.taskmanager.backend.controllers

import at.doml.taskmanager.backend.Response
import at.doml.taskmanager.backend.models.resp.Aliases.{-, Count, Empty, Notification}
import scala.scalajs.js

object Notifications extends Controller {

    private implicit val prefix: String = "/notifications"

    def getNotifications(page: Int, itemsPerPage: Int): Response[js.Array[Notification]] =
        get(optParams(
            "page" -> page,
            "itemsPerPage" -> itemsPerPage
        )) asListOf Notification

    def unseenNotificationsCount(): Response[Count] = get("/unseen-count") as Count

    def markAsSeen(notificationId: Int): Response[-] = post(s"/mark-seen/$notificationId") as Empty

    def markAsClicked(notificationId: Int): Response[-] = post(s"/mark-clicked/$notificationId") as Empty

    def deleteNotification(notificationId: Int): Response[-] = delete(s"/$notificationId") as Empty
}
