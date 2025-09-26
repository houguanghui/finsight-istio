
plugins {
    `java-platform`
    `maven-publish`
}

group = "org.finsight.istio.platform"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-platform"

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform(libs.spring.boot.dependencies))
    constraints {
    }
}

publishing {
    publications {
        create<MavenPublication>("myPlatform") {
            from(components["javaPlatform"])
            pom {
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "https://apache.ac.cn/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "houguanghui"
                        name = "houguanghui"
                        email = "houguanghui@mail.com"
                    }
                }
                scm {
                    connection = "scm:git:git://git@github.com:houguanghui/finsight-istio.git"
                    developerConnection = "scm:git:ssh://git@github.com:houguanghui/finsight-istio.git"
                    url = "https://houguanghui.github.io/"
                }
            }
        }
    }
}