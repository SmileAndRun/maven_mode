package com.bdcom.server.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSONObject;


public class FileWriterUtils {

	private static String pathMa= "model/model1/src/main/java/com/example/demo/mapper/";
	private static String pathS= "model/model1/src/main/java/com/example/demo/service/";
	private static String pathMo= "model/model1/src/main/java/com/example/demo/model/";
	private static String pathSI= "model/model1/src/main/java/com/example/demo/service/impl/";
	private static String pathSM= "model/model1/src/main/resources/mapper/";
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
		File resource = new ClassPathResource("type.json").getFile();
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
}
