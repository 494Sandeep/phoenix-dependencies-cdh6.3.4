ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.11"


val Tookitaki =
  "Tookitaki".at(
    "http://tookitaki-artifacts.tookitaki.com/artifactory/maven-local"
  ).withAllowInsecureProtocol(true)

val TookitakiRelease =
  "TookitakiRelease".at(
    "http://tookitaki-artifacts.tookitaki.com/artifactory/tookitaki-releases"
  ).withAllowInsecureProtocol(true)

val CdhResolver =
  "Cloudera Maven Repository".at("https://repository.cloudera.com/artifactory/cloudera-repos")

val RedHatGA              = "Redhat GA".at("https://maven.repository.redhat.com/ga/")
val TypesafeReleases      = Classpaths.typesafeReleases

val allResolvers = Seq(Tookitaki,
  TookitakiRelease,
  TypesafeReleases,
  RedHatGA,
  CdhResolver)

lazy val root = (project in file("."))
  .settings(
    name := "phoenix-dependencies-cdh6.3.4",
    credentials += Credentials(Path.userHome / ".ivy2" / "credentials"),
    resolvers ++= allResolvers,
    libraryDependencies ++= Seq(
      "org.scalikejdbc"       %% "scalikejdbc"        % "3.4.0",
      "com.h2database"         % "h2"                % "1.4.200",
      "ch.qos.logback"         % "logback-classic"   % "1.2.3",
      "org.apache.hbase"       % "hbase-common"       % "2.1.0-cdh6.2.1",
      "org.apache.hbase"       % "hbase-client"       % "2.1.0-cdh6.2.1",
      "org.apache.phoenix"     % "phoenix"            % "5.0.0-cdh6.2.1-client",
      "com.fasterxml.woodstox" % "woodstox-core"      % "5.0.3",
      "org.apache.hadoop"	     % "hadoop-common"	    % "3.0.0-cdh6.2.1",
      "org.apache.hbase"%	"hbase-protocol"%	"2.1.0-cdh6.2.1",
      "org.apache.hadoop"	% "hadoop-mapreduce-client-common" %	"3.0.0-cdh6.2.1"
    ),
    excludeDependencies ++= Seq(
      ExclusionRule("org.slf4j", "slf4j-log4j12"),
      ExclusionRule("org.apache", "hbase.thirdparty"),
      ExclusionRule("org.apache.hbase", "thirdparty"),
      ExclusionRule("org.apache.hbase.thirdparty", "hbase-thirdparty"),
      ExclusionRule("org.apache.hbase.thirdparty", "hbase-shaded-netty"),
      ExclusionRule("org.apache.hbase.thirdparty", "hbase-shaded-miscellaneous"),
      ExclusionRule("org.apache.hbase.thirdparty", "hbase-shaded-protobuf"),
      ExclusionRule("org.apache.hbase", "hbase-client"),
      ExclusionRule("org.apache.hbase", "hbase-common"),
      ExclusionRule("org.apache.commons" ,"commons-math3")
    ),
  )