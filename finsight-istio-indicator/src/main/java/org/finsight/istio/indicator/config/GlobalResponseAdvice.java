package org.finsight.istio.indicator.config;

import org.finsight.istio.common.web.advice.FinsightResponseAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "org.finsight.istio.indicator.controller")
public class GlobalResponseAdvice extends FinsightResponseAdvice {
}
