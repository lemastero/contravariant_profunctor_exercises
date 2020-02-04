name := "contravariant_profunctor_exercises"

version := "0.0.1"

scalaVersion := "2.13.1"

lazy val catsVersion = "2.1.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion withSources(),
  "org.typelevel" %% "cats-laws" % catsVersion withSources(),

  "dev.zio" %% "zio" % "1.0.0-RC17" withSources(),

  "org.scalaz"    %% "scalaz-core" % "7.3.0-M31" withSources(),

  "org.scalatest" %% "scalatest" % "3.1.0" % Test
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8"
)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
