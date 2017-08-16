package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CriteriaTagDTO implements Serializable {
	

	private static final long serialVersionUID = 3470149212429182290L;
	
	private long criteriaTagId;	
	private Date createdDate;	
	private Date modifiedDate;	
	private String tagNameAr;	
	private String tagNameEn;	
	private Long createdBy;	
	private Long modifiedBy;	
	private boolean isFixed;	
	private Set<UserCriteriaTagDTO> userCriteriaTag;
			
	public CriteriaTagDTO(){
		this.userCriteriaTag = new HashSet<UserCriteriaTagDTO>();
	}
	
	public long getCriteriaTagId() {
		return criteriaTagId;
	}
	
	public void setCriteriaTagId(long criteriaTagId) {
		this.criteriaTagId = criteriaTagId;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String getTagNameAr() {
		return tagNameAr;
	}
	
	public void setTagNameAr(String tagNameAr) {
		this.tagNameAr = tagNameAr;
	}
	
	public String getTagNameEn() {
		return tagNameEn;
	}
	
	public void setTagNameEn(String tagNameEn) {
		this.tagNameEn = tagNameEn;
	}
	
	public Long getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	public Long getModifiedBy() {
		return modifiedBy;
	}
	
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public boolean isFixed() {
		return isFixed;
	}
	
	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}
	
	public Set<UserCriteriaTagDTO> getUserCriteriaTag() {
		return userCriteriaTag;
	}
	
	public void setUserCriteriaTag(Set<UserCriteriaTagDTO> userCriteriaTag) {
		this.userCriteriaTag = userCriteriaTag;
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
		result = prime * result + (isFixed ? 1231 : 1237);
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result
				+ ((tagNameAr == null) ? 0 : tagNameAr.hashCode());
		result = prime * result
				+ ((tagNameEn == null) ? 0 : tagNameEn.hashCode());
		result = prime * result
				+ ((userCriteriaTag == null) ? 0 : userCriteriaTag.hashCode());
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
		CriteriaTagDTO other = (CriteriaTagDTO) obj;
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
		if (isFixed != other.isFixed)
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
		if (tagNameAr == null) {
			if (other.tagNameAr != null)
				return false;
		} else if (!tagNameAr.equals(other.tagNameAr))
			return false;
		if (tagNameEn == null) {
			if (other.tagNameEn != null)
				return false;
		} else if (!tagNameEn.equals(other.tagNameEn))
			return false;
		if (userCriteriaTag == null) {
			if (other.userCriteriaTag != null)
				return false;
		} else if (!userCriteriaTag.equals(other.userCriteriaTag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CriteriaTagDTO [criteriaTagId=" + criteriaTagId
				+ ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", tagNameAr=" + tagNameAr + ", tagNameEn="
				+ tagNameEn + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", isFixed=" + isFixed + ", userCriteriaTag="
				+ userCriteriaTag + "]";
	}

	
	
	
}
