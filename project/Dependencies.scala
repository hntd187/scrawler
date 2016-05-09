import sbt._

object Dependencies {

  lazy val akkaVersion = "2.4.4"
  lazy val tikaVersion = "1.12"
  lazy val scalaTestVersion = "2.2.6"
  lazy val log4jVersion = "2.5"
  lazy val slf4jVersion = "1.7.21"
  lazy val scalaLibVersion = "2.11.8"
  lazy val ficusVersion = "1.2.3"
  lazy val typesafeConfigVersion = "1.3.0"
  lazy val catsVersion = "0.6.0-M1"

  lazy val scalaLib = Seq("org.scala-lang" % "scala-library" % scalaLibVersion)

  lazy val loggingDeps = Seq(
    "org.apache.logging.log4j" % "log4j-core" % log4jVersion,
    "org.apache.logging.log4j" % "log4j-api" % log4jVersion,
    "org.apache.logging.log4j" % "log4j-slf4j-impl" % log4jVersion,
    "org.slf4j" % "slf4j-api" % slf4jVersion
  )

  lazy val testDeps = Seq(
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
  )

  lazy val akkaDeps = Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-remote" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  )

  lazy val tikaDeps = Seq(
    "org.apache.tika" % "tika-core" % tikaVersion,
    "org.apache.tika" % "tika-parsers" % tikaVersion,
    "org.jsoup" % "jsoup" % "1.9.1"
  )

  lazy val universalDeps = Seq(
    "com.typesafe" % "config" % typesafeConfigVersion,
    "com.iheart" %% "ficus" % ficusVersion,
  "org.typelevel" %% "cats" % catsVersion
  ) ++ loggingDeps ++ scalaLib


  lazy val dependencies = akkaDeps ++ tikaDeps ++ testDeps ++ universalDeps

}
