package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.Date;

public class UserRoleDTO implements Serializable{
	
	private static final long serialVersionUID = -4592471981057133297L;
	
	private long userRoleId;
	private RoleDTO role;
	private Boolean isEnabled;
	private Date assignedDate;
	private Boolean isDefault;
	
	public UserRoleDTO(){
		this.role = new RoleDTO();
	}
	  
	public long getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(RoleDTO role) {
		this.role = role;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Date getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assignedDate == null) ? 0 : assignedDate.hashCode());
		result = prime * result
				+ ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result
				+ ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + (int) (userRoleId ^ (userRoleId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleDTO other = (UserRoleDTO) obj;
		if (assignedDate == null) {
			if (other.assignedDate != null)
				return false;
		} else if (!assignedDate.equals(other.assignedDate))
			return false;
		if (isDefault == null) {
			if (other.isDefault != null)
				return false;
		} else if (!isDefault.equals(other.isDefault))
			return false;
		if (isEnabled == null) {
			if (other.isEnabled != null)
				return false;
		} else if (!isEnabled.equals(other.isEnabled))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userRoleId != other.userRoleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRoleDTO [userRoleId=" + userRoleId + ", role=" + role
				+ ", isEnabled=" + isEnabled + ", assignedDate=" + assignedDate
				+ ", isDefault=" + isDefault + "]";
	}
	
	
	
	

}
