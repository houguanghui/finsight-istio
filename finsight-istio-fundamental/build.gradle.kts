plugins {
    `java-library`
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

group = "org.finsight.istio.fundamental"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-fundamental"

dependencies {
    
}