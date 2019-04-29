package org.common.model;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.shiro.session.Session;

public class OnlineCountModel {

	public static Integer activeSessions = 0;// 当前活动人数
	
	private static Map<String,Session> beforeCache = new ConcurrentHashMap<String,Session>();
	private static Map<String,Session> afterCache = new ConcurrentHashMap<String,Session>();
	private static Map<Serializable, String> idCache = new  ConcurrentHashMap<Serializable,String>();
	public static Integer getActiveSessions() {
		return activeSessions;
	}
	public static Map<String, Session> getBeforeCache() {
		return beforeCache;
	}
	public static Map<String, Session> getAfterCache() {
		return afterCache;
	}
	public static Map<Serializable, String> getIdCache() {
		return idCache;
	}
}
