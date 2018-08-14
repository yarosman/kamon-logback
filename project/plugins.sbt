lazy val root: Project = project.in(file(".")).dependsOn(latestSbtUmbrella)
lazy val latestSbtUmbrella = uri("git://github.com/kamon-io/kamon-sbt-umbrella.git")

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.4")
