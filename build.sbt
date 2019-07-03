lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "test.test",
      scalaVersion := "2.12.7"
    )),
    name := "scala-coding-example"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
  ,"org.scalacheck" %% "scalacheck" % "1.14.0" % Test
)
