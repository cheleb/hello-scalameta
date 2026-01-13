// *****************************************************************************
// Build settings
// *****************************************************************************

inThisBuild(
  Seq(
    organization := "io.metabookmarks",
    organizationName := "Olivier NOUGUIER",
    startYear := Some(2021),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalaVersion := "3.8.0",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-encoding",
      "UTF-8"
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    scalafmtOnCompile := true,
    dynverSeparator := "_", // the default `+` is not compatible with docker tags
  )
)

// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `hello-scalameta` =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin)
    .settings(commonSettings)
    .settings(
      libraryDependencies ++= Seq(
        "org.scalameta"        %% "scalameta" % "4.14.4",
        library.munit           % Test,
        library.munitScalaCheck % Test,
      ),
    )

// *****************************************************************************
// Project settings
// *****************************************************************************

lazy val commonSettings =
  Seq(
    // Also (automatically) format build definition together with sources
    Compile / scalafmt := {
      val _ = (Compile / scalafmtSbt).value
      (Compile / scalafmt).value
    },
  )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val munit = "0.7.29"
    }
    val munit           = "org.scalameta" %% "munit"            % Version.munit
    val munitScalaCheck = "org.scalameta" %% "munit-scalacheck" % Version.munit
  }
