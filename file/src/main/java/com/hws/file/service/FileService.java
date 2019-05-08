package com.hws.file.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

	public boolean fileUpload(ResponseEntity<byte[]> bytes);
	public ResponseEntity<byte[]> fileDownload(String path,String id)throws IOException;
}
