package org.finsight.istio.fundamental.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 *
 * @author 侯光辉
 * @email houguanghui@mail.com
 * @since 2025/10/1
 */
@ConfigurationProperties(prefix = "grpc.server")
public record GrpcServerProperties(
        /*
         * gRPC 服务器端口
         */
        @DefaultValue("50051")
        int port,

        /*
          是否启用 gRPC 服务器
         */
        @DefaultValue("true")
        boolean enabled
) {}
