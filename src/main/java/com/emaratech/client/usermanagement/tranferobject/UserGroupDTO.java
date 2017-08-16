package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserGroupDTO implements Serializable{
	
	private static final long serialVersionUID = -7316040829154445040L;
	
	private long userGroupId;
	private Date createdDate;
	private String nameEn;
	private String nameAr;
	private RoleGroupDTO roleGroup;
	private Set<UserGroupCriteriaTagDTO> userGroupCriteriaTags;
	private boolean selected;

	public UserGroupDTO(){
		this.roleGroup = new RoleGroupDTO();
		this.userGroupCriteriaTags = new HashSet<UserGroupCriteriaTagDTO>();
	}

	public long getUserGroupId() {
		return userGroupId;
	}
	
	public void setUserGroupId(long userGroupId) {
		this.userGroupId = userGroupId;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getNameEn() {
		return nameEn;
	}
	
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	
	public String getNameAr() {
		return nameAr;
	}
	
	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}
	
	public RoleGroupDTO getRoleGroup() {
		return roleGroup;
	}
	
	public void setRoleGroup(RoleGroupDTO roleGroup) {
		this.roleGroup = roleGroup;
	}
	
	public Set<UserGroupCriteriaTagDTO> getUserGroupCriteriaTags() {
		return userGroupCriteriaTags;
	}
	
	public void setUserGroupCriteriaTags(
			Set<UserGroupCriteriaTagDTO> userGroupCriteriaTags) {
		this.userGroupCriteriaTags = userGroupCriteriaTags;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((nameAr == null) ? 0 : nameAr.hashCode());
		result = prime * result + ((nameEn == null) ? 0 : nameEn.hashCode());
		result = prime * result
				+ ((roleGroup == null) ? 0 : roleGroup.hashCode());
		result = prime * result + (selected ? 1231 : 1237);
		result = prime
				* result
				+ ((userGroupCriteriaTags == null) ? 0 : userGroupCriteriaTags
						.hashCode());
		result = prime * result + (int) (userGroupId ^ (userGroupId >>> 32));
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
		UserGroupDTO other = (UserGroupDTO) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (nameAr == null) {
			if (other.nameAr != null)
				return false;
		} else if (!nameAr.equals(other.nameAr))
			return false;
		if (nameEn == null) {
			if (other.nameEn != null)
				return false;
		} else if (!nameEn.equals(other.nameEn))
			return false;
		if (roleGroup == null) {
			if (other.roleGroup != null)
				return false;
		} else if (!roleGroup.equals(other.roleGroup))
			return false;
		if (selected != other.selected)
			return false;
		if (userGroupCriteriaTags == null) {
			if (other.userGroupCriteriaTags != null)
				return false;
		} else if (!userGroupCriteriaTags.equals(other.userGroupCriteriaTags))
			return false;
		if (userGroupId != other.userGroupId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserGroupDTO [userGroupId=" + userGroupId + ", createdDate="
				+ createdDate + ", nameEn=" + nameEn + ", nameAr=" + nameAr
				+ ", roleGroup=" + roleGroup + ", userGroupCriteriaTags="
				+ userGroupCriteriaTags + ", selected=" + selected + "]";
	}
	  
	
	
  
	

}
