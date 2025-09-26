package org.finsight.istio.plugin.extensions

/**
 *
 * @author 侯光辉
 * @since 2025/9/27
 * @email houguanghui@mail.com
 */
open class FinsightExtension {
    var version: String? = null
    var dockerRegistry: String = "houguanghui"
    var baseImage: String? = null
    var jvmArgs: List<String>? = null
}