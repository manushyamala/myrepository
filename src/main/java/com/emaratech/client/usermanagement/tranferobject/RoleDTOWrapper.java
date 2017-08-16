package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.List;

public class RoleDTOWrapper implements Serializable {
	
	
	private static final long serialVersionUID = -6682661894090711610L;
	
	private List<RoleDTO> assignedRoleDTOList;
	private List<RoleDTO> unassignedRoleDTOList;
	private long userID;
	
	public RoleDTOWrapper(){
		
	}

	public List<RoleDTO> getAssignedRoleDTOList() {
		return assignedRoleDTOList;
	}

	public void setAssignedRoleDTOList(List<RoleDTO> assignedRoleDTOList) {
		this.assignedRoleDTOList = assignedRoleDTOList;
	}

	public List<RoleDTO> getUnassignedRoleDTOList() {
		return unassignedRoleDTOList;
	}

	public void setUnassignedRoleDTOList(List<RoleDTO> unassignedRoleDTOList) {
		this.unassignedRoleDTOList = unassignedRoleDTOList;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	
	

}
