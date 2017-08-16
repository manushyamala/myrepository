package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.List;

public class PrivilegeDTOListHolder implements Serializable {

	private static final long serialVersionUID = 2331605832598802005L;
	
	private List<PrivilegeDTO> unassignedPrivilegDTOList;
	
	private List<PrivilegeDTO> roleAssignedPrivilegDTOList;
	
	public PrivilegeDTOListHolder(){
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
	
	
	
	

}
