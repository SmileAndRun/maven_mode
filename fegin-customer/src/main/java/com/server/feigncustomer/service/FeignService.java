package com.server.feigncustomer.service;


import org.springframework.cloud.openfeign.FeignClient;

import com.server.restful.api.service.DataApiService;

@FeignClient("eureka-data")
public interface FeignService extends DataApiService{


}
