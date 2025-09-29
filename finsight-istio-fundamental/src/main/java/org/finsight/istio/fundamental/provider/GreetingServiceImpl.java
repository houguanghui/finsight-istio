package org.finsight.istio.fundamental.provider;


import io.grpc.stub.StreamObserver;
import org.finsight.istio.fundamental.GreeterServiceGrpc;
import org.finsight.istio.fundamental.HelloReply;
import org.finsight.istio.fundamental.HelloRequest;

/**
 *
 * @author 侯光辉
 * @email houguanghui@mail.com
 * @since 2025/9/29
 */
public class GreetingServiceImpl extends GreeterServiceGrpc.GreeterServiceImplBase {
    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
