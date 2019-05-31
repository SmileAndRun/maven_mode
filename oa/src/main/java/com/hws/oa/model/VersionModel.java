package com.hws.oa.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_version")
public class VersionModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4524592412155664048L;
	@Id
	private Long versionId;
	private Timestamp createTime;
	private String updateInfo;
	private String packageInfo;
	
	public String getUpdateInfo() {
		return updateInfo;
	}
	public void setUpdateInfo(String updateInfo) {
		this.updateInfo = updateInfo;
	}
	public String getPackageInfo() {
		return packageInfo;
	}
	public void setPackageInfo(String packageInfo) {
		this.packageInfo = packageInfo;
	}
	public Long getVersionId() {
		return versionId;
	}
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
