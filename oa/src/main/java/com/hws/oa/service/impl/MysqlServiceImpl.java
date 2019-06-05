package com.hws.oa.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hws.oa.model.LogModel;
import com.hws.oa.model.VersionModel;
import com.hws.oa.repository.MyLogRepository;
import com.hws.oa.repository.VersionRepository;
import com.hws.oa.service.HQLService;
import com.hws.oa.service.MysqlService;

@Service
public class MysqlServiceImpl implements MysqlService {

	@Autowired
	private VersionRepository vr;
	@Autowired
	private MyLogRepository mr;
	@Autowired
	private HQLService hs;
	@Override
	public List<VersionModel> getAllVersionModel() {
		Sort sort = new Sort(Direction.DESC,"createTime");
		List<VersionModel> list = vr.findAll(sort);
		if(null == list || list.size() == 0) return null;
		return list;
	}

	@Override
	public VersionModel getVersionModelById(Integer versionId) {
		
		String sql = "select versionId,updateInfo,packageInfo,createTime from t_version where versionId = ?";
		VersionModel model = hs.getEntityBySql(VersionModel.class, sql,versionId);
		return model;
	}

	@Override
	public List<VersionModel> getListVersionModelByTime(Timestamp startTime,
			Timestamp endTime) {
		String sql = "select versionId,updateInfo,packageInfo,createTime from t_version where createTime > ? and createTime < ?";
		List<VersionModel> list = hs.getEntitiesBySqlByProx(VersionModel.class, sql, startTime,endTime);
		return list;
	}

	@Override
	public boolean addVersionModel(VersionModel versionModel) {
		VersionModel model = vr.saveAndFlush(versionModel);
		if(null != model)return true;
		return false;
	}

	@Override
	public boolean deleteVersionModel(Integer versionId) {
		if(versionId == null)return false;
		String sql = "delete from t_version where versionId = ?";
		return hs.updateBySql( sql,versionId);
	}

	@Override
	public boolean insertLog(LogModel model) {
		return mr.save(model)==null?false:true;
	}

}
