package com.bdcom.hws;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.bdcom.hws","com.bdcom.server","org.common.core"})
@MapperScan(basePackages={"com.bdcom.hws.mapper","com.bdcom.server.mapper"})//添加对mapper层的扫描 
public class HwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwsApplication.class, args);
	}
}
