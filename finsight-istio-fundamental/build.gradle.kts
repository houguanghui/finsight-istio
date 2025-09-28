plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
//    id("com.google.cloud.tools.jib").version("3.4.5")
}

//jib.to.image = "localhost:5000/my-gcp-project/my-app"
//jib.from.image = "eclipse-temurin:21-jre"
//jib.to.image = "localhost:5000/finsight-istio-fundamental:0.0.1-SNAPSHOT"
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
    implementation(project(":finsight-istio-common"))
    implementation(project(":finsight-istio-api"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("org.postgresql:postgresql")

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(libs.mapstruct)
    annotationProcessor(libs.bundles.mapstruct.annotation)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
