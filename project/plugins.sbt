scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
addSbtPlugin("com.earldouglas"  % "xsbt-web-plugin" % "1.1.0")
resolvers += Resolver.url("sbt-plugin-releases-artifactory", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/")
)(Resolver.ivyStylePatterns)
addSbtPlugin("com.github.retronym" % "sbt-onejar" % "0.8")

// IDE settings
//addSbtPlugin("com.github.mpeltonen"    % "sbt-idea"          % "1.6.0")
//addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.5.0")
