name := "task-manager-frontend"
version := "1.0.0-SNAPSHOOT"
scalaVersion := "2.12.2"

// Dependencies
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.3"

// Plugins
enablePlugins(ScalaJSPlugin, WarPlugin)

// Custom tasks
val build = TaskKey[Unit]("build")
val buildDev = TaskKey[Unit]("buildDev")
val jsTargetFile = SettingKey[File]("jsTargetFile", "Location of generated JavaScript file.")

jsTargetFile := baseDirectory.value / "src" / "main" / "webapp" / "js" / "src.js"

artifactName in Keys.`package` := { (_: ScalaVersion, _: ModuleID, artifact: Artifact) =>
    artifact.name + "." + artifact.extension
}

webappPostProcess := {
    webappDir: File => {
        IO.delete(webappDir / "WEB-INF" / "lib")
    }
}

build := Def.sequential(
    fullOptJS in Compile,
    Def.task(IO.copyFile((fullOptJS in Compile).value.data, jsTargetFile.value)),
    Keys.`package`
).value

buildDev := Def.sequential(
    fastOptJS in Compile,
    Def.task(IO.copyFile((fastOptJS in Compile).value.data, jsTargetFile.value)),
    Keys.`package`
).value
