ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaPracticeWithAlgorithms"
  )

//scalacOptions += "-Ypartial-unification"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.7.0"
scalacOptions += "-language:higherKinds"
libraryDependencies += "dev.zio" %% "zio" % "2.0.0"
libraryDependencies += "dev.zio" %% "zio-streams" % "2.0.0"
libraryDependencies += "dev.zio" %% "zio-prelude" % "1.0.0-RC15"
