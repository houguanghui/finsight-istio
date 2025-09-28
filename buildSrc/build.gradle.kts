//import org.finsight.istio.plugin.FinsightIstioPlugin
//buildscript { dependencies { classpath("org.finsight.istio.plugin:finsight-istio-plugin:0.0.1-SNAPSHOT") } }
//apply(plugin = "org.finsight.istio.plugin")

repositories {
    mavenLocal()
    maven(url = "https://maven.aliyun.com/repository/public")
    maven(url = "https://maven.aliyun.com/repository/google")
    maven(url = "https://maven.aliyun.com/repository/central")
    maven(url = "https://repo.huaweicloud.com/repository/maven/")
    maven(url = "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
    maven(url = "https://mirrors.163.com/maven/repository/maven-public/")
    mavenCentral()
}

dependencies {
//    implementation("org.finsight.istio.plugin:finsight-istio-plugin:0.0.1-SNAPSHOT")
}

subprojects {
//    plugins {
//        id("org.finsight.istio.plugin")
//    }
}
