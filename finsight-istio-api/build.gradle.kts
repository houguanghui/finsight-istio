import com.google.protobuf.gradle.id

plugins {
    `java-library`
    id("com.google.protobuf").version("0.9.5")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "org.finsight.istio.api"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-api"

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.5"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.75.0"
        }
        generateProtoTasks {
            all().forEach {
                it.plugins {
                    id("grpc")
                }
            }
        }
    }
}

dependencies {
    implementation("io.grpc:grpc-protobuf:1.75.0")
    implementation("io.grpc:grpc-stub:1.75.0")
//    compileOnly("jakarta.annotation:jakarta.annotation-api:1.3.5")

    compileOnly(platform(project(":finsight-istio-platform")))
    compileOnly("org.projectlombok:lombok")
    annotationProcessor(libs.lombok)
}
