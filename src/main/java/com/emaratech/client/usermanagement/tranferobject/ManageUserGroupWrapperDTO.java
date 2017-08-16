package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ManageUserGroupWrapperDTO implements Serializable {

	private static final long serialVersionUID = -5762884268973430330L;
	
	private List<ApplicationUserDTO> applicationUsers;
	
	private List<RoleGroupDTO> roleGroups;
	
	private List<UserGroupDTO> userGroups;
	
	private Map<Long,RoleGroupDTO>  roleGroupsMap;
	
	private Map<Long,UserGroupDTO> userGroupsMap;
	
	private Map<Long,ApplicationUserDTO> applicationUserMap;
	
	public ManageUserGroupWrapperDTO(){		
	}

	public List<ApplicationUserDTO> getApplicationUsers() {
		return applicationUsers;
	}

	public void setApplicationUsers(List<ApplicationUserDTO> applicationUsers) {
		this.applicationUsers = applicationUsers;
	}

	public List<RoleGroupDTO> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(List<RoleGroupDTO> roleGroups) {
		this.roleGroups = roleGroups;
	}

	public List<UserGroupDTO> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroupDTO> userGroups) {
		this.userGroups = userGroups;
	}

	public Map<Long, RoleGroupDTO> getRoleGroupsMap() {
		return roleGroupsMap;
	}

	public void setRoleGroupsMap(Map<Long, RoleGroupDTO> roleGroupsMap) {
		this.roleGroupsMap = roleGroupsMap;
	}

	public Map<Long, UserGroupDTO> getUserGroupsMap() {
		return userGroupsMap;
	}

	public void setUserGroupsMap(Map<Long, UserGroupDTO> userGroupsMap) {
		this.userGroupsMap = userGroupsMap;
	}

	public Map<Long, ApplicationUserDTO> getApplicationUserMap() {
		return applicationUserMap;
	}

	public void setApplicationUserMap(
			Map<Long, ApplicationUserDTO> applicationUserMap) {
		this.applicationUserMap = applicationUserMap;
	}
	
}
