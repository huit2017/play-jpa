name := """play-jpa"""

version := "1.0-SNAPSHOT"

import com.typesafe.sbt.packager.Keys.scriptClasspath
scriptClasspath := {
  val originalClasspath = scriptClasspath.value
  val manifest = new java.util.jar.Manifest()
  manifest.getMainAttributes().putValue("Class-Path", originalClasspath.mkString(" "))
  val classpathJar = (target in Universal).value / "lib" / "classpath.jar"
  IO.jar(Seq.empty, classpathJar, manifest)
  Seq(classpathJar.getName)
}
mappings in Universal += (((target in Universal).value / "lib" / "classpath.jar") -> "lib/classpath.jar")


jacoco.settings

cpdSettings
cpdLanguage := de.johoop.cpd4sbt.Language.Java
//testNGSettings
// codequality.CodeQualityPlugin.Settings

lazy val commonSettings = Seq(
  EclipseKeys.skipParents in ThisBuild := false,
  organization := "huit2017",
  scalaVersion := "2.11.8",
  javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-encoding", "UTF-8"),
  sources in (Compile, doc) := Seq.empty,
  publishArtifact in (Compile, packageDoc) := false,
  PlayKeys.externalizeResources := false,
  libraryDependencies ++= Seq(
    javaJpa,
    filters,
    javaWs,
    cache,
    "com.github.mumoshu" %% "play2-memcached-play25" % "0.8.0",
    "com.google.guava" % "guava" % "21.0",
    "com.novocode" % "junit-interface" % "0.11" % "test",
    "com.opencsv" % "opencsv" % "4.0",
    "com.typesafe.play" %% "play-mailer" % "6.0.1",
    "com.typesafe.play" %% "play-mailer-guice" % "6.0.1",
    "dom4j" % "dom4j" % "1.6.1",
    "javax.mail" % "javax.mail-api" % "1.6.0",
    "junit" % "junit" % "4.12" % "test",
    "mysql" % "mysql-connector-java" % "5.1.36",
    "net.sourceforge.pmd" % "pmd" % "5.1.3" exclude("org.ow2.asm", "asm"),
    "org.apache.commons" % "commons-lang3" % "3.3.2",
    "org.apache.poi" % "poi" % "3.15",
    "org.apache.poi" % "poi-ooxml" % "3.15",
    "org.hibernate" % "hibernate-core" % "5.2.5.Final",
    "org.hibernate" % "hibernate-entitymanager" % "5.2.5.Final",
    "org.mockito" % "mockito-core" % "2.1.0",
    "org.olap4j" % "olap4j" % "1.2.0"
  )
)
  
lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .enablePlugins(SbtWeb)
  .aggregate(member, admin, batch)
  .dependsOn(member, admin, batch)
  .settings(commonSettings: _*)
  .settings(findbugsSettings: _*)
  .settings(findbugsExcludeFilters :=  Some(<FindBugsFilter>
    <!-- Exclude classes generated by PlayFramework. See docs/examples
         at http://findbugs.sourceforge.net/manual/filter.html for the
         filtering rules. -->
    <Match>
      <Class name="~views\.html\..*"/>
    </Match>
    <Match>
      <Class name="~Routes.*"/>
    </Match>
    <Match>
      <Class name="~controllers\.routes.*"/>
    </Match>
    <Match>
      <Class name="web*"/>
    </Match>
  </FindBugsFilter>)
  )
  
lazy val member = (project in file("modules/member"))
  .enablePlugins(PlayJava)
  .enablePlugins(SbtWeb)
  .settings(commonSettings: _*)
  .settings(findbugsSettings: _*)
  .settings(findbugsExcludeFilters :=  Some(<FindBugsFilter>
    <Match>
      <Class name="~views\.html\..*"/>
    </Match>
    <Match>
      <Class name="~Routes.*"/>
    </Match>
    <Match>
      <Class name="~controllers\.routes.*"/>
    </Match>
    <Match>
      <Class name="web*"/>
    </Match>
  </FindBugsFilter>)
  )
  
lazy val admin = (project in file("modules/admin"))
  .enablePlugins(PlayJava)
  .enablePlugins(SbtWeb)
  .settings(commonSettings: _*)
  
lazy val batch = (project in file("modules/batch"))
  .enablePlugins(PlayJava)
  .enablePlugins(SbtWeb)
  .settings(commonSettings: _*)