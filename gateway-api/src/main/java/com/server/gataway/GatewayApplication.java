package com.server.gataway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

import com.netflix.zuul.FilterProcessor;
import com.server.gataway.filter.DidiFilterProcessor;
import com.server.gataway.filter.ErrorExFilter;
import com.server.gataway.filter.ErrorFilter;
import com.server.gataway.filter.PreFilter;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

	@Bean
	public PreFilter preFilter(){
		return new PreFilter();
	}
	@Bean
	public ErrorFilter errorFilter(){
		return new ErrorFilter();
	}
	@Bean
	public ErrorExFilter errorExFilter(){
		return new ErrorExFilter();
	}
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
	return new PatternServiceRouteMapper(
			"(?<name>^.+)-(?<version>v.+$)",
			"${version}/${name}");
	}
	
	@RefreshScope
	@ConfigurationProperties("zuul")
	public ZuulProperties zuulProperties() {
		return new ZuulProperties();
	}
	
	public static void main(String[] args) {
		FilterProcessor.setProcessor (new DidiFilterProcessor());
		SpringApplication.run(GatewayApplication.class, args);
	}

}
