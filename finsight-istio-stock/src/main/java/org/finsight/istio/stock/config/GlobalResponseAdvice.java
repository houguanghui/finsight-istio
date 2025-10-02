package org.finsight.istio.stock.config;

import org.finsight.istio.common.web.advice.FinsightResponseAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "org.finsight.istio.stock.controller")
public class GlobalResponseAdvice extends FinsightResponseAdvice {
}
