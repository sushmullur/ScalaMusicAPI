lazy val root = (project in file("."))
  .settings(
    name := "Music API",
    version := "0.1",
    scalaVersion := "2.13.14",
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.playframework" %% "play-json" % "3.1.0-M1",
      "org.scala-lang" %% "toolkit" % "0.1.7"
    )
  )
