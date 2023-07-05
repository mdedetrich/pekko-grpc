// Can be removed when we move to 2.12.14
// https://github.com/akka/akka-grpc/pull/1279
scalaVersion := "2.12.18"

// TODO remove these resolvers when we start using released Pekko jars
resolvers += Resolver.ApacheMavenSnapshotsRepo
resolvers += "apache-staging".at("https://repository.apache.org/content/groups/staging/")

enablePlugins(PekkoGrpcPlugin)

Compile / pekkoGrpcGeneratedSources := Seq(PekkoGrpc.Server)

//#test
Test / pekkoGrpcGeneratedSources := Seq(PekkoGrpc.Client)
Test / PB.protoSources ++= (Compile / PB.protoSources).value
//#test

//#it
configs(IntegrationTest)
Defaults.itSettings
PekkoGrpcPlugin.configSettings(IntegrationTest)

IntegrationTest / pekkoGrpcGeneratedLanguages := Seq(PekkoGrpc.Java)
IntegrationTest / PB.protoSources ++= (Compile / PB.protoSources).value
//#it
