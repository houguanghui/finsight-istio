package org.finsight.istio.common.web.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.finsight.istio.api.model.Result;
import org.finsight.istio.common.web.exception.BusinessException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
public class FinsightResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,@Nullable
    Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().equals(Result.class);
    }

    @Override
    public Object beforeBodyWrite(Object body,@Nullable  MethodParameter returnType,@Nullable
                                  MediaType selectedContentType,@Nullable
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @Nullable ServerHttpRequest request,@Nullable  ServerHttpResponse response) {

        // 如果body已经是ApiResponse，直接返回
        if (body instanceof Result) {
            return body;
        }

        // 如果body是null，返回成功的空响应
        if (body == null) {
            return Result.success(null);
        }

        if (body instanceof String) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                log.error("JSON转换失败", e);
                return Result.error("响应转换失败");
            }
        }

        // 包装普通响应
        return Result.success(body);
    }

    // 处理业务异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return Result.error(Result.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // 处理空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleNullPointerException(NullPointerException e) {
        log.error(e.getMessage(), e);
        return Result.error(Result.INTERNAL_SERVER_ERROR, "数据不存在或为空");
    }

    // 处理自定义业务异常（如果有的话）
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return Result.error(Result.BAD_REQUEST, e.getMessage());
    }
}