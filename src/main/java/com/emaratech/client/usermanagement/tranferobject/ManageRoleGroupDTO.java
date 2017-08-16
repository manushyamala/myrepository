package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class ManageRoleGroupDTO implements Serializable {

	private static final long serialVersionUID = -2204315515142981342L;
	
	private String groupNameEn;
	private String groupNameAr;
	private boolean active;
	private long[] roleIDList;
	private long selectedRoleGroupID;
	private long userID;
	
	public ManageRoleGroupDTO(){
		
	}

	public String getGroupNameEn() {
		return groupNameEn;
	}

	public void setGroupNameEn(String groupNameEn) {
		this.groupNameEn = groupNameEn;
	}

	public String getGroupNameAr() {
		return groupNameAr;
	}

	public void setGroupNameAr(String groupNameAr) {
		this.groupNameAr = groupNameAr;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long[] getRoleIDList() {
		return roleIDList;
	}

	public void setRoleIDList(long[] roleIDList) {
		this.roleIDList = roleIDList;
	}

	public long getSelectedRoleGroupID() {
		return selectedRoleGroupID;
	}

	public void setSelectedRoleGroupID(long selectedRoleGroupID) {
		this.selectedRoleGroupID = selectedRoleGroupID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	
	
	

}
