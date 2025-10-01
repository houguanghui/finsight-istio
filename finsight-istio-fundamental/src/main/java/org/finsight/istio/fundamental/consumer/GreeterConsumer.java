package org.finsight.istio.fundamental.consumer;

import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import org.finsight.istio.fundamental.GreeterServiceGrpc;
import org.finsight.istio.fundamental.HelloReply;
import org.finsight.istio.fundamental.HelloRequest;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 侯光辉
 * @email houguanghui@mail.com
 * @since 2025/9/29
 */
@Service
public class GreeterConsumer {
    private static final Logger logger = Logger.getLogger(GreeterConsumer.class.getName());

    private final GreeterServiceGrpc.GreeterServiceBlockingStub blockingStub;

    public GreeterConsumer(Channel channel) {
        this.blockingStub = GreeterServiceGrpc.newBlockingStub(channel);
    }

    public String greet(String name) {
        logger.info("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);

        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return "RPC failed: " + e.getStatus().getDescription();
        }
        return response.getMessage();
    }
}
