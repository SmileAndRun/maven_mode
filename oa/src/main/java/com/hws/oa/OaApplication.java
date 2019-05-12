package com.hws.oa;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hws.oa.core.LoadConf;

@SpringBootApplication
public class OaApplication {

	@Value("${system.set}")
	private  boolean systemSet;
	
	public static void main(String[] args) {
		SpringApplication.run(OaApplication.class, args);
		try {
			LoadConf.load(true);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	
	
}
