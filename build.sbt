import org.scalatra.sbt.DistPlugin._
import org.scalatra.sbt.DistPlugin.DistKeys._
lazy val scalatraVersion = "2.3.1"
lazy val akkaVersion     = "2.3.10"
lazy val tikaVersion     = "1.8"
val myDistSettings = org.scalatra.sbt.DistPlugin.distSettings ++ Seq(
  mainClass in Dist   := Some("com.github.scrawler.web.JettyLauncher"),
  memSetting in Dist  := "2g",
  envExports in Dist  := Seq("LC_CTYPE=en_US.UTF-8", "LC_ALL=en_US.utf-8"),
  javaOptions in Dist ++= Seq("-Xss4m", "-Dfile.encoding=UTF-8")
)
lazy val root = (project in file(".")).settings(
  organization := "com.github",
  name := "scrawler",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.6",
  resolvers ++= Seq(
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  ),
  libraryDependencies ++= Seq(
    "org.scalaz"        %% "scalaz-core"       % "7.1.1",
    "org.scalatra"      %% "scalatra"          % scalatraVersion,
    "org.scalatra"      %% "scalatra-scalate"  % scalatraVersion,
    "org.scalatra"      %% "scalatra-specs2"   % scalatraVersion % "test",
    "org.scalatest"     %% "scalatest"         % "2.2.1"         % "test",
    "org.eclipse.jetty" %  "jetty-webapp"      % "9.3.0.M2"      % "container;compile",
    "javax.servlet"     %  "javax.servlet-api" % "3.1.0"         % "provided",
    "ch.qos.logback"    %  "logback-classic"   % "1.1.3"         % "runtime",
    "org.apache.tika"   %  "tika-core"         % tikaVersion,
    "org.apache.tika"   %  "tika-parsers"      % tikaVersion,
    "com.typesafe.akka" %% "akka-actor"        % akkaVersion,
    "com.typesafe.akka" %% "akka-remote"       % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"        % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit"      % akkaVersion
  )
).settings(jetty(): _*).settings(myDistSettings: _*)
