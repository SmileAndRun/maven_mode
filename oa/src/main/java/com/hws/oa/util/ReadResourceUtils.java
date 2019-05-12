package com.hws.oa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/***
 * 读取资源文件
 * @author hws
 *
 */
public class ReadResourceUtils {
	/**读取properties文件下的具体某个关键字的内容*/
	public static String getPropertiesData(String path,String alias) throws IOException{
		Properties propertie = new Properties();
		BufferedReader read = new BufferedReader(new FileReader(new File(path)));
		propertie.load(read);
		return propertie.getProperty(alias);
	}
	/**读取xml根节点下的节点*/
	public static Element getXmlRootElement(File file) throws DocumentException{
		 SAXReader reader = new SAXReader();
		 Document doc = reader.read(file);
		 return doc.getRootElement();
	}
	/**将节点解析为map*/
	public static List<Map<String, String>> getAttributeValues(List<String> attribute,Element node){
		@SuppressWarnings("unchecked")
		List<Element> elements = node.elements() ;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(Element ele:elements){
			Map<String, String> map = new HashMap<String,String>();
			for(String attr:attribute){
				map.put(attr,ele.attributeValue(attr));
			}
			list.add(map);
		}
		return list;
	}
	/**将节点解析为map*/
	public static Map<Integer,Map<String, String>> getNodes(List<String> attribute,Element node){
		@SuppressWarnings("unchecked")
		List<Element> elements = node.elements() ;
		Map<Integer,Map<String, String>> cMap = new ConcurrentHashMap<>();
		for(Element ele:elements){
			Map<String, String> map = new HashMap<String,String>();
			Integer temp = null;
			for(String attr:attribute){
				map.put(attr,ele.attributeValue(attr));
				if(attr.equals("num"))temp = Integer.parseInt(ele.attributeValue(attr));
			}
			cMap.put(temp,map);
		}
		return cMap;
	}
	
	/**
	 * 读取整个properties文件
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Properties getProperties(String path){
		BufferedReader reader = null;
		Properties properties = null;
		try {
			File file = new File(path);
			reader = new BufferedReader(new FileReader(file));
			properties = new Properties();
			properties.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
