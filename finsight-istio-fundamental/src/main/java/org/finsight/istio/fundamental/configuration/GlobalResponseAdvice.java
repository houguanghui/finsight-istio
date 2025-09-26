package org.finsight.istio.fundamental.configuration;

import org.finsight.istio.common.web.advice.FinsightResponseAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "org.finsight.sca.fundamental.controller")
public class GlobalResponseAdvice extends FinsightResponseAdvice {
}
