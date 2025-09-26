package org.finsight.istio.plugin

import com.google.cloud.tools.jib.gradle.JibExtension
import org.finsight.istio.plugin.extensions.FinsightExtension
import org.finsight.istio.plugin.tasks.DeployTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

/**
 *
 * @author 侯光辉
 * @since 2025/9/27
 * @email houguanghui@mail.com
 */
class FinsightIstioPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // 创建扩展配置
        val extension = project.extensions.create("finsightIstioPlugin", FinsightExtension::class.java)

        // 配置所有子项目
        project.childProjects.forEach { (_, subproject) ->
            subproject.plugins.withId("org.springframework.boot") {
                configureSubproject(subproject, extension)
            }
        }

        // 注册自定义任务
        project.tasks.register<DeployTask>("deployTask") {
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
                image = "docker://eclipse-temurin:21-jre"
            }
            to {
                image = "${extension.dockerRegistry}/${project.name}:${project.version}"
//                auth {
//                    username = ""
//                    password = ""
//                }
            }
            container {
                ports = listOf("8080")
                jvmFlags = extension.jvmArgs ?: listOf("-Xmx512m")
            }
        }
    }
}

