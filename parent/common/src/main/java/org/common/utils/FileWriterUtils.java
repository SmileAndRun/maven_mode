package org.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;


public class FileWriterUtils {
    private static String pathJava = System.getProperty("user.dir")+"/model/model1/src/main/java/com/example/demo";
    private static String pathC = System.getProperty("user.dir")+"/model/model1/src/main/java/com/example/demo/controller";
    private static String pathResources = System.getProperty("user.dir")+"/model/model1/src/main/resources";
	private static String pathMa= System.getProperty("user.dir")+"/model/model1/src/main/java/com/example/demo/mapper/";
	private static String pathS= System.getProperty("user.dir")+"/model/model1/src/main/java/com/example/demo/service/";
	private static String pathMo= System.getProperty("user.dir")+"/model/model1/src/main/java/com/example/demo/model/";
	private static String pathSI= System.getProperty("user.dir")+"/model/model1/src/main/java/com/example/demo/service/impl/";
	private static String pathSM= System.getProperty("user.dir")+"/model/model1/src/main/resources/mapper/";
	public static void writerData(String tableName,String[] columnNames,String[] types) throws IOException{
		
		File fileMa = new File(pathMa + getFirstUp(tableName) + "Mapper.java");
		File fileS = new File(pathS + getFirstUp(tableName) + "Service.java");
		File fileSI = new File(pathSI + getFirstUp(tableName) + "ServiceImp.java");
		File fileMo = new File(pathMo + getFirstUp(tableName) + ".java");
		File fileMaX = new File(pathSM + getFirstUp(tableName) + "Mapper.xml");
		System.out.println(fileMa);
		if(!fileMa.exists())fileMa.createNewFile();
		if(!fileS.exists()) fileS.createNewFile();
		if(!fileSI.exists()) fileSI.createNewFile();
		if(!fileMaX.exists())fileMaX.createNewFile();
		if(!fileMo.exists())fileMo.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileMo));
		writer.write(setDataOfModel(tableName,columnNames,types));
		writer.flush();
		writer.close();
		writer = new BufferedWriter(new FileWriter(fileMa));
		writer.write(setDataOfMapper(tableName));
		writer.flush();
		writer.close();
		writer = new BufferedWriter(new FileWriter(fileMaX));
		writer.write(setDataOfMapperXml(tableName,columnNames));
		writer.flush();
		writer.close();
		writer = new BufferedWriter(new FileWriter(fileS));
		writer.write(setDataOfService(tableName,columnNames,types));
		writer.flush();
		writer.close();
		writer = new BufferedWriter(new FileWriter(fileSI));
		writer.write(setDataOfServiceImpl(tableName));
		writer.flush();
		writer.close();
	}
	public static String setDataOfModel(String tableName,String[] columnNames,String[] types)throws IOException{
		String model = "public class " + getFirstUp(tableName) +"{ \n";
		String column = "";
		String method = "";
		for(int i=0;i<columnNames.length;i++){
			column += "\tprivate " + getType(types[i]) +" " + columnNames[i]+ ";\n";
			method += "\tpublic "+getType(types[i]) + " get" + getFirstUp(columnNames[i]) +"(){\n"
					+ "\t\treturn " + columnNames[i] +"\n}\n"
							+ "\tpublic void set" +getFirstUp(columnNames[i])+"("+ columnNames[i] +"){\n"
									+ "\t\tthis."+columnNames[i]+" = " +columnNames[i] +"\n}\n";
		}
		model += column + method + "}\n";
		return model;
		
	}
	public static String setDataOfService(String tableName,String[] columnNames,String[] types){
		String data = "public interface "+getFirstUp(tableName)+"Service{\n";
		String method = "\tpublic List<" +getFirstUp(tableName)+"> getAll"+getFirstUp(tableName)+"();\n"
				+ "\tpublic int insert("+getFirstUp(tableName)+" tableName);\n"
						+ "\tpublic int delete("+getFirstUp(tableName)+" tableName);\n}\n";
		data += method;
		return data;
	}
	public static String setDataOfServiceImpl(String tableName){
		String data = "public class "+getFirstUp(tableName)+"ServiceImp implements "+getFirstUp(tableName)+"Service{\n"
				+ "\t@"+getFirstUp(tableName)+"Mapper "+tableName+"Mapper;\n"
						+ "\tpublic List<" +getFirstUp(tableName)+"> getAll"+getFirstUp(tableName)+"(){\n"
								+ "\t\treturn "+tableName+"Mapper.getAll"+getFirstUp(tableName)+"();\n\t}\n}\n";
		return data;
		
	}
	public static String setDataOfMapper(String tableName){
		String data = "public interface "+getFirstUp(tableName)+"Mapper{\n";
		String method = "\tpublic List<" +getFirstUp(tableName)+"> getAll"+getFirstUp(tableName)+"();\n"
				+ "\tpublic int insert("+getFirstUp(tableName)+" tableName);\n"
						+ "\tpublic int delete("+getFirstUp(tableName)+" tableName);\n"
								+ "\tpublic int insert("+getFirstUp(tableName)+" tableName);\n}\n";
		data += method;
		return data;
		
	}
	public static String setDataOfMapperXml(String tableName,String[] columns){
		String title = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<!DOCTYPE mapperPUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">";
		String mapper = "\n<mapper namespace=\"com.example.demo.mapper."+getFirstUp(tableName)+"Mapper\">"
				+ "\n\t<select id=\"getAll"+getFirstUp(tableName)+"\"  resultType=\"com.example.demo.model."+getFirstUp(tableName)+"\">"
						+ "\n\t\tselect * from "+tableName+"\n\t</select>\n"
								+ "\t<insert id=\"insert\" parameterType=\"com.example.demo.model."+getFirstUp(tableName)+"\">"+
		"\n\t\tinsert   into t_user(";
		String insertP = "";
		String insertV = "";
		for(int i=0;i<columns.length;i++){
			insertP += columns[i]+",";
			insertV += "#{"+columns[i]+"},";
		}
		insertP = insertP.substring(0,insertP.length()-1);
		insertV = insertV.substring(0,insertV.length()-1);
		mapper += insertP +") values("+insertV+")\n\t</insert>\n</mapper>";
		
		return title+mapper;
		
	}
	public static String getType(String typeName) throws IOException{
		File resource = new File(System.getProperty("user.dir")+"/conf/type.json");
		String input = FileUtils.readFileToString(resource, "UTF-8");
		JSONObject obj = JSONObject.parseObject(input);
		return (String)obj.get(typeName.toUpperCase());
	}
	public static String getFirstUp(String data){
		if(data.length() != 1){
			String temp = data.substring(0,1).toUpperCase();
			data = data.substring(1, data.length());
			data = temp + data;
		}else{
			data = data.toUpperCase();
		}
		return data;
	}
	public static Map<String, String[]> getFileInfo(){
		File path = new File(pathJava);
		String[] dirDemo = null;
		if(path.exists()&&path.isDirectory())dirDemo = path.list();
		path = new File(pathC);
		String[] dirController = null;
		if(path.exists()&&path.isDirectory())dirController = path.list();
		String[] dirService = null;
		path = new File(pathS);
		if(path.exists()&&path.isDirectory())dirService = path.list();
		String[] dirServiceImpl = null;
		path = new File(pathSI);
		if(path.exists()&&path.isDirectory())dirServiceImpl = path.list();
		String[] dirModel = null;
		path = new File(pathMo);
		if(path.exists()&&path.isDirectory())dirModel = path.list();
		String[] dirMapper = null;
		path = new File(pathMa);
		if(path.exists()&&path.isDirectory())dirMapper = path.list();
		String[] dirResourceMapper = null;
		path = new File(pathSM);
		if(path.exists()&&path.isDirectory())dirResourceMapper = path.list();
		String[] dirReasources = null;
		path = new File(pathResources);
		if(path.exists()&&path.isDirectory())dirReasources = path.list();
		Map<String, String[]> map = new HashMap<String,String[]>();
		map.put("demo", dirDemo);
		map.put("controller", dirController);
		map.put("service", dirService);
		map.put("serviceImpl", dirServiceImpl);
		map.put("model", dirModel);
		map.put("mapper", dirMapper);
		map.put("mapperXml", dirResourceMapper);
		map.put("resources", dirReasources);
		return map;
	}
	
	public static boolean isExist(String path){
		boolean flag = false;
		File file = new File(path);
		if(!file.exists())return false;
		if(file.list().length>0) flag = true;
		return flag;
	}
}
