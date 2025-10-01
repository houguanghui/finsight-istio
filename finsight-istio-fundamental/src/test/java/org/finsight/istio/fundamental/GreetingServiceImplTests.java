package org.finsight.istio.fundamental;

import org.finsight.istio.fundamental.consumer.GreeterConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author 侯光辉
 * @email houguanghui@mail.com
 * @since 2025/9/29
 */
@SpringBootTest
public class GreetingServiceImplTests {

    @Autowired
    private GreeterConsumer greeterConsumer;

    @Test
    void testSayHello() {
        System.out.println(greeterConsumer.greet("test"));
    }

}
