name := "snap"

version := "0.1"

scalaVersion := "2.12.11"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"

mainClass in (Compile, run) := Some("io.fele.snap.Main")