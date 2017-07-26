package at.doml.taskmanager.backend.models.resp

object Aliases {

    final type - = Unit
    final val Empty: Class[-] = classOf[-]
    final type Count = Int
    final val Count: Class[Count] = classOf[Count]
    final type User = UserResp
    final val User: Class[User] = classOf[User]
    final type Comment = CommentResp
    final val Comment: Class[Comment] = classOf[Comment]
    final type Task = TaskResp
    final val Task: Class[Task] = classOf[Task]
    final type Project = ProjectResp
    final val Project: Class[Project] = classOf[Project]
    final type Notification = NotificationResp
    final val Notification: Class[Notification] = classOf[Notification]
}
