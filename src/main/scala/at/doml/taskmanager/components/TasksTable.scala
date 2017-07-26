package at.doml.taskmanager.components

import at.doml.taskmanager.Config
import at.doml.taskmanager.backend.models.resp.Aliases.Task
import org.scalajs.dom
import org.scalajs.dom.document
import scala.scalajs.js

abstract class TasksTable(element: dom.raw.Element) extends Table[Task](element) {

    def this(id: String) = this(document.getElementById(id))

    protected val firstTimeField: String
    protected val secondTimeField: String

    // language=HTML
    override val header: String =
        s"""
        <th width="80pt">ID</th>
        <th width="80pt">Priority</th>
        <th>Subject</th>
        <th width="140pt">Assignee</th>
        <th width="140pt">$firstTimeField</th>
        <th width="140pt">$secondTimeField</th>
        <th width="140pt">Action</th>
        """

    override protected def fillRow(task: Task): String = {
        def priorityDivs(priority: Int): String = {
            var output = ""

            for (i <- 1 to Config.MAX_PRIORITY) {
                if (i <= priority) {
                    output += """<span class="activated-span"></span>"""
                } else {
                    output += "<span></span>"
                }
            }

            output
        }

        def assignee(task: Task): String = {
            "-" // TODO
        }

        def toDate(timestamp: Number): String = {
            if (timestamp == null) {
                "-"
            } else {
                val date = new js.Date(timestamp.toString)
                s"${date.getHours()}:${date.getMinutes()} ${date.getDate()}-" +
                    s"${date.getMonth() + 1}-${date.getFullYear()}"
            }
        }

        // language=HTML
        s"""
        <td>#${task.id}</td>
        <td><div class="priority-div">${priorityDivs(task.priority)}</div></td>
        <td>${task.subject}</td>
        <td>${assignee(task)}</td>
        <td>${toDate(this.getFirstTimeField(task))}</td>
        <td>${toDate(this.getSecondTimeField(task))}</td>
        <td>${this.getButtons}</td>
        """
    }

    protected def getFirstTimeField(task: Task): Number

    protected def getSecondTimeField(task: Task): Number

    protected def getButtons: String
}
