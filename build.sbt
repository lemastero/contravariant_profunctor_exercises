name := "contravariant_profunctor_exercises"

version := "0.0.1"

scalaVersion := "2.13.1"

lazy val catsVersion = "2.1.1"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion withSources(),
  "org.typelevel" %% "cats-laws" % catsVersion withSources(),

  "io.monix" %% "monix" % "3.1.0" withSources(),

  "dev.zio" %% "zio" % "1.0.0-RC18-1" withSources(),

  "org.scalaz"    %% "scalaz-core" % "7.3.0-M32" withSources(),

  "org.scalatest" %% "scalatest" % "3.1.1" % Test
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8"
)

wartremoverErrors ++= Warts.allBut(Wart.NonUnitStatements)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

addCommandAlias(name = "ca", "compile;test:compile")
addCommandAlias(name = "t", "test")
addCommandAlias("cat", "ca;t")
addCommandAlias("ncat", "~cat")
