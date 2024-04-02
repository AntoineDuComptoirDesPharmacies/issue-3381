name := "example-mininal"

version := "1.1-SNAPSHOT"

scalaVersion := "2.13.12"

lazy val akkaVersion = "2.5.3"

libraryDependencies ++= Seq(
  "io.ebean" % "ebean-agent" % "15.0.1",
  "io.ebean" % "ebean" % "15.0.2",
  "com.h2database" % "h2" % "2.2.224",
  "io.ebean" % "ebean-test" % "15.0.2" % Test,
  "org.slf4j" % "slf4j-api" % "1.7.20",
  "junit" % "junit" % "4.13.2" % Test
)

Compile / run / mainClass := Some("org.example.App")

