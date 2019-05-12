package com.hws.oa.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hws.oa.core.LoadConf;
import com.hws.oa.exception.CommonException;
import com.hws.oa.util.JGitUtils;

@RestController
public class GitController {

	@RequestMapping("/full")
	public void pull(){
		try {
			JGitUtils.update(1);
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
}
