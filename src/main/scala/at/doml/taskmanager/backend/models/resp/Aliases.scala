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
    final type Notification = NotificationResp
    final val Notification: Class[Notification] = classOf[Notification]
}
