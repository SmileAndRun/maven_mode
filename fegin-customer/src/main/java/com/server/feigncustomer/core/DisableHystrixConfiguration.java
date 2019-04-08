package com.server.feigncustomer.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;
/**
 * 覆盖默认熔断机制
 * 使用方法：
 * @FeignClient(name="XX", configuration = DisableHystrixConfiguration.class)
 * @author hws
 *
 */
/*@Configuration
public class DisableHystrixConfiguration {

	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}*/
