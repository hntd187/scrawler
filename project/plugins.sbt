scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

resolvers += Resolver.url("sbt-plugin-releases-artifactory", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")
addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "2.0.4")
//addSbtPlugin("org.scala-sbt.plugins" % "sbt-onejar" % "0.8")
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")
