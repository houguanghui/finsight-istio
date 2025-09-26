package org.finsight.istio.fundamental.consumer;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author 侯光辉
 * @email houguanghui@mail.com
 * @since 2025/9/26
 */
@Service
@AllArgsConstructor
public class MyRestService {

    private final RestTemplate restTemplate;

    public String callOtherService(HttpHeaders originalRequestHeaders) {
        // 创建新的请求头
        HttpHeaders headers = new HttpHeaders();

        // 正确的方式：从 HttpHeaders 中获取值
        String requestId = originalRequestHeaders.getFirst("x-request-id");
        String traceId = originalRequestHeaders.getFirst("x-b3-traceid");
        String spanId = originalRequestHeaders.getFirst("x-b3-spanid");

        if (requestId != null) {
            headers.set("x-request-id", requestId);
        }
        if (traceId != null) {
            headers.set("x-b3-traceid", traceId);
        }
        if (spanId != null) {
            headers.set("x-b3-spanid", spanId);
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://target-service/api/data",
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}
