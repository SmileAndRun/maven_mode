package com.hws.oa.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.exception.CommonException;

public interface JGitService {

	public JSONObject update(Integer num,HttpServletRequest request)throws  CommonException, IOException, InvalidRemoteException, TransportException, GitAPIException;
}
