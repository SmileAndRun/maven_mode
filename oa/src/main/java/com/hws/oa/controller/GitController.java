package com.hws.oa.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hws.oa.core.LoadConf;
import com.hws.oa.exception.CommonException;
import com.hws.oa.service.JGitService;
import com.hws.oa.service.MavenService;

@RestController
public class GitController {

	@Autowired
	JGitService js;
	@Autowired
	MavenService ms;
	
	@RequestMapping("/pull")
	public void pull(HttpServletRequest request){
		try {
			js.update(1,request);
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/mvn")
	public void test(HttpServletRequest request){
		try {
			ms.mvn("", "ipconfig");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
