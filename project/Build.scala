import Dependencies._
import sbt.Keys._
import sbt._

object Build extends Build {

  unmanagedResourceDirectories in Compile += {
    baseDirectory.value / "src/main/webapp"
  }

  lazy val root = (project in file(".")).
    settings(
      organization := "com.github",
      name := "scrawler",
      scalaVersion := "2.11.8",
      crossScalaVersions := Seq("2.11.8", "2.12.0-M4"),
      scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
      libraryDependencies ++= dependencies,
      resolvers += Resolver.jcenterRepo
    )
}