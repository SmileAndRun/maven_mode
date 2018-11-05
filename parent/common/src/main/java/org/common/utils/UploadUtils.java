package org.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
	
	public static void  Upload(MultipartFile[] files) throws Exception{
		InputStream inputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		for(MultipartFile file:files){
			String rootPath = ResourceUtils.getURL("classpath:").getPath();
			String path = "/static/upload/img";
			String fileName = file.getOriginalFilename();
			
			inputStream = file.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			File tempFile = new File(rootPath+path,fileName);
			String[] temp = fileName.split("\\.");
			int num = 0;
			while(tempFile.exists()){
				num++;
				tempFile.renameTo(new File(rootPath+path,temp[0] + "("+num+")." + temp[1]));
			}
			OutputStream out = new FileOutputStream(tempFile); 
			bufferedOutputStream = new BufferedOutputStream(out);
			byte[] buff = new byte[1024];
			int length = 0;
			while( (length = bufferedInputStream.read(buff)) != -1){
				bufferedOutputStream.write(buff,0,length);
			}
			bufferedOutputStream.flush();
			
		}
		if(inputStream != null){
			inputStream.close();
		}
		if(bufferedOutputStream != null){
			bufferedOutputStream.close();
		}
		
		
	}
}
