package com.server.gataway.filter;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			
			throw e;
		}
	}
}