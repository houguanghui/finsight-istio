package org.finsight.istio.fundamental;

import org.finsight.istio.fundamental.consumer.GreeterConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "org.finsight.istio.fundamental.properties")
public class FinsightIstioFundamentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinsightIstioFundamentalApplication.class, args);
    }

    @Autowired
    public GreeterConsumer greeterConsumer;

    @GetMapping("grpcHello")
    public String grpcHello() {
        return "fundamental-v1:".concat(greeterConsumer.greet("grpc"));
    }
}
