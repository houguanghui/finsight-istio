
plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
//    id("com.google.cloud.tools.jib").version("3.4.5")
}

group = "org.finsight.istio.fundamental"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-fundamental"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

springBoot {
    mainClass.set("org.finsight.istio.fundamental.FinsightIstioFundamentalApplication.class")
}

dependencies {
    compileOnly(platform(project(":finsight-istio-platform")))
    implementation(project(":finsight-istio-api"))
    implementation(project(":finsight-istio-common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")


    runtimeOnly("io.grpc:grpc-netty-shaded:1.75.0")
    implementation("io.grpc:grpc-protobuf:1.75.0")
    implementation("io.grpc:grpc-stub:1.75.0")
    implementation("com.google.protobuf:protobuf-java-util:3.25.8")
    implementation("io.grpc:grpc-services:1.75.0")

    runtimeOnly("org.postgresql:postgresql")

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(libs.mapstruct)
    annotationProcessor(libs.bundles.mapstruct.annotation)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
//    Agent 指定
    classpath.find { it.name.contains("byte-buddy-agent") }?.let {
        jvmArgs = listOf(
            "-javaagent:${it.absolutePath}" // 静态添加 ByteBuddy Agent
        )
    }
    useJUnitPlatform()
}
