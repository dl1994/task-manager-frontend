<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="partial/head-includes.html" %>
        <script src="js/modal.js" type="text/javascript"></script>
        <script src="js/projects.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/modal.css"/>
        <link rel="stylesheet" type="text/css" href="css/projects.css"/>
        <title>Projects</title>
    </head>
    <body>
        <%@ include file="partial/navbar.html" %>
        <div id="body-div" class="body-div">
            <%@ include file="partial/modal.html" %>
            <table class="body-table">
                <tr>
                    <td class="projects" valign="top" align="center">
                        <h1>Projects</h1>
                        <div class="project-container">
                            <div id="project-tiles">
                            </div>
                            <div class="project-buttons">
                                <button class="button success-button large-button" style='width: 100%'
                                        onclick="showModal('#new-project-modal');">
                                    <img class="large-button-img" height="15" src="img/plus.svg" alt="New Project"/>New
                                                                                                                    Project
                                </button>
                            </div>
                        </div>
                    </td>
                    <td class="project-panel" valign="top">
                        <div id="no-project" class="no-selected-project-div">
                            <div>
                                <h2>NO PROJECT SELECTED</h2>
                            </div>
                        </div>
                        <div id="selected-project" hidden>
                            <div class="project-navbar">
                                <div id="overview-tab" class="selected-project-tab" onclick="showOverviewTab();">
                                    Overview
                                </div>
                                <div id="tasks-tab" class="project-tab" onclick="showTasksTab();">
                                    Tasks
                                </div>
                            </div>
                            <div id="overview-panel">
                                <h1 id="project-name"></h1>
                                <p id="project-description"></p>
                                <h3>INVOLVED USERS</h3>
                                <div class="task-buttons">
                                    <button class="button success-button small-button">
                                        <img class="small-button-img" height="9" src="img/plus.svg" alt="Add User"/>Add
                                                                                                                    User
                                    </button>
                                </div>
                                <h4 id="users-text" hidden>No users are involved in this project</h4>
                                <table id="project-users" class="users-table" border="1">
                                </table>
                            </div>
                            <div id="tasks-panel" hidden>
                                <div class="task-buttons">
                                    <button class="button success-button medium-button"
                                            onclick="showModal('#new-task-modal');">
                                        <img class="medium-button-img" height="12" src="img/plus.svg" alt="New Task"/>New
                                                                                                                      Task
                                    </button>
                                </div>
                                <h2 id="no-tasks-label">This project has no tasks</h2>
                                <h2 id="in-progress-label">IN PROGRESS</h2>
                                <h3 id="in-progress-text">No tasks are in progress</h3>
                                <table id="in-progress-tasks" class="tasks-table" border="1">
                                </table>
                                <h2 id="new-label">NEW</h2>
                                <h3 id="new-text">No new tasks</h3>
                                <table id="new-tasks" class="tasks-table" border="1">
                                </table>
                                <h2 id="done-label">DONE</h2>
                                <h3 id="done-text">No tasks have been completed</h3>
                                <table id="done-tasks" class="tasks-table" border="1">
                                </table>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
