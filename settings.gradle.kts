rootProject.name = "finsight-istio"

include("finsight-istio-catalog")
include("finsight-istio-platform")
include("finsight-istio-api")
include("finsight-istio-common")
include("finsight-istio-fundamental")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        mavenLocal()
        mavenCentral()
    }
}