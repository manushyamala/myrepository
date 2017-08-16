package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.Date;

public class UserPrivilegeDTO implements Serializable{
	
	private static final long serialVersionUID = 4920163935728532450L;
	
	private long userPrivilegeId;
	private Long createdBy;
	private Date createdDate;
	private ApplicationUserDTO applicationUser;
	private PrivilegeDTO privilege;
	private Date modifiedDate;
	private Long modifiedBy;
	
	public UserPrivilegeDTO(){
		this.applicationUser = new ApplicationUserDTO();
		this.privilege = new PrivilegeDTO();
	}

	public long getUserPrivilegeId() {
		return userPrivilegeId;
	}

	public void setUserPrivilegeId(long userPrivilegeId) {
		this.userPrivilegeId = userPrivilegeId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ApplicationUserDTO getApplicationUser() {
		return applicationUser;
	}

	public void setApplicationUser(ApplicationUserDTO applicationUser) {
		this.applicationUser = applicationUser;
	}

	public PrivilegeDTO getPrivilege() {
		return privilege;
	}

	public void setPrivilege(PrivilegeDTO privilege) {
		this.privilege = privilege;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationUser == null) ? 0 : applicationUser.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result
				+ ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result
				+ (int) (userPrivilegeId ^ (userPrivilegeId >>> 32));
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
		UserPrivilegeDTO other = (UserPrivilegeDTO) obj;
		if (applicationUser == null) {
			if (other.applicationUser != null)
				return false;
		} else if (!applicationUser.equals(other.applicationUser))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		if (userPrivilegeId != other.userPrivilegeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPrivilegeDTO [userPrivilegeId=" + userPrivilegeId
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", applicationUser=" + applicationUser + ", privilege="
				+ privilege + ", modifiedDate=" + modifiedDate
				+ ", modifiedBy=" + modifiedBy + "]";
	}
	
	
	
	
	
}
