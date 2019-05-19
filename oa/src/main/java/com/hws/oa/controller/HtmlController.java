package com.hws.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hws.oa.core.LoadConf;

@Controller
@RequestMapping("/html")
public class HtmlController {

	@RequestMapping("/homepage")
	public ModelAndView homepage(){
		return new ModelAndView("index");
	}
	@RequestMapping("/settings")
	public ModelAndView settings(HttpServletRequest request){
		Map<Integer,Map<String,String>> map = LoadConf.getSystemMap();
        List<Map<String,String>> sets = null;;
		if(map.size()>0){
			sets = new ArrayList<>();
			
		}
		for(Integer num:map.keySet()){
			Map<String,String> temp = new HashMap<String,String>();
			temp.put("num", String.valueOf(num));
			temp.put("local", map.get(num).get("local-repo"));
			temp.put("remote", map.get(num).get("remote-repo"));
			sets.add(temp);
		}
		request.setAttribute("sets", sets);
		return new ModelAndView("settings");
	}
	
}
