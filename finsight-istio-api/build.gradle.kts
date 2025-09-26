plugins {
    `java-library`
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "org.finsight.istio.api"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-api"

dependencies {
    compileOnly(platform(project(":finsight-istio-platform")))
    compileOnly("org.projectlombok:lombok")
    annotationProcessor(libs.lombok)
}
