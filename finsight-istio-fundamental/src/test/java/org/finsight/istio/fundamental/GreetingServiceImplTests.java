package org.finsight.istio.fundamental;

import org.finsight.istio.fundamental.consumer.GreeterConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//http://localhost:8080/api/company-info?page=0&size=10&sortBy=id&direction=asc
//http://localhost:8080/api/statistics/pmi_A0B01_month?startTime=202210&endTime=202510
//http://localhost:8080/api/stock-monthly/code/600519.SH/year-month-range?startYearMonth=2023-01&endYearMonth=2023-12

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
