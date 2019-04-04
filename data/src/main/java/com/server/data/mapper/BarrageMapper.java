package com.server.data.mapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.server.restful.api.pojo.Barrage;




public interface BarrageMapper {
	/**
	 * 获取所有弹幕
	 * @return
	 */
	public List<Barrage> getAllBar();
	/**
	 * 通过时间模糊获取部分弹幕
	 * @param time
	 * @return
	 */
	public List<Barrage> getListBarByTime(Date time);
	/**
	 * 通过id获取弹幕
	 */
	public Barrage getBarById(String id);
	/**
	 * 模糊查询（待定）
	 */
	public List<Barrage> getListBarByLike(String content);
	/**
	 * 查询当前时间弹幕数量
	 * @param time
	 * @return
	 */
	public Integer getBarrageCount(Timestamp time);
	/**
	 * 获取指定图片的弹幕
	 */
	public List<Barrage> getBarByImagesId(Integer imagesId);
	/**
	 * 添加弹幕
	 * @param barrage
	 * @return
	 */
	public Integer addBarrage(Barrage barrage);
	/**
	 * 通过主键删除弹幕
	 * @param contentId
	 * @return
	 */
	public Integer delBarrageBycontentId(Integer contentId);
	public Barrage getMaxContentId();
}
