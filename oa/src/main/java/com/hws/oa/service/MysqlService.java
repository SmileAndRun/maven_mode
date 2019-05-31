package com.hws.oa.service;

import java.sql.Timestamp;
import java.util.List;

import com.hws.oa.model.VersionModel;

public interface MysqlService {
	public List<VersionModel> getAllVersionModel();
	public VersionModel getVersionModelById(Long versionId);
	public List<VersionModel> getListVersionModelByTime(Timestamp startTime,Timestamp endTime);
	public boolean addVersionModel(VersionModel versionModel);
	public boolean deleteVersionModel(Long versionId);
}
