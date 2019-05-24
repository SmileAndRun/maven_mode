package com.hws.oa.model;

public class SystemModel {

	private int num;
	private String localRepo;
	private String remoteRepo;
	public int getNum() {
		return num;
	}

	public String getLocalRepo() {
		return localRepo;
	}

	public void setLocalRepo(String localRepo) {
		this.localRepo = localRepo;
	}

	public String getRemoteRepo() {
		return remoteRepo;
	}

	public void setRemoteRepo(String remoteRepo) {
		this.remoteRepo = remoteRepo;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public SystemModel() {
		super();
	}

	@Override
	public String toString() {
		return "SystemModel [num=" + num + ", localRepo=" + localRepo
				+ ", remoteRepo=" + remoteRepo + "]";
	}
}
