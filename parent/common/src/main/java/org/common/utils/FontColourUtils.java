package org.common.utils;

public class FontColourUtils {
	public static String colour(String origin,String color,String match){
		String[] temp = origin.split(match);
		String result = "";
		switch (temp.length) {
		case 0://表示全部匹配
			result = "<font color='"+color+"'>"+origin+"</font>";
			break;
		case 1:
			int num = origin.indexOf(match);
			if(num ==0){//表示匹配的字符在最左侧
				result = "<font color='"+color+"'>"+match+"</font>"+temp[0];
			}else{//表示匹配的字符在最右侧
				result = temp[0]+"<font color='"+color+"'>"+match+"</font>";
			}
			break;
		case 2:
			result = temp[0] +"<font color='"+color+"'>"+match+"</font>"+ temp[1];
			break;

		}
		
		return result;
	}

}
