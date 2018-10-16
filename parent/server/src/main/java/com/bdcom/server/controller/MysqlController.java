package com.bdcom.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdcom.server.lucene.SearchMethod;

@RestController
@RequestMapping(value="/mysql")
public class MysqlController extends SearchMethod{
	Map<List<Document>, Query> map = null;
	List<String> strList = null;
	List<Document> docList = null;
	
	@RequestMapping(value="/suggestion")
	@ResponseBody
	public List<String> getSuggestion(String context){
		
		try {
			map = searchByPrefixQuery(new Term("keyword", context));
			if(null != map){
				for(List<Document> list:map.keySet()){
					docList = list;
					break;
				}
				strList = new ArrayList<String>();
				for(Document doc:docList){
					if(null == doc) return null;
					String[] temp = doc.get("keyword").split(context);
					String text = "";
					if(doc.get("keyword").equals(context.trim())){
						text = "<font style='font-weight:bold;'>" + context + "</font>";
					}else{
						text = "<font style='font-weight:bold;'>" + context + "</font>" +temp[1];
					}
					
					strList.add(text);
				}
			}
		} catch (IOException e) {
			strList = null;
			e.printStackTrace();
		}
		return strList;
	}
}
