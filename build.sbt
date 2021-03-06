name := "httpize"

version := "0.2.0"

organization := "org.purang.net"

scalaVersion := "2.11.8"

val http4sVersion = "0.16.6" //"0.15.16" //"0.14.11"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-core" % http4sVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-argonaut" % http4sVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.7"
  )

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"
)

scalacOptions ++= Seq("-encoding",
  "UTF-8",
  "-deprecation", "-feature", "-unchecked", "-language:_")
  //"-optimize",
  //"-Yinline", "-Yinline-warnings" , "-Ywarn-all")
//ran into this: https://issues.scala-lang.org/browse/SI-3882

Revolver.settings

cancelable := true

fork := true

logBuffered := false

//seq(bintrayPublishSettings:_*)

licenses += ("BSD", url("http://www.tldrlegal.com/license/bsd-3-clause-license-%28revised%29"))

enablePlugins(JavaServerAppPackaging)
