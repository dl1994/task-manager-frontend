package at.doml.taskmanager.backend.controllers

import at.doml.taskmanager.backend.Response
import at.doml.taskmanager.backend.models.req.CommentReq
import at.doml.taskmanager.backend.models.resp.Aliases.{-, Comment, Empty}
import scala.scalajs.js

object Comments extends Controller {

    private implicit val prefix: String = "/comments"

    def getCommentsForTask(taskId: Int, page: Int, itemsPerPage: Int): Response[js.Array[Comment]] =
        get(s"/$taskId" + optParams(
            "page" -> page,
            "itemsPerPage" -> itemsPerPage
        )) asListOf Comment

    def commentOnTask(taskId: Int, req: CommentReq): Response[Comment] = put(s"/$taskId") withJson req as Comment

    def editComment(commentId: Int, req: CommentReq): Response[Comment] = post(s"/$commentId") withJson req as Comment

    def deleteComment(commentId: Int): Response[-] = delete(s"/$commentId") as Empty
}
