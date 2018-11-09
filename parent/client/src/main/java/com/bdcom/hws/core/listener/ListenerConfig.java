package com.bdcom.hws.core.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  
public class ListenerConfig {  
    @Bean  
    public OnLineCount init(){  
        return new OnLineCount();  
    }  
}  