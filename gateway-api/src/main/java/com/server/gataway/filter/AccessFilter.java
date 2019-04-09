package com.server.gataway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AccessFilter extends ZuulFilter {

	private static Logger log= LoggerFactory.getLogger(AccessFilter.class);
	
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("send {} request to {}", request.getMethod(),
		request.getRequestURL().toString());
		Object accessToken = request.getParameter("accessToken");
		if(accessToken == null){
			log.warn("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}
		log.info("access token ok");
		return null;
	}

	/**
	 * 过滤器是否启用
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 当存在多个过滤器的时候指定其执行顺序
	 * @return
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 *  pre：可以在请求被路由之前调用
	 * 	route：在路由请求时候被调用
	 * 	post：在route和error过滤器之后被调用
	 * 	error：处理请求时发生错误时被调用
	 * @return
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
