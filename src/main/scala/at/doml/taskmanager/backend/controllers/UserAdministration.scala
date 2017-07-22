package at.doml.taskmanager.backend.controllers

import at.doml.taskmanager.backend.Response
import at.doml.taskmanager.backend.models.req.{NewUserReq, PasswordReq, RoleReq, UserReq}
import at.doml.taskmanager.backend.models.resp.Aliases.{-, Empty, User}

object UserAdministration extends UsersController {

    def deleteUser(userId: Int): Response[-] = delete(s"/$userId") as Empty

    def createUser(req: NewUserReq): Response[User] = put("/new") withJson req as User

    def changeInfo(userId: Int, req: UserReq): Response[User] = post(s"/$userId/change-info") withJson req as User

    def changePassword(userId: Int, req: PasswordReq): Response[-] =
        post(s"/$userId/change-password") withJson req as Empty

    def changeRole(userId: Int, req: RoleReq): Response[User] = post(s"/$userId/change-role") withJson req as User

    def expireSessions(userId: Int): Response[-] = get(s"/$userId/expire-sessions") as Empty
}
