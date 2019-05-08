package com.hws.file.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hws.file.service.FileService;

@RestController
public class FileController {

	@Autowired
	FileService fs;
	
	@RequestMapping("/downLoad")
	public ResponseEntity<byte[]> fileDownload(String path,String id){
		try {
			return fs.fileDownload(path, id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
