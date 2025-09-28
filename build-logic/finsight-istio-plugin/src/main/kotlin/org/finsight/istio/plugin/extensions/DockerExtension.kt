package org.finsight.istio.plugin.extensions

import com.google.cloud.tools.jib.api.buildplan.ContainerBuildPlan
import com.google.cloud.tools.jib.gradle.extension.GradleData
import com.google.cloud.tools.jib.gradle.extension.JibGradlePluginExtension
import com.google.cloud.tools.jib.plugins.extension.ExtensionLogger
import org.gradle.api.Project
import java.util.Optional

/**
 *
 * @author 侯光辉
 * @since 2025/9/27
 * @email houguanghui@mail.com
 */
class DockerExtension: JibGradlePluginExtension<Void> {
    override fun getExtraConfigType(): Optional<Class<Void>> {
        return Optional.empty<Class<Void>>()
    }

    override fun extendContainerBuildPlan(
        buildPlan: ContainerBuildPlan,
        properties: Map<String, String>,
        extraConfig: Optional<Void>,
        gradleData: GradleData,
        logger: ExtensionLogger
    ): ContainerBuildPlan {
        // 1. 记录日志
        logger.log(ExtensionLogger.LogLevel.INFO, "[Finsight] 开始扩展Jib构建计划")

        // 2. 通过gradleData获取Gradle项目信息
        val project: Project = gradleData.project
        logger.log(ExtensionLogger.LogLevel.DEBUG, "[Finsight] 处理项目: ${project.name}")

        // 3. 从properties中读取用户配置（在build.gradle.kts中传入）
        val baseImage = properties.getOrDefault("baseImage", "eclipse-temurin:21-jre")
        val jvmFlags = properties.getOrDefault("jvmFlags", "-Xmx512m").split(",")

        // 4. 开始修改构建计划
        val planBuilder = buildPlan.toBuilder()

        // 示例1: 修改基础镜像
        planBuilder.setBaseImage(baseImage)

        // 示例2: 设置环境变量
        val newEnvironment = mutableMapOf<String, String>()
        newEnvironment.putAll(buildPlan.environment)
        newEnvironment["APP_NAME"] = project.name
        newEnvironment["BUILD_TIME"] = java.time.Instant.now().toString()
        planBuilder.setEnvironment(newEnvironment)

        // 示例3: 设置JVM参数
        planBuilder.setEntrypoint(
            listOf(
                "java",
                *jvmFlags.toTypedArray(), // 展开用户配置的JVM参数
                "-jar",
                "/app.jar"
            )
        )

        // 5. 记录完成日志并返回新的构建计划
        logger.log(ExtensionLogger.LogLevel.INFO, "[Finsight] Jib构建计划扩展完成")
        return planBuilder.build()
    }


}