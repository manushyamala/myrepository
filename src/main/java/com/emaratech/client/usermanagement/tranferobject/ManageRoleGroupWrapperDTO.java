package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ManageRoleGroupWrapperDTO implements Serializable{

	private static final long serialVersionUID = 2958966458325773573L;
	
	private List<RoleGroupDTO> roleGroupDTOList;
	private List<RoleDTO> roleDTOList;
	private Map<Long,RoleGroupDTO> roleGroupDTOMap;
	private Map<Long,RoleDTO> roleDTOMap;
	
	public ManageRoleGroupWrapperDTO(){
	}

	public List<RoleGroupDTO> getRoleGroupDTOList() {
		return roleGroupDTOList;
	}

	public void setRoleGroupDTOList(List<RoleGroupDTO> roleGroupDTOList) {
		this.roleGroupDTOList = roleGroupDTOList;
	}

	public List<RoleDTO> getRoleDTOList() {
		return roleDTOList;
	}

	public void setRoleDTOList(List<RoleDTO> roleDTOList) {
		this.roleDTOList = roleDTOList;
	}

	public Map<Long, RoleGroupDTO> getRoleGroupDTOMap() {
		return roleGroupDTOMap;
	}

	public void setRoleGroupDTOMap(Map<Long, RoleGroupDTO> roleGroupDTOMap) {
		this.roleGroupDTOMap = roleGroupDTOMap;
	}

	public Map<Long, RoleDTO> getRoleDTOMap() {
		return roleDTOMap;
	}

	public void setRoleDTOMap(Map<Long, RoleDTO> roleDTOMap) {
		this.roleDTOMap = roleDTOMap;
	}
	

}
