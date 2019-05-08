package com.hws.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hws.file.service.FileService;
import com.hws.file.util.MyFileUtils;

public class FileServiceImpl implements FileService {

	@Override
	public boolean fileUpload(ResponseEntity<byte[]> bytes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResponseEntity<byte[]> fileDownload(String path,String id) throws IOException {
		
		String fileName = MyFileUtils.findFileName(path,id);
		if(null == fileName) return null;
		fileName   = new String(fileName.getBytes("GBK"), "iso-8859-1");
		File file = new File(path+File.separator+fileName);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return  new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
		
	}

}
