plugins {
    `maven-publish`
    `version-catalog`
}

group = "org.finsight.istio.catalog"
version = "0.0.1-SNAPSHOT"
description = "finsight-istio-catalog"

catalog {
    versionCatalog {
        from(files("../gradle/libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
        }
    }
}