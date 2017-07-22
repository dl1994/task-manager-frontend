package at.doml.taskmanager.backend.models

object NotificationStatus extends Enumeration {

    final val UNSEEN = Value("UNSEED")
    final val SEEN = Value("SEEN")
    final val CLICKED = Value("CLICKED")
}
