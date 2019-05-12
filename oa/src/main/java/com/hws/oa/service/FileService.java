package com.hws.oa.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;


public interface FileService {

	public boolean fileUpload(ResponseEntity<byte[]> bytes);
	public ResponseEntity<byte[]> fileDownload(String path,String id)throws IOException;
}
