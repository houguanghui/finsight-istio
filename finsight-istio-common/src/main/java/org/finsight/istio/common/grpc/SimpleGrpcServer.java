package org.finsight.istio.common.grpc;

import io.grpc.*;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *
 * @author 侯光辉
 * @email houguanghui@mail.com
 * @since 2025/9/29
 */
@NoArgsConstructor
public class SimpleGrpcServer {
    private static final Logger logger = Logger.getLogger(SimpleGrpcServer.class.getName());

    public SimpleGrpcServer(int port, List<BindableService> bindableServices, Executor executor) {
        this.port = port;
        this.bindableServices = bindableServices;
    }

    private List<BindableService>  bindableServices;
    private Executor executor;

    private int port = 50051;
    private Server server;

    public void start() throws IOException {
        ServerBuilder<?> builder = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .executor(executor);
        for (BindableService service : bindableServices) {
            builder.addService(service);
        }
        server = builder.build().start();
        logger.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("*** shutting down gRPC server since JVM is shutting down");
            try {
                SimpleGrpcServer.this.stop();
            } catch (InterruptedException e) {
                if (server != null) {
                    server.shutdownNow();
                }
                e.printStackTrace(System.err);
            }
            logger.info("*** server shut down");
        }));
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
