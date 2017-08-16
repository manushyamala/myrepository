package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.math.BigDecimal;


public class UserCriteriaTagDTO implements Serializable{

	private static final long serialVersionUID = 541629480358292481L;
	
	private long userCriteriaTagId;
	private CriteriaTagDTO criteriaTag;
	private ApplicationUserDTO applicationUser;
	private BigDecimal tagPriority;
	
	public UserCriteriaTagDTO(){
		this.criteriaTag = new CriteriaTagDTO();
		this.applicationUser = new ApplicationUserDTO();
	}

	public long getUserCriteriaTagId() {
		return userCriteriaTagId;
	}

	public void setUserCriteriaTagId(long userCriteriaTagId) {
		this.userCriteriaTagId = userCriteriaTagId;
	}

	public BigDecimal getTagPriority() {
		return tagPriority;
	}

	public void setTagPriority(BigDecimal tagPriority) {
		this.tagPriority = tagPriority;
	}

	public CriteriaTagDTO getCriteriaTag() {
		return criteriaTag;
	}

	public void setCriteriaTag(CriteriaTagDTO criteriaTag) {
		this.criteriaTag = criteriaTag;
	}

	public ApplicationUserDTO getApplicationUser() {
		return applicationUser;
	}

	public void setApplicationUser(ApplicationUserDTO applicationUser) {
		this.applicationUser = applicationUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationUser == null) ? 0 : applicationUser.hashCode());
		result = prime * result
				+ ((criteriaTag == null) ? 0 : criteriaTag.hashCode());
		result = prime * result
				+ ((tagPriority == null) ? 0 : tagPriority.hashCode());
		result = prime * result
				+ (int) (userCriteriaTagId ^ (userCriteriaTagId >>> 32));
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
		UserCriteriaTagDTO other = (UserCriteriaTagDTO) obj;
		if (applicationUser == null) {
			if (other.applicationUser != null)
				return false;
		} else if (!applicationUser.equals(other.applicationUser))
			return false;
		if (criteriaTag == null) {
			if (other.criteriaTag != null)
				return false;
		} else if (!criteriaTag.equals(other.criteriaTag))
			return false;
		if (tagPriority == null) {
			if (other.tagPriority != null)
				return false;
		} else if (!tagPriority.equals(other.tagPriority))
			return false;
		if (userCriteriaTagId != other.userCriteriaTagId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserCriteriaTagDTO [userCriteriaTagId=" + userCriteriaTagId
				+ ", criteriaTag=" + criteriaTag + ", applicationUser="
				+ applicationUser + ", tagPriority=" + tagPriority + "]";
	}
	
	
	
	

}
