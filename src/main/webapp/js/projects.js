var projects = {};
var users = [];
var inProgressTasks = [];
var newTasks = [];
var doneTasks = [];
var currentProject = null;

initializer.successCallback = loadProjects;
$(document).ready(selectButtonOnLoad("#nav-projects"));

function fillUsersTable() {
    var table = $("#project-users");

    table.append('<tr>' +
        '<th width="250">Name</th>' +
        '<th width="50">Action</th>' +
        '</tr>');

    for (var i = 0; i < users.length; i++) {
        var user = users[i];

        table.append('<tr class=' + (i % 2 === 0 ? '"light-row"' : '"dark-row"') + '>' +
            '<td>' + user.firstName + ' ' + user.lastName + '</td>' +
            '<td>' +
            '<button class="button red-button small-button" title="Delete task" ' +
            'onclick="">' +
            '<img height="9" src="img/delete.svg"/>' +
            '</button>' +
            '</td>' +
            '</tr>');
    }
}

function loadUsers(projectId) {
    var projectUsers = $("#project-users");
    var usersText = $("#users-text");

    projectUsers.empty();

    // TODO load from backend
    if (projectId === "1") {
        users = [{
            id: 11,
            username: "user1",
            firstName: "User",
            lastName: "Test",
            role: "ROLE_ADMIN"
        }, {
            id: 22,
            username: "user2",
            firstName: "UUser",
            lastName: "TTest",
            role: "ROLE_USER"
        }];
    } else {
        users = [];
    }

    if (users.length === 0) {
        projectUsers.hide();
        usersText.show();
    } else {
        fillUsersTable();
        usersText.hide();
    }

    projectUsers.show();
}

function clearTasks() {
    $("#in-progress-tasks").empty();
    $("#new-tasks").empty();
    $("#done-tasks").empty();
}

function showNoTasks() {
    $("#in-progress-tasks").hide();
    $("#new-tasks").hide();
    $("#done-tasks").hide();
    $("#in-progress-label").hide();
    $("#new-label").hide();
    $("#done-label").hide();
    $("#in-progress-text").hide();
    $("#new-text").hide();
    $("#done-text").hide();
    $("#no-tasks-label").show();
}

function showTasks() {
    $("#in-progress-tasks").show();
    $("#new-tasks").show();
    $("#done-tasks").show();
    $("#in-progress-label").show();
    $("#new-label").show();
    $("#done-label").show();
    $("#no-tasks-label").hide();
}

function fillTaskTable(tableIdentifier, tasks, util) {
    var table = $(tableIdentifier);

    table.append('<tr>' +
        '<th width="80pt">ID</th>' +
        '<th width="80pt">Priority</th>' +
        '<th>Subject</th>' +
        '<th width="140pt">Assignee</th>' +
        '<th width="140pt">' + util.firstField + '</th>' +
        '<th width="140pt">' + util.secondField + '</th>' +
        '<th width="140pt">Action</th>' +
        '</tr>');

    for (var i = 0; i < tasks.length; i++) {
        var task = tasks[i];

        table.append('<tr class=' + (i % 2 === 0 ? '"light-row"' : '"dark-row"') + '>' +
            '<td>#' + task.id + '</td>' +
            '<td>' +
            '<div class="priority-div">' +
            prioritySpans(task.priority, MAX_PRIORITY) +
            '</div>' +
            '</td>' +
            '<td>' + task.subject + '</td>' +
            '<td>' + toString(task.assignee) + '</td>' +
            util.filler(task, i) +
            '</tr>');
    }
}

function loadTasks(projectId) {
    clearTasks();

    // TODO load from backend
    if (projectId === "1") {
        inProgressTasks = [{
            id: 555,
            priority: 3,
            subject: "test task 1",
            assignee: "user 1",
            createdTimestamp: 1487710619000,
            startedTimestamp: 1487710619000,
            dueTimestamp: 1487710619000,
            finishedTimestamp: 1487710619000,
            status: "IN_PROGRESS"
        }, {
            id: 2342,
            priority: 2,
            subject: "test task 2",
            assignee: null,
            createdTimestamp: 1487710619000,
            startedTimestamp: 1487710619000,
            dueTimestamp: 1487710619000,
            finishedTimestamp: 1487710619000,
            status: "IN_PROGRESS"
        }];

        newTasks = [{
            id: 235,
            priority: 4,
            subject: "new task 1",
            assignee: "user 2",
            createdTimestamp: 1487710619000,
            startedTimestamp: 1487710619000,
            dueTimestamp: 1487710619000,
            finishedTimestamp: 1487710619000,
            status: "NEW"
        }];

        doneTasks = [{
            id: 999,
            priority: 1,
            subject: "done task",
            assignee: "user 2",
            createdTimestamp: 1487710619000,
            startedTimestamp: 1487710619000,
            dueTimestamp: 1487710619000,
            finishedTimestamp: 1487710619000,
            status: "DONE"
        }];
    } else {
        inProgressTasks = [];
        newTasks = [];
        doneTasks = [];
    }

    if (inProgressTasks.length === 0 && newTasks.length === 0 && doneTasks.length === 0) {
        showNoTasks();
    } else {
        if (inProgressTasks.length === 0) {
            $("#in-progress-text").show();
        } else {
            $("#in-progress-text").hide();
            fillTaskTable("#in-progress-tasks", inProgressTasks, inProgressTasksUtil);
        }

        if (newTasks.length === 0) {
            $("#new-text").show();
        } else {
            $("#new-text").hide();
            fillTaskTable("#new-tasks", newTasks, newTasksUtil);
        }

        if (doneTasks.length === 0) {
            $("#done-text").show();
        } else {
            $("#done-text").hide();
            fillTaskTable("#done-tasks", doneTasks, doneTasksUtil);
        }

        showTasks();
    }
}

function loadProjects() {
    projects = {
        "1": {
            id: 1,
            name: "project 1",
            description: "this is a test project with tasks",
            owner: "user 1",
            projectStatus: "ACTIVE"
        }, "2": {
            id: 2,
            name: "project 2",
            description: "this is a test project without tasks",
            owner: "user 1",
            projectStatus: "ACTIVE"
        }
    }; // TODO: load from backend

    var projectTiles = $("#project-tiles");

    projectTiles.empty();

    for (var projectKey in projects) {
        if (projects.hasOwnProperty(projectKey)) {
            var project = projects[projectKey];

            projectTiles.append('<div id="project-' + project.id + '" onclick="selectProject(\'' + project.id + '\');"'
                + ' class="project-tile"><p>' + project.name + '</p></div>');
        }
    }
}

function prioritySpans(value, max) {
    var output = "";

    for (var i = 1; i <= max; i++) {
        if (i <= value) {
            output += '<span class="activated-span"></span>';
        } else {
            output += '<span></span>';
        }
    }

    return output;
}

function toString(string) {
    if (string === null) {
        return "-";
    } else {
        return string;
    }
}

function toDate(string) {
    if (string === null) {
        return "-";
    } else {
        var date = new Date(string);
        return date.getHours() + ":" + date.getMinutes() + " "
            + date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
    }
}

var inProgressTasksUtil = {
    firstField: "Started",
    secondField: "Due",
    filler: function(task, taskIndex) {
        return '<td>' + toDate(task.startedTimestamp) + '</td>' +
            '<td>' + toDate(task.dueTimestamp) + '</td>' +
            '<td>' +
            '<button class="button green-button small-button" title="Mark as done">' +
            '<img height="12" src="img/done.svg"/>' +
            '</button> ' +
            '<button class="button blue-button small-button" ' +
            'title="Assign task" ' +
            'onclick="showAssignModalInProgress(' + taskIndex + ');">' +
            '<img height="12" src="img/take.svg"/>' +
            '</button> ' +
            '<button class="button blue-button small-button" title="Edit task" ' +
            'onclick="showEditModalInProgress(' + taskIndex + ');">' +
            '<img height="12" src="img/edit.svg"/>' +
            '</button> ' +
            '<button class="button red-button small-button" title="Delete task" ' +
            'onclick="showDeleteModalInProgress(' + taskIndex + ');">' +
            '<img height="12" src="img/delete.svg"/>' +
            '</button>' +
            '</td>';
    }
};

var newTasksUtil = {
    firstField: "Created",
    secondField: "Due",
    filler: function(task, taskIndex) {
        return '<td>' + toDate(task.createdTimestamp) + '</td>' +
            '<td>' + toDate(task.dueTimestamp) + '</td>' +
            '<td>' +
            '<button class="button green-button small-button" title="Start task">' +
            '<img height="12" src="img/start.svg"/>' +
            '</button> ' +
            '<button class="button blue-button small-button" title="Assign task" ' +
            'onclick="showAssignModalNew(' + taskIndex + ');">' +
            '<img height="12" src="img/take.svg"/>' +
            '</button> ' +
            '<button class="button blue-button small-button" title="Edit task" ' +
            'onclick="showEditModalNew(' + taskIndex + ');">' +
            '<img height="12" src="img/edit.svg"/>' +
            '</button> ' +
            '<button class="button red-button small-button" title="Delete task" ' +
            'onclick="showDeleteModalNew(' + taskIndex + ');">' +
            '<img height="12" src="img/delete.svg"/>' +
            '</button>' +
            '</td>';
    }
};

var doneTasksUtil = {
    firstField: "Started",
    secondField: "Finished",
    filler: function(task, taskIndex) {
        return '<td>' + toDate(task.startedTimestamp) + '</td>' +
            '<td>' + toDate(task.finishedTimestamp) + '</td>' +
            '<td>' +
            '<button class="button blue-button small-button" title="Restart task">' +
            '<img height="12" src="img/restart.svg"/>' +
            '</button> ' +
            '<button class="button red-button small-button" title="Delete task" ' +
            'onclick="showDeleteModalDone(' + taskIndex + ');">' +
            '<img height="12" src="img/delete.svg"/>' +
            '</button>' +
            '</td>';
    }
};

function showAssignModal(task) {
    var dataList = $("#assignable-users");

    dataList.empty();

    for (var i = 0; i < users.length; i++) {
        var user = users[i];

        dataList.append('<option value="' + user.firstName + ' ' + user.lastName + '">' + user.username + '</option>');
    }

    $("#assign-task-name").text(task.subject);
    $("#assign-task-modal").show();
}

function showAssignModalInProgress(taskIndex) {
    showAssignModal(inProgressTasks[taskIndex]);
}

function showAssignModalNew(taskIndex) {
    showAssignModal(newTasks[taskIndex]);
}

function showEditModal(task) {
    $("#edit-task-modal").show();
    $("#task-subject").val(task.subject);
    $("#task-due").val(task.dueTimestamp);
    $("#task-priority").val(task.priority);
}

function showEditModalInProgress(taskIndex) {
    showEditModal(inProgressTasks[taskIndex]);
}

function showEditModalNew(taskIndex) {
    showEditModal(newTasks[taskIndex]);
}

function showDeleteModal(task) {
    $("#delete-task-modal").show();
    $("#delete-task-name").text(task.subject);
}

function showDeleteModalInProgress(taskIndex) {
    showDeleteModal(inProgressTasks[taskIndex]);
}

function showDeleteModalNew(taskIndex) {
    showDeleteModal(newTasks[taskIndex]);
}

function showDeleteModalDone(taskIndex) {
    showDeleteModal(doneTasks[taskIndex]);
}

function selectProject(projectId) {
    if (currentProject === projectId) {
        return;
    }

    $("#project-" + currentProject).removeClass("selected-project-tile");

    currentProject = projectId;

    loadTasks(projectId);
    loadUsers(projectId);

    $("#project-" + projectId).addClass("selected-project-tile");
    $("#project-name").text(projects[projectId].name);
    $("#project-description").text(projects[projectId].description);
    $("#no-project").hide();
    $("#selected-project").show();
}

function selectTab(tabId, otherId) {
    var tab = $(tabId);
    var otherTab = $(otherId);

    if (!tab.hasClass("selected-project-tab")) {
        tab.removeClass("project-tab");
        tab.addClass("selected-project-tab");
        otherTab.removeClass("selected-project-tab");
        otherTab.addClass("project-tab");
    }
}

function showOverviewTab() {
    selectTab("#overview-tab", "#tasks-tab");

    $("#overview-panel").show();
    $("#tasks-panel").hide();
}

function showTasksTab() {
    selectTab("#tasks-tab", "#overview-tab");

    $("#tasks-panel").show();
    $("#overview-panel").hide();
}
