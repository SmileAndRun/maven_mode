package com.server.feigncustomer.service;


import org.springframework.cloud.openfeign.FeignClient;

import com.server.feigncustomer.service.impl.FeignServiceFallback;
import com.server.restful.api.service.DataApiService;

@FeignClient(name = "eureka-data",fallback=FeignServiceFallback.class)
public interface FeignService extends DataApiService{


}
