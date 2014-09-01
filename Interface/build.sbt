organization := "ch.hesge.hepia"

name := "introscala"

version := "0.0.1"

scalaVersion := "2.11.2"


// Trouvé sur le site scala-lang.org
// https://wiki.scala-lang.org/display/SW/Tools+and+Libraries#ToolsandLibraries-Swing
// puis : https://github.com/benhutchison/ScalaSwingContrib
// Cliquer sur le fichier build.sbt

libraryDependencies ++= {
  val sv = scalaVersion.value
	if( sv startsWith "2.10"){
		Seq("org.scala-lang" % "scala-swing" % sv)
	}else{
		Seq("org.scala-lang.modules" %% "scala-swing" % "1.0.1",
		  "org.scala-lang.modules" %% "scala-xml" % "1.0.1")
	}
}


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.0" % "test"
)

scalacOptions ++= Seq("-deprecation", "-feature")

scalaSource in Compile <<= baseDirectory(_ / "src")

scalaSource in Test <<= baseDirectory(_ / "test")
