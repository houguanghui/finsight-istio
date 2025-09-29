plugins {
    `java-library`
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "org.finsight.istio.common"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-common"

dependencies {
    compileOnly(platform(project(":finsight-istio-platform")))
    compileOnly(project(":finsight-istio-api"))

    compileOnly("io.grpc:grpc-stub:1.75.0")

    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor(libs.lombok)
}