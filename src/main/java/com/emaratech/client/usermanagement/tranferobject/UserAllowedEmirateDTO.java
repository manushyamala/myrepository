package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class UserAllowedEmirateDTO implements Serializable{
	
	private static final long serialVersionUID = 2258681076719641303L;
	
	private long userAllowedEmirateId;
	private Long emiratesDepartmentId;
	private Long userId;
	private Boolean isForWork;
	private Boolean isForAdministration;
	private EmiratesDepartmentDTO emiratesDepartment;
	
	public UserAllowedEmirateDTO(){
		this.emiratesDepartment = new EmiratesDepartmentDTO();
	}
	
	public long getUserAllowedEmirateId() {
		return userAllowedEmirateId;
	}
	public void setUserAllowedEmirateId(long userAllowedEmirateId) {
		this.userAllowedEmirateId = userAllowedEmirateId;
	}
	public Long getEmiratesDepartmentId() {
		return emiratesDepartmentId;
	}
	public void setEmiratesDepartmentId(Long emiratesDepartmentId) {
		this.emiratesDepartmentId = emiratesDepartmentId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Boolean getIsForWork() {
		return isForWork;
	}
	public void setIsForWork(Boolean isForWork) {
		this.isForWork = isForWork;
	}
	public Boolean getIsForAdministration() {
		return isForAdministration;
	}
	public void setIsForAdministration(Boolean isForAdministration) {
		this.isForAdministration = isForAdministration;
	}
	public EmiratesDepartmentDTO getEmiratesDepartment() {
		return emiratesDepartment;
	}
	public void setEmiratesDepartment(EmiratesDepartmentDTO emiratesDepartment) {
		this.emiratesDepartment = emiratesDepartment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((emiratesDepartment == null) ? 0 : emiratesDepartment
						.hashCode());
		result = prime
				* result
				+ ((emiratesDepartmentId == null) ? 0 : emiratesDepartmentId
						.hashCode());
		result = prime
				* result
				+ ((isForAdministration == null) ? 0 : isForAdministration
						.hashCode());
		result = prime * result
				+ ((isForWork == null) ? 0 : isForWork.hashCode());
		result = prime * result
				+ (int) (userAllowedEmirateId ^ (userAllowedEmirateId >>> 32));
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserAllowedEmirateDTO other = (UserAllowedEmirateDTO) obj;
		if (emiratesDepartment == null) {
			if (other.emiratesDepartment != null)
				return false;
		} else if (!emiratesDepartment.equals(other.emiratesDepartment))
			return false;
		if (emiratesDepartmentId == null) {
			if (other.emiratesDepartmentId != null)
				return false;
		} else if (!emiratesDepartmentId.equals(other.emiratesDepartmentId))
			return false;
		if (isForAdministration == null) {
			if (other.isForAdministration != null)
				return false;
		} else if (!isForAdministration.equals(other.isForAdministration))
			return false;
		if (isForWork == null) {
			if (other.isForWork != null)
				return false;
		} else if (!isForWork.equals(other.isForWork))
			return false;
		if (userAllowedEmirateId != other.userAllowedEmirateId)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserAllowedEmirateDTO [userAllowedEmirateId="
				+ userAllowedEmirateId + ", emiratesDepartmentId="
				+ emiratesDepartmentId + ", userId=" + userId + ", isForWork="
				+ isForWork + ", isForAdministration=" + isForAdministration
				+ ", emiratesDepartment=" + emiratesDepartment + "]";
	}
	 
	
	
	

}
