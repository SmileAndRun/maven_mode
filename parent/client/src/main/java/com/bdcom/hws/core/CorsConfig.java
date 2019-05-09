package com.bdcom.hws.core;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration  
public class CorsConfig implements WebMvcConfigurer {  
	private static Logger logger = Logger.getLogger(CorsConfig.class);
    
    @Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowCredentials(true)  
                .allowedMethods("*")  
                .allowedHeaders("*")  
                .maxAge(3600);  
        logger.info("*********************************跨域过滤器**************************");  
    }  
  
} 