plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
}
group = "org.finsight.istio.plugin"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-plugin"

dependencies {
//    implementation("com.google.cloud.tools.jib:com.google.cloud.tools.jib.gradle.plugin:3.4.5")
    implementation("com.google.cloud.tools.jib:com.google.cloud.tools.jib.gradle.plugin:3.4.5")
//    compileOnly("com.google.cloud.tools:jib-gradle-plugin-extension-api:0.4.0")
}

kotlin {
    jvmToolchain(21)
}

gradlePlugin {
    plugins {
        register("finsight-istio-plugin") {
            id = "org.finsight.istio.plugin"
            implementationClass = "org.finsight.istio.plugin.FinsightIstioPlugin"
            version = "0.0.1-SNAPSHOT"
            displayName = "Finsight-istio plugin"
            description = "A custom plugin for Finsight Istio microservices"
        }
    }
}

//publishing {
//    publications {
//        create<MavenPublication>("mavenJava") {
//            from(components["java"])
//            artifactId = "finsight-istio-plugin"
//            version = project.version.toString()
//        }
//    }
//}


