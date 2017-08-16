package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PrivilegeDTO implements Serializable{
	
	private static final long serialVersionUID = 8682698343765540880L;
	
	private long privilegeId;
	private Long appActionTypeId;
	private String privilege;
	private String privilegeNameAr;
	private String privilegeNameEn;
	private PrivilegeDTO parentPrivilege;
	private boolean ngeAllowed;
	private Boolean applyDataFilter;
	private Set<PrivilegeEmiratesDepartmentDTO> privilegeEmiratesDepartmentSet;
	private boolean selected;
	private Set<PrivilegeAdminPrivilegeDTO> associatedAdminPrivileges;
	private Boolean isAdminPrivilege;
	private Set<AdminPrivilegeEmiratesDepartmentDTO> adminPrivilegeEmiratesDepartmentSet;
	
	public PrivilegeDTO(){
		this.privilegeEmiratesDepartmentSet = new HashSet<PrivilegeEmiratesDepartmentDTO>();
		this.associatedAdminPrivileges = new HashSet<PrivilegeAdminPrivilegeDTO>();
		this.adminPrivilegeEmiratesDepartmentSet = new HashSet<AdminPrivilegeEmiratesDepartmentDTO>();
	}
	
	public long getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(long privilegeId) {
		this.privilegeId = privilegeId;
	}
	public Long getAppActionTypeId() {
		return appActionTypeId;
	}
	public void setAppActionTypeId(Long appActionTypeId) {
		this.appActionTypeId = appActionTypeId;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getPrivilegeNameAr() {
		return privilegeNameAr;
	}
	public void setPrivilegeNameAr(String privilegeNameAr) {
		this.privilegeNameAr = privilegeNameAr;
	}
	public String getPrivilegeNameEn() {
		return privilegeNameEn;
	}
	public void setPrivilegeNameEn(String privilegeNameEn) {
		this.privilegeNameEn = privilegeNameEn;
	}
	public PrivilegeDTO getParentPrivilege() {
		return parentPrivilege;
	}
	public void setParentPrivilege(PrivilegeDTO parentPrivilege) {
		this.parentPrivilege = parentPrivilege;
	}
	public boolean isNgeAllowed() {
		return ngeAllowed;
	}
	public void setNgeAllowed(boolean ngeAllowed) {
		this.ngeAllowed = ngeAllowed;
	}
	public Boolean getApplyDataFilter() {
		return applyDataFilter;
	}
	public void setApplyDataFilter(Boolean applyDataFilter) {
		this.applyDataFilter = applyDataFilter;
	}
	public Set<PrivilegeEmiratesDepartmentDTO> getPrivilegeEmiratesDepartmentSet() {
		return privilegeEmiratesDepartmentSet;
	}
	public void setPrivilegeEmiratesDepartmentSet(
			Set<PrivilegeEmiratesDepartmentDTO> privilegeEmiratesDepartmentSet) {
		this.privilegeEmiratesDepartmentSet = privilegeEmiratesDepartmentSet;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Set<PrivilegeAdminPrivilegeDTO> getAssociatedAdminPrivileges() {
		return associatedAdminPrivileges;
	}
	public void setAssociatedAdminPrivileges(
			Set<PrivilegeAdminPrivilegeDTO> associatedAdminPrivileges) {
		this.associatedAdminPrivileges = associatedAdminPrivileges;
	}
	public Boolean getAdminPrivilege() {
		return isAdminPrivilege;
	}
	public void setAdminPrivilege(Boolean isAdminPrivilege) {
		this.isAdminPrivilege = isAdminPrivilege;
	}
	public Set<AdminPrivilegeEmiratesDepartmentDTO> getAdminPrivilegeEmiratesDepartmentSet() {
		return adminPrivilegeEmiratesDepartmentSet;
	}
	public void setAdminPrivilegeEmiratesDepartmentSet(
			Set<AdminPrivilegeEmiratesDepartmentDTO> adminPrivilegeEmiratesDepartmentSet) {
		this.adminPrivilegeEmiratesDepartmentSet = adminPrivilegeEmiratesDepartmentSet;
	}
	

	@Override
	public String toString() {
		return "PrivilegeDTO [privilegeId=" + privilegeId
				+ ", appActionTypeId=" + appActionTypeId + ", privilege="
				+ privilege + ", privilegeNameAr=" + privilegeNameAr
				+ ", privilegeNameEn=" + privilegeNameEn + ", parentPrivilege="
				+ parentPrivilege + ", ngeAllowed=" + ngeAllowed
				+ ", applyDataFilter=" + applyDataFilter
				+ ", privilegeEmiratesDepartmentSet="
				+ privilegeEmiratesDepartmentSet + ", selected=" + selected
				+ ", associatedAdminPrivileges=" + associatedAdminPrivileges
				+ ", isAdminPrivilege=" + isAdminPrivilege
				+ ", adminPrivilegeEmiratesDepartmentSet="
				+ adminPrivilegeEmiratesDepartmentSet + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result + (int) (privilegeId ^ (privilegeId >>> 32));
		result = prime * result
				+ ((privilegeNameAr == null) ? 0 : privilegeNameAr.hashCode());
		result = prime * result
				+ ((privilegeNameEn == null) ? 0 : privilegeNameEn.hashCode());
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
		PrivilegeDTO other = (PrivilegeDTO) obj;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		if (privilegeId != other.privilegeId)
			return false;
		if (privilegeNameAr == null) {
			if (other.privilegeNameAr != null)
				return false;
		} else if (!privilegeNameAr.equals(other.privilegeNameAr))
			return false;
		if (privilegeNameEn == null) {
			if (other.privilegeNameEn != null)
				return false;
		} else if (!privilegeNameEn.equals(other.privilegeNameEn))
			return false;
		return true;
	}

	
	
	
	

}
