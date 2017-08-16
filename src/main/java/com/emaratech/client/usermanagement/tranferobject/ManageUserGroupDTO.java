package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class ManageUserGroupDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String groupNameEn;
	private String groupNameAr;
	private long roleGroupID;
	private long[] appUserIDList;
	private long selectedUserGroupID;
	private String selectedUserGroupRoleNameEn;
	private String selectedUserGroupRoleNameAr;
	
	public ManageUserGroupDTO(){	
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

	public long getRoleGroupID() {
		return roleGroupID;
	}

	public void setRoleGroupID(long roleGroupID) {
		this.roleGroupID = roleGroupID;
	}

	public long[] getAppUserIDList() {
		return appUserIDList;
	}

	public void setAppUserIDList(long[] appUserIDList) {
		this.appUserIDList = appUserIDList;
	}

	public long getSelectedUserGroupID() {
		return selectedUserGroupID;
	}

	public void setSelectedUserGroupID(long selectedUserGroupID) {
		this.selectedUserGroupID = selectedUserGroupID;
	}

	public String getSelectedUserGroupRoleNameEn() {
		return selectedUserGroupRoleNameEn;
	}

	public void setSelectedUserGroupRoleNameEn(String selectedUserGroupRoleNameEn) {
		this.selectedUserGroupRoleNameEn = selectedUserGroupRoleNameEn;
	}

	public String getSelectedUserGroupRoleNameAr() {
		return selectedUserGroupRoleNameAr;
	}

	public void setSelectedUserGroupRoleNameAr(String selectedUserGroupRoleNameAr) {
		this.selectedUserGroupRoleNameAr = selectedUserGroupRoleNameAr;
	}


	
	

}
