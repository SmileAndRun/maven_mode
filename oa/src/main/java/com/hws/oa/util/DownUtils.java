package com.hws.oa.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.hws.oa.common.MyCommonConstants;

public class DownUtils {
	private static final int  BUFFER_SIZE = 2 * 1024;
	private static final String JARFILENAME = "target";
	private static final String ZIPSUFFIX = ".zip";
	private static byte[] buf = new byte[BUFFER_SIZE];
	public static void compress(String[] files,ZipOutputStream zos) throws FileNotFoundException, IOException{
		 
		 for(String temp : files){
			 if(new File(temp).isDirectory()){
				 File parentFile = new File(temp);
				 if(!parentFile.exists())continue;
				 List<String> list = Arrays.asList(parentFile.list());
				 if(list.contains(JARFILENAME)){
					 File sourceFile = new File(temp+File.separator+JARFILENAME);
					 for(File file :sourceFile.listFiles()){
						 if(file.isFile()){
							 zos.putNextEntry(new ZipEntry(file.getName()));
							 int len;
					         FileInputStream in = new FileInputStream(file);
					         while ((len = in.read(buf)) != -1){
					             zos.write(buf, 0, len);
					         }
					         in.close();
						 }
					 }
				 }else{
					 if(list.contains("pom.xml")){
						 compress(parentFile.list(), zos); 
					 }else{
						 continue;
					 }
				 }
			 }
		 }
    }
	public static String zipData(String[] path,String name,String targetLocation) throws FileNotFoundException, IOException{
		File tempFile = new File(targetLocation);
		//如果目录不存在则新建
		if(!tempFile.exists())tempFile.mkdirs();
		String fileName = targetLocation+File.separator+name+ZIPSUFFIX;
		File file = new File(fileName);
		String versionId ="";
		while(file.exists()){
			versionId = String.valueOf(MyCommonConstants.codeVersion.getAndIncrement());
			fileName = targetLocation+File.separator+versionId+ZIPSUFFIX;
			file = new File(fileName);
		}
		OutputStream out = new FileOutputStream(new File(fileName));
		ZipOutputStream zos = new ZipOutputStream(out);
		compress(path, zos);
		zos.close();
		return versionId;
	}
	//下载
	public static boolean downZip(OutputStream out,String versionId,String location) throws IOException{
		File file = new File(location+File.separator+versionId+".zip");
		if(!file.exists())return false;
		int len;
		DataInputStream  in = new DataInputStream( new FileInputStream(file));
        while ((len = in.read(buf)) != -1){
            out.write(buf, 0, len);
        }
        in.close();
        return true;
	} 

}
