import com.github.retronym.SbtOneJar.oneJarSettings
lazy val akkaVersion     = "2.3.11"
lazy val tikaVersion     = "1.8"
mainClass in oneJar := Some("com.github.scrawler.web.JettyLauncher")
lazy val root = (project in file(".")).settings(
  organization := "com.github",
  name := "scrawler",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.6",
  libraryDependencies ++= Seq(
    "org.scalaz"        %% "scalaz-core"       % "7.1.1",
    "org.scalatest"     %% "scalatest"         % "2.2.1"         % "test",
    "org.eclipse.jetty" %  "jetty-webapp"      % "9.3.0.M2"      % "container;compile",
    "javax.servlet"     %  "javax.servlet-api" % "3.1.0"         % "provided",
    "com.typesafe.akka" %% "akka-actor"        % akkaVersion,
    "com.typesafe.akka" %% "akka-remote"       % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"        % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit"      % akkaVersion
  ),
  libraryDependencies += "org.apache.tika" % "tika-core" % tikaVersion excludeAll(
    ExclusionRule(organization = "commons-logging"), 
    ExclusionRule(organization = "com.googlecode.mp4parser")
  ),
  libraryDependencies += "org.apache.tika" % "tika-parsers" % tikaVersion excludeAll(
    ExclusionRule(organization = "commons-logging"), 
    ExclusionRule(organization = "com.googlecode.mp4parser")
  )
).settings(jetty(): _*).settings(oneJarSettings: _*)
