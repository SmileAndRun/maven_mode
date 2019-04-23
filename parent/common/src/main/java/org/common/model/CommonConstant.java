package org.common.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;


public class CommonConstant {

	public static String http = "http://";
	public static String https = "https://";
	public static String gatewayServereName = "gateway-api";
	public static String feignKey = "/api-feign";
	public static String feignUrl = "/feign";
	public static String identificationKey = "identification";
	public static String identificationValue = "hws";
	public static final String SYMBOL1 = "?";
	public static final String SYMBOL2 = "&";
	public static final String SYMBOL3 = "=";
	public static final String SYMBOL4 = "{";
	public static final String SYMBOL5 = "}";
	public static final String SYMBOL6 = "/";
	
	
	
	public static StringBuffer getCommonUrl(){
		StringBuffer sBuffer = new StringBuffer(http);
		sBuffer.append(gatewayServereName);
		sBuffer.append(feignKey);
		sBuffer.append(feignUrl);
		return sBuffer;
	}
	
	public static <T> T getDataForMap(RestTemplate restTemplate,StringBuffer url,Class<T> responseType,Map<String, Object> map){
		if(null == map)map = new HashMap<String,Object>();
		map.put(identificationKey, identificationValue);
		
		StringBuffer sBuffer = getCommonUrl();
		if(null != url&&url.indexOf(SYMBOL6) == -1)url = url.insert(0, SYMBOL6);
		sBuffer.append(url);
		//使用map需要占位符
		sBuffer.append(SYMBOL1);
		for(String  str:map.keySet()){
			sBuffer.append(str);
			sBuffer.append(SYMBOL3+SYMBOL4+str+SYMBOL5);
			sBuffer.append(SYMBOL2);
		}
		String temp = sBuffer.substring(0, sBuffer.length()-1);
		return restTemplate.getForEntity(temp, responseType, map).getBody();
	}
	//Object obj如果为实体对象如User则调用方和提供方的对象类型要一致
	public static <T> T postDataForObject(RestTemplate restTemplate,StringBuffer url,Object obj,Class<T> responseType,Map<String, Object> map){
		if(null == map)map = new HashMap<String,Object>();
		map.put(identificationKey, identificationValue);
		StringBuffer sBuffer = getCommonUrl();
		if(null != url&&url.indexOf(SYMBOL6) == -1)url = url.insert(0, SYMBOL6);
		sBuffer.append(url);
		//使用map需要占位符
		sBuffer.append(SYMBOL1);
		for(String  str:map.keySet()){
			sBuffer.append(str);
			sBuffer.append(SYMBOL3+SYMBOL4+str+SYMBOL5);
			sBuffer.append(SYMBOL2);
		}
		String temp = sBuffer.substring(0, sBuffer.length()-1);
		return restTemplate.postForObject(temp, obj, responseType,map);
	}
	
	/*public static <T> T exchangeDataForObject(RestTemplate restTemplate,StringBuffer url,Object obj,Class<T> responseType,Map<String, Object> map){
		if(null == map)map = new HashMap<String,Object>();
		map.put(identificationKey, identificationValue);
		StringBuffer sBuffer = getCommonUrl();
		if(null != url&&url.indexOf("/") == -1)url = url.insert(0, "/");
		sBuffer.append(url);
		return restTemplate.exchange(url.toString(), HttpMethod.POST, requestEntity, responseType, map);
	}*/
}
