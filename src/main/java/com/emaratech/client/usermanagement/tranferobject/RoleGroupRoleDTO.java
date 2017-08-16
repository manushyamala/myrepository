package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;


public class RoleGroupRoleDTO implements Serializable{
	
	
	private static final long serialVersionUID = 9154296930130709741L;
	
	private long roleGroupId;
	private RoleGroupDTO roleGroup;
	private RoleDTO role;
	
	public RoleGroupRoleDTO(){		
	}
	
	public long getRoleGroupId() {
		return roleGroupId;
	}
	public void setRoleGroupId(long roleGroupId) {
		this.roleGroupId = roleGroupId;
	}
	public RoleGroupDTO getRoleGroup() {
		return roleGroup;
	}
	public void setRoleGroup(RoleGroupDTO roleGroup) {
		this.roleGroup = roleGroup;
	}
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(RoleDTO role) {
		this.role = role;
	}
	
}
