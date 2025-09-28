
plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "2.1.21"
    `maven-publish`
//    `kotlin-dsl`
//    id("com.google.cloud.tools.jib").version("3.4.5") apply false
}
print("finsight-istio-plugin: ${project.version}")
group = "org.finsight.istio.plugin"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-plugin"


dependencies {
    implementation("com.google.cloud.tools:jib-gradle-plugin-extension-api:0.4.0")
}

gradlePlugin {
    plugins {
        create("finsightIstioPlugin") {
            id = "org.finsight.istio.plugin"
            displayName = "Finsight-istio plugin"
            implementationClass = "org.finsight.istio.plugin.FinsightIstioPlugin"
            description = "A custom plugin for Finsight Istio microservices"
            version = "0.0.1-SNAPSHOT"
        }
    }
}


