package com.dss.CLIENTAPP.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="dss3-login")
public interface FeignProxy {
    @GetMapping("/register/instance")
    public String getServiceInstance();

}
