rootProject.name = "finsight-istio"

includeBuild("build-logic")
include(
    "finsight-istio-catalog"
    , "finsight-istio-platform"
    , "finsight-istio-api"
    , "finsight-istio-common"
    , "finsight-istio-fundamental"
    , "finsight-istio-indicator"
    , "finsight-istio-stock"
)

pluginManagement {
    repositories {
        mavenLocal()
        maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        mavenLocal()
        maven(url = "https://maven.aliyun.com/repository/public")
        maven(url = "https://maven.aliyun.com/repository/google")
        maven(url = "https://maven.aliyun.com/repository/central")
        maven(url = "https://repo.huaweicloud.com/repository/maven/")
        maven(url = "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
        maven(url = "https://mirrors.163.com/maven/repository/maven-public/")
        maven(url = "https://plugins.gradle.org/m2/")
        mavenCentral()
    }
}