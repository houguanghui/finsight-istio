plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    id("com.gradle.plugin-publish") version "1.2.0"
}

group = "org.finsight.istio.plugin"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-plugin"

dependencies {
    implementation(gradleApi())
    implementation("com.google.cloud.tools:jib-gradle-plugin:3.4.4")
//    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.1.0")
}

gradlePlugin {
    plugins {
        create("finsightPlugin") {
            id = "org.finsight.istio.plugin"
            implementationClass = "org.finsight.istio.plugin.FinsightIstioPlugin"
            displayName = "Finsight Istio Plugin"
            description = "A custom plugin for Finsight Istio microservices"
            version = "0.0.1-SNAPSHOT"
        }
    }
}


