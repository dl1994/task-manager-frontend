package at.doml.taskmanager.backend.models

object ProjectParticipantRole extends Enumeration {

    final val USER = Value("USER")
    final val ADMIN = Value("ADMIN")
    final val OWNER = Value("OWNER")
}
