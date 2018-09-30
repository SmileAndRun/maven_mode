package com.bdcom.hws.exception;

import org.apache.log4j.Logger;

public class CommonException extends Exception{
	 
	private static final long serialVersionUID = 4391247397386158310L;
	private static Logger log = Logger.getLogger(CommonException.class.getClass());
	 
	 /**
	  * 常见异常方法
	  */
	public  String getErrorMessage(String massage){
		 log.error(massage);
		 return massage;
	}
	public String getMessage(String massage){
		log.debug(massage);
		return massage;
	}
	public CommonException() {
		
	}
	public CommonException(String massage,boolean isError){
		if(isError){
			getErrorMessage(massage);
		}else{
			getMessage(massage);
		}
	}

}
