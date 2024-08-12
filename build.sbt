lazy val root = (project in file("."))
  .settings(
    name := "Music API",
    version := "0.1",
    scalaVersion := "2.13.14",
    libraryDependencies ++= Seq(
      "org.playframework" %% "play-json" % "3.1.0-M1",
      "org.scala-lang" %% "toolkit" % "0.1.7",
      "com.amazonaws" % "aws-lambda-java-core" % "1.2.1",
      "com.amazonaws" % "aws-lambda-java-events" % "3.11.0",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.3",
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.scalamock" %% "scalamock" % "5.2.0" % Test,
      "org.scalatest" %% "scalatest" % "3.2.15" % Test
    ),
    // Assembly settings
    assembly / assemblyOption := (assembly / assemblyOption).value.copy(includeScala = true),
    assembly / mainClass := Some("com.sush.musicapi.LambdaHandler"),
    test in assembly := {}, 
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", "versions", "9", "module-info.class") => MergeStrategy.discard
      case PathList("META-INF", _@ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    }
  )
