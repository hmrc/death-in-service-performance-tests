lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .settings(
      name := "inheritance-tax-on-pensions-performance-tests",
      version := "0.1.0-SNAPSHOT",
      scalaVersion := "3.3.7",
      scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:postfixOps"),
      Test / testOptions := Seq.empty,
      libraryDependencies ++= Dependencies.test,
      Gatling / javaOptions ++= Seq(
          "--add-opens=java.base/java.lang=ALL-UNNAMED",
          "--add-opens=java.base/java.io=ALL-UNNAMED"
      )
  )