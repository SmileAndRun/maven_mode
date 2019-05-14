package org.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
	/**获取源文件下的路径file*/
	public static File getClassPathResource(String name) throws IOException{
		File resource = new File(name);
		return resource;
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
	/**
	 * 读取jar中的文件不能以文件形式读取，只能通过字节方式读取
	 * @param path
	 * @return
	 */
	public static Properties getPropertiesForJar(String path){
		 InputStream is=ReadResourceUtils.class.getResourceAsStream(path); 
	     BufferedReader br=new BufferedReader(new InputStreamReader(is));
	     Properties properties = null;
	     try {
	    	properties = new Properties();
			properties.load(br);
		} catch (IOException e) {
			e.printStackTrace();
		}
	     return properties;
	}
}
