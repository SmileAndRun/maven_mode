package com.bdcom.hws.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@RestController
@RequestMapping(value="/i18n")
public class InternationalController {

	@RequestMapping(value="/selectLanguage")
	public String selectLanguage(String language,HttpServletRequest request){
		Locale locale = null;
		if(language.equals("ENGLISH")){
			locale = new Locale("en", "US");
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
			request.getSession().setAttribute("lan", "en_US");
		}else{
			locale = new Locale("zh", "CN");
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
			request.getSession().setAttribute("lan", "zh_CN");
		}
		return "true";
	}
	
}
