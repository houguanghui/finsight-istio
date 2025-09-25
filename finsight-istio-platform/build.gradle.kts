plugins {
    `java-platform`
    `maven-publish`
    `version-catalog`
}

group = "org.finsight.istio.platform"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-platform"

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("com.fasterxml.jackson:jackson-bom:2.9.8"))
    constraints {
        api("commons-httpclient:commons-httpclient:3.1")
        runtime("org.postgresql:postgresql:42.2.5")
    }
}

catalog {
    versionCatalog {
        library("my-lib", "com.mycompany:mylib:1.2")
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
                    connection = "scm:git:git://example.com/my-library.git"
                    developerConnection = "scm:git:ssh://git@github.com:houguanghui/finsight-istio.git"
                    url = "https://houguanghui.github.io/"
                }
            }
        }
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
        }
    }
}