package com.hws.oa.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author hws
 *
 */
public class MyCacheUtils {
	public static Set<String> NAMECACHE =new HashSet<String>();
	public static void updateNameCache(Set<String> parm){
		NAMECACHE = parm;
	}
	public static void addItem(String name){
		NAMECACHE.add(name);
	}
	public static void removeItem(String name){
		NAMECACHE.remove(name);
	}
	public static boolean isContain(String name){
		return NAMECACHE.contains(name);
	}
}
