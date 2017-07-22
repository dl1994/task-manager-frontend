package at.doml.taskmanager.backend.models

object UserRole extends Enumeration {

    final val ROLE_ADMIN = Value("ROLE_ADMIN")
    final val ROLE_USER = Value("ROLE_USER")
}
