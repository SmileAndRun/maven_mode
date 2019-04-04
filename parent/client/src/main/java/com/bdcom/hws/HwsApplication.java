package com.bdcom.hws;


import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.bdcom.hws","com.bdcom.server","org.common.core"})
@MapperScan(basePackages={"com.bdcom.hws.mapper","com.bdcom.server.mapper"})//添加对mapper层的扫描 
public class HwsApplication {
	private static Logger log = Logger.getLogger(HwsApplication.class);
	public static void main(String[] args) {
		System.setProperty("log.base",System.getProperty("user.dir"));
		log.info("springboot初始化");
		SpringApplication.run(HwsApplication.class, args);
		log.info("springboot初始化结束");
	}
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
