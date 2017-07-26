package at.doml.taskmanager.pages

import at.doml.taskmanager.backend.models.resp.Aliases.{Project, Task, User}
import at.doml.taskmanager.components.{Element, Navbar, Page, Table, TasksTable}
import org.scalajs.dom.document
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Projects")
object Projects extends Page with Navbar {

    override val pageButtonId: String = "nav-projects"

    private lazy val usersTable = new Table[User]("project-users") {
        // language=HTML
        override val header: String =
            """
            <th width="250">Name</th>
            <th width="50">Action</th>
            """

        override protected def fillRow(user: User): String = {
            // language=HTML
            s"""
            <td>${user.firstName} ${user.lastName}</td>
            <td>
                <button class="button danger-button small-button" title="Delete user" onclick=""><!-- TODO -->
                    <img height="9" src="img/delete.svg"/>
                </button>
            </td>
            """
        }
    }

    private lazy val inProgressTasksTable = new TasksTable("in-progress-tasks") {
        override protected val firstTimeField: String = "Started"
        override protected val secondTimeField: String = "Due"

        override protected def getFirstTimeField(task: Task): Number = task.startedTimestamp

        override protected def getSecondTimeField(task: Task): Number = task.dueTimestamp

        // language=HTML
        override protected def getButtons: String =
            """
            <button class="button success-button small-button" title="Mark as done">
                <img height="12" src="img/done.svg"/>
            </button>
            <button class="button confirm-button small-button" title="Assign task">
                <img height="12" src="img/take.svg"/>
            </button>
            <button class="button confirm-button small-button" title="Edit task">
                <img height="12" src="img/edit.svg"/>
            </button>
            <button class="button danger-button small-button" title="Delete task" >
                <img height="12" src="img/delete.svg"/>
            </button>
            """
    }

    private lazy val newTasksTable = new TasksTable("new-tasks") {
        override protected val firstTimeField: String = "Created"
        override protected val secondTimeField: String = "Due"

        override protected def getFirstTimeField(task: Task): Number = task.createdTimestamp

        override protected def getSecondTimeField(task: Task): Number = task.dueTimestamp

        // language=HTML
        override protected def getButtons: String =
            """
            <button class="button success-button small-button" title="Start task">
                <img height="12" src="img/start.svg"/>
            </button>
            <button class="button confirm-button small-button" title="Assign task">
                <img height="12" src="img/take.svg"/>
            </button>
            <button class="button confirm-button small-button" title="Edit task">
                <img height="12" src="img/edit.svg"/>
            </button>
            <button class="button danger-button small-button" title="Delete task">
                <img height="12" src="img/delete.svg"/>
            </button>
            """
    }

    private lazy val doneTasksTable = new TasksTable("done-tasks") {
        override protected val firstTimeField: String = "Started"
        override protected val secondTimeField: String = "Finished"

        override protected def getFirstTimeField(task: Task): Number = task.startedTimestamp

        override protected def getSecondTimeField(task: Task): Number = task.finishedTimestamp

        // language=HTML
        override protected def getButtons: String =
            """
            <button class="button confirm-button small-button" title="Re-open task">
                <img height="12" src="img/restart.svg"/>
            </button>
            <button class="button danger-button small-button" title="Delete task">
                <img height="12" src="img/delete.svg"/>
            </button>
            """
    }

    private lazy val usersText = new Element("users-text")
    private lazy val projectTiles = new Element("project-tiles")
    private lazy val projectName = new Element("project-name")
    private lazy val projectDescription = new Element("project-description")
    private lazy val noProject = new Element("no-project")
    private lazy val selectedProject = new Element("selected-project")
    private lazy val inProgressLabel = new Element("in-progress-label")
    private lazy val newLabel = new Element("new-label")
    private lazy val doneLabel = new Element("done-label")
    private lazy val inProgressText = new Element("in-progress-text")
    private lazy val newText = new Element("new-text")
    private lazy val doneText = new Element("done-text")
    private lazy val noTasksLabel = new Element("no-tasks-label")
    private lazy val overviewTab = new Element("overview-tab")
    private lazy val tasksTab = new Element("tasks-tab")
    private lazy val overviewPanel = new Element("overview-panel")
    private lazy val tasksPanel = new Element("tasks-panel")

    private var users: js.Array[User] = js.Array()
    private var projects: js.Array[Project] = js.Array()
    private var currentProject: Project = _
    private var newTasks: js.Array[Task] = js.Array()
    private var doneTasks: js.Array[Task] = js.Array()
    private var inProgressTasks: js.Array[Task] = js.Array()

    override def onNavbarInitSuccess(): Unit = {
        // TODO load from backend
        // language=JSON
        projects = JSON.parse(
            """
            [
                {
                    "id": 1,
                    "name": "project 1",
                    "description": "this is a test project with tasks",
                    "ownerId": 1,
                    "projectStatus": "ACTIVE"
                }, {
                    "id": 2,
                    "name": "project 2",
                    "description": "this is a test project without tasks",
                    "ownerId": 1,
                    "projectStatus": "ACTIVE"
                }
            ]
            """).asInstanceOf[js.Array[Project]]

        projectTiles.empty()

        for (index <- 0 until projects.length) {
            val project = projects(index)

            // language=HTML
            projectTiles.append(
                s"""
                <div id="project-${project.id}" onclick="Projects.selectProject($index)" class="project-tile">
                    <p>${project.name}</p>
                </div>
                """
            )
        }
    }

    @JSExport
    def selectProject(projectIndex: Int): Unit = {
        val project = projects(projectIndex)

        if (currentProject != null && currentProject.id == project.id) {
            return
        }

        if (currentProject != null) {
            document.getElementById(s"project-${currentProject.id}").classList.remove("selected-project-tile")
        }

        currentProject = project

        this.loadTasks()
        this.loadUsers()

        document.getElementById(s"project-${project.id}").classList.add("selected-project-tile")
        projectName.text(project.name)
        projectDescription.text(project.description)
        noProject.noDisplay()
        selectedProject.show()
    }

    private def loadTasks(): Unit = {
        this.clearTasks()

        if (currentProject.id == 1) {
            // language=JSON
            inProgressTasks = JSON.parse(
                """
                [
                    {
                        "id": 555,
                        "priority": 3,
                        "subject": "test task 1",
                        "assigneeId": 1,
                        "createdTimestamp": 1487710619000,
                        "startedTimestamp": 1487710619000,
                        "dueTimestamp": 1487710619000,
                        "finishedTimestamp": 1487710619000,
                        "status": "IN_PROGRESS"
                    }, {
                        "id": 2342,
                        "priority": 2,
                        "subject": "test task 2",
                        "assigneeId": null,
                        "createdTimestamp": 1487710619000,
                        "startedTimestamp": 1487710619000,
                        "dueTimestamp": 1487710619000,
                        "finishedTimestamp": 1487710619000,
                        "status": "IN_PROGRESS"
                    }
                ]
                """).asInstanceOf[js.Array[Task]]
            // language=JSON
            newTasks = JSON.parse(
                """
                [
                    {
                        "id": 235,
                        "priority": 4,
                        "subject": "new task 1",
                        "assigneeId": 1,
                        "createdTimestamp": 1487710619000,
                        "startedTimestamp": 1487710619000,
                        "dueTimestamp": 1487710619000,
                        "finishedTimestamp": 1487710619000,
                        "status": "NEW"
                    }
                ]
                """).asInstanceOf[js.Array[Task]]
            // language=JSON
            doneTasks = JSON.parse(
                """
                [
                    {
                        "id": 999,
                        "priority": 1,
                        "subject": "done task",
                        "assigneeId": 1,
                        "createdTimestamp": 1487710619000,
                        "startedTimestamp": 1487710619000,
                        "dueTimestamp": 1487710619000,
                        "finishedTimestamp": 1487710619000,
                        "status": "DONE"
                    }
                ]
                """).asInstanceOf[js.Array[Task]]
        } else {
            inProgressTasks = new js.Array[Task]()
            newTasks = new js.Array[Task]()
            doneTasks = new js.Array[Task]()
        }
        // TODO load from backend

        if (inProgressTasks.length == 0 && newTasks.length == 0 && doneTasks.length == 0) {
            this.hideTasks()
        } else {
            this.fillTableIfNotEmpty(inProgressTasksTable, inProgressText, inProgressTasks)
            this.fillTableIfNotEmpty(newTasksTable, newText, newTasks)
            this.fillTableIfNotEmpty(doneTasksTable, doneText, doneTasks)
            this.showTasks()
        }
    }

    private def fillTableIfNotEmpty(table: TasksTable, hiddenText: Element, tasks: js.Array[Task]): Unit = {
        if (tasks.length == 0) {
            hiddenText.show()
        } else {
            hiddenText.hide()
            table.fillTable(tasks)
        }
    }

    private def clearTasks(): Unit = {
        inProgressTasksTable.empty()
        newTasksTable.empty()
        doneTasksTable.empty()
    }

    private def hideTasks(): Unit = {
        inProgressTasksTable.hide()
        newTasksTable.hide()
        doneTasksTable.hide()
        inProgressLabel.hide()
        newLabel.hide()
        doneLabel.hide()
        inProgressText.hide()
        newText.hide()
        doneText.hide()
        noTasksLabel.show()
    }

    private def showTasks(): Unit = {
        inProgressTasksTable.show()
        newTasksTable.show()
        doneTasksTable.show()
        inProgressLabel.show()
        newLabel.show()
        doneLabel.show()
        noTasksLabel.hide()
    }

    private def loadUsers(): Unit = {
        usersTable.empty()

        // TODO load from backend
        users = if (currentProject.id == 1) {
            // language=JSON
            JSON.parse(
                """
                [{
                    "id": 11,
                    "username": "user1",
                    "firstName": "User",
                    "lastName": "Test",
                    "role": "ROLE_ADMIN"
                }, {
                    "id": 22,
                    "username": "user2",
                    "firstName": "UUser",
                    "lastName": "TTest",
                    "role": "ROLE_USER"
                }]
                """).asInstanceOf[js.Array[User]]
        } else {
            new js.Array[User]()
        }

        if (users.length == 0) {
            usersTable.hide()
            usersText.show()
        } else {
            usersTable.fillTable(users)
            usersTable.show()
            usersText.hide()
        }
    }

    private def switchTab(newTab: Element, oldTab: Element): Unit = {
        if (!newTab.hasClass("selected-project-tab")) {
            newTab.removeClass("project-tab")
            newTab.addClass("selected-project-tab")
            oldTab.removeClass("selected-project-tab")
            oldTab.addClass("project-tab")
        }
    }

    @JSExport
    def showOverviewTab(): Unit = {
        this.switchTab(overviewTab, tasksTab)

        overviewPanel.show()
        tasksPanel.hide()
    }

    @JSExport
    def showTasksTab(): Unit = {
        this.switchTab(tasksTab, overviewTab)

        tasksPanel.show()
        overviewPanel.hide()
    }
}
