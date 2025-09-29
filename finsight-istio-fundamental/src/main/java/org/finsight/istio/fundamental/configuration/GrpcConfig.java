package org.finsight.istio.fundamental.configuration;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import org.finsight.istio.common.grpc.SimpleGrpcServer;
import org.finsight.istio.fundamental.provider.GreetingServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author 侯光辉
 * @email houguanghui@mail.com
 * @since 2025/9/29
 */
@Configuration
public class GrpcConfig {

    @Bean("grpcExecutor")
    public Executor customExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("GrpcExecutor-Thread-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    public SimpleGrpcServer server(@Qualifier("grpcExecutor") Executor executor) throws IOException {
        SimpleGrpcServer simpleGrpcServer = new SimpleGrpcServer(50051, List.of(new GreetingServiceImpl()),executor);
        simpleGrpcServer.start();
        return simpleGrpcServer;
    }

    @Bean
    public ManagedChannel channel() {
        return  Grpc.newChannelBuilder("localhost:50051", InsecureChannelCredentials.create())
                .build();
    }
}
