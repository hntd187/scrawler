import sbt._

object Dependencies {
 
  lazy val akkaVersion = "2.3.11"
  lazy val tikaVersion = "1.10"
  
  val scalaz      = "org.scalaz"        %% "scalaz-core"       % "7.1.1"
  val scalatest   = "org.scalatest"     %% "scalatest"         % "2.2.1"         % "test"
  val jetty       = "org.eclipse.jetty" %  "jetty-webapp"      % "9.3.0.M2"      % "container;compile"
  val jettyPlus   = "org.eclipse.jetty" %  "jetty-plus"        % "9.3.0.M2"      % "container;compile"
  val servlet     = "javax.servlet"     %  "javax.servlet-api" % "3.1.0"         % "provided"
  val logback     = "ch.qos.logback"    %  "logback-classic"   % "1.1.3"
  val akkaActor   = "com.typesafe.akka" %% "akka-actor"        % akkaVersion
  val akkaRemote  = "com.typesafe.akka" %% "akka-remote"       % akkaVersion
  val akkaslf4j   = "com.typesafe.akka" %% "akka-slf4j"        % akkaVersion
  val akkaTestkit = "com.typesafe.akka" %% "akka-testkit"      % akkaVersion
  
  val tikaCore    = "org.apache.tika" % "tika-core" % tikaVersion excludeAll(
    ExclusionRule(organization = "commons-logging"), 
    ExclusionRule(organization = "com.googlecode.mp4parser")
  )
  
  val tikaParsers = "org.apache.tika" % "tika-parsers" % tikaVersion excludeAll(
    ExclusionRule(organization = "commons-logging"), 
    ExclusionRule(organization = "com.googlecode.mp4parser")
  )
  
  val dependencies = Seq(
    scalaz,
    scalatest,
    jetty,
    jettyPlus,
    servlet,
    logback,
    akkaActor,
    akkaRemote,
    akkaslf4j,
    akkaTestkit,
    tikaCore,
    tikaParsers
  )
  
}
