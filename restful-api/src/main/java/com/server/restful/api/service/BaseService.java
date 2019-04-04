package com.server.restful.api.service;

import java.util.List;

public interface BaseService {
	
	 <T> T selectById(Integer id);
	 <T> T selectById(T obj);
	 <T> List<T> selectAll();
	 <T> Integer update(T obj);
	 <T> Integer insert(T obj);
	 <T> Integer deleteById(T obj);
	 <T> Integer deleteById(Integer id);
	 

}
