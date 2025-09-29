package org.finsight.istio.plugin

import com.google.cloud.tools.jib.gradle.JibExtension
import org.finsight.istio.plugin.extensions.FinsightExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * @author 侯光辉
 * @since 2025/9/27
 * @email houguanghui@mail.com
 */
class FinsightIstioPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.childProjects.forEach { (_, subproject) ->
            subproject.pluginManager.withPlugin("org.springframework.boot") {
                val extension = project.extensions.create("finsightIstioPlugin", FinsightExtension::class.java)
                configureSubproject(subproject, extension)
            }
        }
    }

    private fun configureSubproject(project: Project, extension: FinsightExtension) {
        // 配置版本
        project.version = extension.version ?: "0.0.1-SNAPSHOT"
        // 配置Docker
        configureDocker(project, extension)
    }

    private fun configureDocker(project: Project, extension: FinsightExtension) {
        project.plugins.apply("com.google.cloud.tools.jib")
        project.extensions.configure<JibExtension>("jib") {
            from {
                image = "eclipse-temurin:21-jre"
            }
            to {
                image = "${extension.dockerRegistry}/${project.name}:${project.version}"
            }
            container {
                ports = listOf("8080")
                jvmFlags = extension.jvmArgs ?: listOf("-Xmx512m")
            }
//            pluginExtensions {
//                pluginExtension {
//                    implementation = "org.finsight.istio.plugin.extensions.DockerExtensionPlugin"
//                }
//            }
        }
    }
}

