// The Play plugin
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "sbt community repository" at "http://dl.bintray.com/sbt/sbt-plugin-releases/"

// resolvers += "corux-releases" at "https://nexus.corux.de/content/repositories/releases/"
// resolvers += "corux-snapshots" at "https://nexus.corux.de/content/repositories/snapshots/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.10")
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.1.0")
// addSbtPlugin("de.corux" %% "sbt-code-quality" % "0.2.0")
addSbtPlugin("de.johoop" % "cpd4sbt" % "1.1.5")
addSbtPlugin("de.johoop" % "findbugs4sbt" % "1.3.0")
addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.6")
// addSbtPlugin("de.johoop" % "sbt-testng-plugin" % "3.0.2")
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-M15")