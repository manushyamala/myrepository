package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class RolePrivilegeDTO implements Serializable{
	
	private static final long serialVersionUID = -3162520059155609081L;
	
	private long rolePrivilegeId;
	
	private RoleDTO roleDTO;
	
	public RolePrivilegeDTO(){
		
	}	
	
	public long getRolePrivilegeId() {
		return rolePrivilegeId;
	}
	public void setRolePrivilegeId(long rolePrivilegeId) {
		this.rolePrivilegeId = rolePrivilegeId;
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}


}
