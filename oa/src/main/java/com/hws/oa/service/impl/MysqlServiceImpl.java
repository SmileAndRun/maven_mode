package com.hws.oa.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hws.oa.model.VersionModel;
import com.hws.oa.repository.VersionRepository;
import com.hws.oa.service.HQLService;
import com.hws.oa.service.MysqlService;

@Service
public class MysqlServiceImpl implements MysqlService {

	@Autowired
	private VersionRepository vr;
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
	public VersionModel getVersionModelById(Long versionId) {
		Optional<VersionModel>  temp = vr.findById(versionId);
		return temp.orElse(null);
	}

	@Override
	public List<VersionModel> getListVersionModelByTime(Timestamp startTime,
			Timestamp endTime) {
		String sql = "select versionId,updateInfo,packageInfo,createTime from t_version where createTime > ? and createTime < ?";
		List<VersionModel> list = hs.getEntitiesBySql(VersionModel.class, sql, startTime,endTime);
		return list;
	}

	@Override
	public boolean addVersionModel(VersionModel versionModel) {
		VersionModel model = vr.saveAndFlush(versionModel);
		if(null != model)return true;
		return false;
	}

	@Override
	public boolean deleteVersionModel(Long versionId) {
		if(versionId == null)return false;
		vr.deleteById(versionId);
		return true;
	}

}
