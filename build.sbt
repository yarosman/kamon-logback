import sbt.Keys.publishMavenStyle

/* =========================================================================================
 * Copyright © 2013-2017 the kamon project <http://kamon.io/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * =========================================================================================
 */

val kamonCore             = "io.kamon"        %%  "kamon-core"      % "1.1.3"
val kamonTestkit          = "io.kamon"        %%  "kamon-testkit"   % "1.1.3"
val latestLogbackClassic  = "ch.qos.logback"  %   "logback-classic" % "1.2.3"

lazy val root = (project in file("."))
  .settings(Seq(name := "kamon-logback", scalaVersion := "2.12.6"))
  .settings(aspectJSettings: _*)
  .settings(
    organization := "com.impactua",
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    publishMavenStyle := true,
    publishArtifact := true,
    publishArtifact in Test := false,
    bintrayReleaseOnPublish := true,
    bintrayPackage := name.value,
    bintrayOrganization in bintray := Some("yarosman")
  )
  .settings(
    libraryDependencies ++=
      compileScope(kamonCore, latestLogbackClassic) ++
        providedScope(aspectJ) ++
        testScope(kamonTestkit, scalatest))
