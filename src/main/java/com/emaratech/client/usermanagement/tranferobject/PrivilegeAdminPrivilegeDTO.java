package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;


public class PrivilegeAdminPrivilegeDTO implements Serializable {
	
	private static final long serialVersionUID = 5574915663125445987L;
	
	private long privilegeAdminPrivilegeId;
	private PrivilegeDTO mainAdminPrivilege;
	private PrivilegeDTO adminAssociatedPrivilege;
	
	public PrivilegeAdminPrivilegeDTO(){
		this.mainAdminPrivilege = new PrivilegeDTO();
		this.adminAssociatedPrivilege = new PrivilegeDTO();
	}

	public long getPrivilegeAdminPrivilegeId() {
		return privilegeAdminPrivilegeId;
	}

	public void setPrivilegeAdminPrivilegeId(long privilegeAdminPrivilegeId) {
		this.privilegeAdminPrivilegeId = privilegeAdminPrivilegeId;
	}

	public PrivilegeDTO getMainAdminPrivilege() {
		return mainAdminPrivilege;
	}

	public void setMainAdminPrivilege(PrivilegeDTO mainAdminPrivilege) {
		this.mainAdminPrivilege = mainAdminPrivilege;
	}

	public PrivilegeDTO getAdminAssociatedPrivilege() {
		return adminAssociatedPrivilege;
	}

	public void setAdminAssociatedPrivilege(PrivilegeDTO adminAssociatedPrivilege) {
		this.adminAssociatedPrivilege = adminAssociatedPrivilege;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((adminAssociatedPrivilege == null) ? 0
						: adminAssociatedPrivilege.hashCode());
		result = prime
				* result
				+ ((mainAdminPrivilege == null) ? 0 : mainAdminPrivilege
						.hashCode());
		result = prime
				* result
				+ (int) (privilegeAdminPrivilegeId ^ (privilegeAdminPrivilegeId >>> 32));
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
		PrivilegeAdminPrivilegeDTO other = (PrivilegeAdminPrivilegeDTO) obj;
		if (adminAssociatedPrivilege == null) {
			if (other.adminAssociatedPrivilege != null)
				return false;
		} else if (!adminAssociatedPrivilege
				.equals(other.adminAssociatedPrivilege))
			return false;
		if (mainAdminPrivilege == null) {
			if (other.mainAdminPrivilege != null)
				return false;
		} else if (!mainAdminPrivilege.equals(other.mainAdminPrivilege))
			return false;
		if (privilegeAdminPrivilegeId != other.privilegeAdminPrivilegeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PrivilegeAdminPrivilegeDTO [privilegeAdminPrivilegeId="
				+ privilegeAdminPrivilegeId + ", mainAdminPrivilege="
				+ mainAdminPrivilege + ", adminAssociatedPrivilege="
				+ adminAssociatedPrivilege + "]";
	} 
	
	
	
	


}
