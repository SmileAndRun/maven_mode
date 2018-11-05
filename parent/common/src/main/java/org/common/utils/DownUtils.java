package org.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownUtils {
	private static final int  BUFFER_SIZE = 2 * 1024;
	public static void compress(String path,ZipOutputStream zos,String name) throws FileNotFoundException, IOException{
		 byte[] buf = new byte[BUFFER_SIZE];
		 File sourceFile = new File(path);
		 if(sourceFile.isFile()){
			 // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
			 zos.putNextEntry(new ZipEntry(name));
			 // copy文件到zip输出流中
	         int len;
	         FileInputStream in = new FileInputStream(sourceFile);
	         while ((len = in.read(buf)) != -1){
	             zos.write(buf, 0, len);
	         }
	         in.close();
		 }else{
			 File[] listFiles = sourceFile.listFiles();
             if(listFiles == null || listFiles.length == 0){
                 // 空文件夹的处理
                 zos.putNextEntry(new ZipEntry(name + "/"));
                 // 没有文件，不需要文件的copy
                 zos.closeEntry();
             }else{
            	 for (File file : listFiles) {
            		// 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
	                // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
	                compress(file.getPath(), zos, name + "/" + file.getName());
            	 }
             }
		 }
    }
	public static void download(String path,ZipOutputStream zos,String name,String temp) throws FileNotFoundException, IOException{
		//OutputStream out = new FileOutputStream(new File("model/temp/model.zip"));
		//ZipOutputStream zos = new ZipOutputStream(out);
		//File tempFile = new File(temp);
		//如果目录不存在则新建
		//if(!tempFile.exists())tempFile.createNewFile();
		compress(path, zos, name);
	}
	public static void deleteFiles(String path){
		File file = new File(path);
		File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
        }
	}

}
