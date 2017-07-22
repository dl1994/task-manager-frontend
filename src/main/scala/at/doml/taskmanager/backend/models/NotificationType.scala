package at.doml.taskmanager.backend.models

object NotificationType extends Enumeration {

    final val PROJECT = Value("PROJECT")
    final val TASK = Value("TASk")
    final val COMMENT = Value("COMMENT")
}
