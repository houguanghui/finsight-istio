package org.finsight.istio.plugin.extensions

/**
 *
 * @author 侯光辉
 * @since 2025/9/27
 * @email houguanghui@mail.com
 */
open class FinsightExtension {
    var version: String? = "1.0.0"
    var dockerRegistry: String = "localhost:5000"
//    var dockerRegistry: String = "registry.cn-hangzhou.aliyuncs.com/houguanghui"
    var baseImage: String? = null
    var jvmArgs: List<String>? = null
}