import Dependencies._

enablePlugins(JettyPlugin)

lazy val libVer   = "0.1.0-SNAPSHOT"
lazy val scalaVer = "2.11.7"

lazy val root = (project in file(".")).
  settings(
    organization := "com.github",
    name 	 := "scrawler",
    version      := libVer,
    scalaVersion := scalaVer,
    mainClass       in assembly := Some("com.github.scrawler.web.JettyLauncher"),
    assemblyJarName in assembly := s"scrawler-$libVer.jar",
    libraryDependencies         ++= dependencies
  )
