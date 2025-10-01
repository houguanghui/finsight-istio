package org.finsight.istio.fundamental.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 *
 * @author 侯光辉
 * @email houguanghui@mail.com
 * @since 2025/10/1
 */
@ConfigurationProperties(prefix = "grpc.client")
public record GrpcClientProperties(
        /*
         * 要连接的 gRPC 服务器地址
         */
        @DefaultValue("localhost:50051")
        String serverAddress,

        /*
          是否启用 gRPC 服务器
         */
        @DefaultValue("true")
        boolean enabled
) {}