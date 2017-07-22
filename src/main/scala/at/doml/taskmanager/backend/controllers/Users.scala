package at.doml.taskmanager.backend.controllers

import at.doml.taskmanager.backend.Response
import at.doml.taskmanager.backend.models.req.{PasswordReq, UserReq}
import at.doml.taskmanager.backend.models.resp.Aliases.{-, Empty, User}

object Users extends UsersController {

    def me(): Response[User] = get("/me") as User

    def getUser(userId: Int): Response[User] = get(s"/$userId") as User

    def changeInfo(req: UserReq): Response[User] = post("/me/change-info") withJson req as User

    def changePassword(req: PasswordReq): Response[-] = post("/me/change-password") withJson req as Empty
}
