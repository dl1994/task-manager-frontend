package at.doml.taskmanager.backend.controllers

import at.doml.taskmanager.backend.Response
import at.doml.taskmanager.backend.models.resp.Aliases.{-, Empty, User}
import at.doml.taskmanager.components.Form

object App extends Controller {

    private implicit val prefix: String = ""

    def status(): Response[-] = get("/status") as Empty

    def login(data: Form): Response[User] = post("/login") useCredentials false withFromData data.serialize() as User

    def logout(): Response[-] = get("/logout") as Empty
}
