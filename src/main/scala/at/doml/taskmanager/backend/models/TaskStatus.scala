package at.doml.taskmanager.backend.models

object TaskStatus extends Enumeration {

    final val NEW = Value("NEW")
    final val IN_PROGRESS = Value("IN_PROGRESS")
    final val DONE = Value("DONE")
}
