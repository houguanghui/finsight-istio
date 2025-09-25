plugins {
    `java-library`
}

group = "org.finsight.istio"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

//plugins {
//    `java-gradle-plugin`
//}
//
//gradlePlugin {
//    plugins {
//        create("myPlugin") {
//            id = "org.example.myplugin"
//            implementationClass = "org.example.MyPlugin"
//        }
//    }
//}