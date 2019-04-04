package com.server.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages={"com.server.data.mapper"})//添加对mapper层的扫描 
public class DataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}

}
