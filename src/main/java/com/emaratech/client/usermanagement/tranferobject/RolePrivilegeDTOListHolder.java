package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.List;

public class RolePrivilegeDTOListHolder implements Serializable {

	private static final long serialVersionUID = 2331605832598802005L;
	
	private List<PrivilegeDTO> unassignedPrivilegDTOList;
	
	private List<PrivilegeDTO> roleAssignedPrivilegDTOList;
	
	private long roleID;
	
	private long createdBy;	
	
	public RolePrivilegeDTOListHolder(){
	}

	public List<PrivilegeDTO> getUnassignedPrivilegDTOList() {
		return unassignedPrivilegDTOList;
	}

	public void setUnassignedPrivilegDTOList(
			List<PrivilegeDTO> unassignedPrivilegDTOList) {
		this.unassignedPrivilegDTOList = unassignedPrivilegDTOList;
	}

	public List<PrivilegeDTO> getRoleAssignedPrivilegDTOList() {
		return roleAssignedPrivilegDTOList;
	}

	public void setRoleAssignedPrivilegDTOList(
			List<PrivilegeDTO> userAssignedPrivilegDTOList) {
		this.roleAssignedPrivilegDTOList = userAssignedPrivilegDTOList;
	}

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
	

}
