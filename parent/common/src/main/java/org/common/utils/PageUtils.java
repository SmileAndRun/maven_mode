package org.common.utils;

public class PageUtils {
	/**
	 * 
	 * @param pages 页码总数
	 * @param pageSize 每页展示的数目
	 * @return
	 */
	public static Integer getPaginationNum(Integer pages,Integer pageSize){
		if(pages == 0)return 0;
		//整除
		if(pages%pageSize == 0)return pageSize;
		return pages%pageSize;
	}

}
