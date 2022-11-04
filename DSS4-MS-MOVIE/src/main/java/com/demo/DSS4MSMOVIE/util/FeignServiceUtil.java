package com.demo.DSS5MSACTOR.util;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="feign", url="http://localhost:9006/actor")
public interface FeignServiceUtil {
}
