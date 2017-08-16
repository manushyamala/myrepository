package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.Date;

public class UserGroupCriteriaTagDTO implements Serializable{
	
	private static final long serialVersionUID = -7698297987362821965L;
	
	private long userGroupCriteriaTagId;
	private Long createdBy;
	private Date createdDate;
	private long criteriaTagId;
	private UserGroupDTO userGroup;
	private Date modifiedDate;
	private Long modifiedBy;
	
	public UserGroupCriteriaTagDTO(){
		this.userGroup = new UserGroupDTO();
	}

	public long getUserGroupCriteriaTagId() {
		return userGroupCriteriaTagId;
	}

	public void setUserGroupCriteriaTagId(long userGroupCriteriaTagId) {
		this.userGroupCriteriaTagId = userGroupCriteriaTagId;
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

	public long getCriteriaTagId() {
		return criteriaTagId;
	}

	public void setCriteriaTagId(long criteriaTagId) {
		this.criteriaTagId = criteriaTagId;
	}

	public UserGroupDTO getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroupDTO userGroup) {
		this.userGroup = userGroup;
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
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ (int) (criteriaTagId ^ (criteriaTagId >>> 32));
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result
				+ ((userGroup == null) ? 0 : userGroup.hashCode());
		result = prime
				* result
				+ (int) (userGroupCriteriaTagId ^ (userGroupCriteriaTagId >>> 32));
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
		UserGroupCriteriaTagDTO other = (UserGroupCriteriaTagDTO) obj;
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
		if (criteriaTagId != other.criteriaTagId)
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
		if (userGroup == null) {
			if (other.userGroup != null)
				return false;
		} else if (!userGroup.equals(other.userGroup))
			return false;
		if (userGroupCriteriaTagId != other.userGroupCriteriaTagId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserGroupCriteriaTagDTO [userGroupCriteriaTagId="
				+ userGroupCriteriaTagId + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", criteriaTagId="
				+ criteriaTagId + ", userGroup=" + userGroup
				+ ", modifiedDate=" + modifiedDate + ", modifiedBy="
				+ modifiedBy + "]";
	}

	
	


}
