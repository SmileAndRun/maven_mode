package com.hws.oa.exception;

import org.apache.log4j.Logger;

public class CommonException extends Exception{
	 
	private static final long serialVersionUID = 4391247397386158310L;
	private static Logger log = Logger.getLogger(CommonException.class.getClass());
	private String errorMessage;
	
	public CommonException(String errorMessage){
		this.errorMessage = errorMessage;
	}
	 /**
	  * 常见异常方法
	  */
	public  String getErrorMessage(){
		 log.error(errorMessage);
		 return errorMessage;
	}
	
	public CommonException() {
		
	}
	

}
