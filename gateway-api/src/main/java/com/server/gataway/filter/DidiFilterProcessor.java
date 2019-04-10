package com.server.gataway.filter;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class DidiFilterProcessor extends FilterProcessor {
	private static Logger log= LoggerFactory.getLogger(DidiFilterProcessor.class);
	
	@Override
	public Object processZuulFilter(ZuulFilter filter)throws ZuulException{
		try {
			return super.processZuulFilter(filter);
		} catch (ZuulException e) {
			
			//捕获因超时产生的ZuulException异常
			//如果不作处理会进入SendErrorFilter进行处理，会打印大量错误日志
			log.error("DidiFilterProcessor.processZuulFilter 发生异常 : {}",e.getMessage());
			
			RequestContext ctx = RequestContext.getCurrentContext();
			ctx.set("error.filter", filter);
			
			HttpServletRequest request = ctx.getRequest();
			request.setAttribute("javax.servlet.error.status_code", e.nStatusCode);
			request.setAttribute("javax.servlet.error.exception", e.getCause());

			if (StringUtils.hasText(e.getMessage())) {
				request.setAttribute("javax.servlet.error.message", e.getMessage());
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
			if (dispatcher != null) {
				ctx.set("sendErrorFilter.ran", true);
				if (!ctx.getResponse().isCommitted()) {
					ctx.setResponseStatusCode(e.nStatusCode);
					try {
						dispatcher.forward(request, ctx.getResponse());
					} catch (ServletException e1) {
						log.error("DidiFilterProcessor.processZuulFilter 发生ServletException异常 : {}",e1.getMessage());
					} catch (IOException e1) {
						log.error("DidiFilterProcessor.processZuulFilter 发生IOException异常 : {}",e1.getMessage());
					}
				}
			}
			
			throw e;
		}
	}
}