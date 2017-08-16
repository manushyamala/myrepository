package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationUserDTO implements Serializable{
	

	private static final long serialVersionUID = 8726686000379443841L;
	
	private long userId;
	private String userLogin;
	private String userPassword;
	private String userName;
	private Date userExpiryDate;
	private Date passwordExpiryDate;
	private Boolean isReset;
	private Boolean isEnabled;
	private String phoneNo;
	private String mobileNo;
	private String email;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Set<UserRoleDTO> userRoles;
	private Set<UserPrivilegeDTO> userPrivileges;
	private Set<UserCriteriaTagDTO> userCriteriaTag;
	private Boolean canProcessUrgent;
	private EmployeeDTO employee;
	private Set<AppUserLoginAttemptDTO> appUserLoginAttempts;
	private UserGroupDTO userGroup;
	private Long oldEmployeeId;
	private Long portId;
	private Date lastAccessDate;
	private boolean selected;
	private String fullNameEn; 
	private String fullNameAr;
	private Boolean isSystemUser;
	private Long unifiedNumber;
	private Date userActivationDate;
	private EmirateDTO emirate;
	private List<UserAllowedEmirateDTO> userAllowedEmirates;
	
	public ApplicationUserDTO() {
		this.userRoles = new HashSet<UserRoleDTO>();
		this.userPrivileges = new HashSet<UserPrivilegeDTO>();
		this.userCriteriaTag = new HashSet<UserCriteriaTagDTO>();
		this.employee = new EmployeeDTO();
		this.appUserLoginAttempts = new HashSet<AppUserLoginAttemptDTO>();
		this.emirate = new EmirateDTO();
		this.userAllowedEmirates = new ArrayList<UserAllowedEmirateDTO>();
		
	}
		
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getUserExpiryDate() {
		return userExpiryDate;
	}
	public void setUserExpiryDate(Date userExpiryDate) {
		this.userExpiryDate = userExpiryDate;
	}
	public Date getPasswordExpiryDate() {
		return passwordExpiryDate;
	}
	public void setPasswordExpiryDate(Date passwordExpiryDate) {
		this.passwordExpiryDate = passwordExpiryDate;
	}
	public Boolean getIsReset() {
		return isReset;
	}
	public void setIsReset(Boolean isReset) {
		this.isReset = isReset;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Set<UserRoleDTO> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRoleDTO> userRoles) {
		this.userRoles = userRoles;
	}
	public Set<UserPrivilegeDTO> getUserPrivileges() {
		return userPrivileges;
	}
	public void setUserPrivileges(Set<UserPrivilegeDTO> userPrivileges) {
		this.userPrivileges = userPrivileges;
	}
	public Set<UserCriteriaTagDTO> getUserCriteriaTag() {
		return userCriteriaTag;
	}
	public void setUserCriteriaTag(Set<UserCriteriaTagDTO> userCriteriaTag) {
		this.userCriteriaTag = userCriteriaTag;
	}
	public Boolean getCanProcessUrgent() {
		return canProcessUrgent;
	}
	public void setCanProcessUrgent(Boolean canProcessUrgent) {
		this.canProcessUrgent = canProcessUrgent;
	}
	public EmployeeDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	public Set<AppUserLoginAttemptDTO> getAppUserLoginAttempts() {
		return appUserLoginAttempts;
	}
	public void setAppUserLoginAttempts(
			Set<AppUserLoginAttemptDTO> appUserLoginAttempts) {
		this.appUserLoginAttempts = appUserLoginAttempts;
	}
	public UserGroupDTO getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroupDTO userGroup) {
		this.userGroup = userGroup;
	}
	public Long getOldEmployeeId() {
		return oldEmployeeId;
	}
	public void setOldEmployeeId(Long oldEmployeeId) {
		this.oldEmployeeId = oldEmployeeId;
	}
	public Long getPortId() {
		return portId;
	}
	public void setPortId(Long portId) {
		this.portId = portId;
	}
	public Date getLastAccessDate() {
		return lastAccessDate;
	}
	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getFullNameEn() {
		return fullNameEn;
	}
	public void setFullNameEn(String fullNameEn) {
		this.fullNameEn = fullNameEn;
	}
	public String getFullNameAr() {
		return fullNameAr;
	}
	public void setFullNameAr(String fullNameAr) {
		this.fullNameAr = fullNameAr;
	}
	public Boolean getIsSystemUser() {
		return isSystemUser;
	}
	public void setIsSystemUser(Boolean isSystemUser) {
		this.isSystemUser = isSystemUser;
	}
	public Long getUnifiedNumber() {
		return unifiedNumber;
	}
	public void setUnifiedNumber(Long unifiedNumber) {
		this.unifiedNumber = unifiedNumber;
	}
	public Date getUserActivationDate() {
		return userActivationDate;
	}
	public void setUserActivationDate(Date userActivationDate) {
		this.userActivationDate = userActivationDate;
	}
	public EmirateDTO getEmirate() {
		return emirate;
	}
	public void setEmirate(EmirateDTO emirate) {
		this.emirate = emirate;
	}
	public List<UserAllowedEmirateDTO> getUserAllowedEmirates() {
		return userAllowedEmirates;
	}
	public void setUserAllowedEmirates(
			List<UserAllowedEmirateDTO> userAllowedEmirates) {
		this.userAllowedEmirates = userAllowedEmirates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((appUserLoginAttempts == null) ? 0 : appUserLoginAttempts
						.hashCode());
		result = prime
				* result
				+ ((canProcessUrgent == null) ? 0 : canProcessUrgent.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emirate == null) ? 0 : emirate.hashCode());
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result
				+ ((fullNameAr == null) ? 0 : fullNameAr.hashCode());
		result = prime * result
				+ ((fullNameEn == null) ? 0 : fullNameEn.hashCode());
		result = prime * result
				+ ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result + ((isReset == null) ? 0 : isReset.hashCode());
		result = prime * result
				+ ((isSystemUser == null) ? 0 : isSystemUser.hashCode());
		result = prime * result
				+ ((lastAccessDate == null) ? 0 : lastAccessDate.hashCode());
		result = prime * result
				+ ((mobileNo == null) ? 0 : mobileNo.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result
				+ ((oldEmployeeId == null) ? 0 : oldEmployeeId.hashCode());
		result = prime
				* result
				+ ((passwordExpiryDate == null) ? 0 : passwordExpiryDate
						.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((portId == null) ? 0 : portId.hashCode());
		result = prime * result + (selected ? 1231 : 1237);
		result = prime * result
				+ ((unifiedNumber == null) ? 0 : unifiedNumber.hashCode());
		result = prime
				* result
				+ ((userActivationDate == null) ? 0 : userActivationDate
						.hashCode());
		result = prime
				* result
				+ ((userAllowedEmirates == null) ? 0 : userAllowedEmirates
						.hashCode());
		result = prime * result
				+ ((userCriteriaTag == null) ? 0 : userCriteriaTag.hashCode());
		result = prime * result
				+ ((userExpiryDate == null) ? 0 : userExpiryDate.hashCode());
		result = prime * result
				+ ((userGroup == null) ? 0 : userGroup.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result
				+ ((userLogin == null) ? 0 : userLogin.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result
				+ ((userPrivileges == null) ? 0 : userPrivileges.hashCode());
		result = prime * result
				+ ((userRoles == null) ? 0 : userRoles.hashCode());
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
		ApplicationUserDTO other = (ApplicationUserDTO) obj;
		if (appUserLoginAttempts == null) {
			if (other.appUserLoginAttempts != null)
				return false;
		} else if (!appUserLoginAttempts.equals(other.appUserLoginAttempts))
			return false;
		if (canProcessUrgent == null) {
			if (other.canProcessUrgent != null)
				return false;
		} else if (!canProcessUrgent.equals(other.canProcessUrgent))
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emirate == null) {
			if (other.emirate != null)
				return false;
		} else if (!emirate.equals(other.emirate))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (fullNameAr == null) {
			if (other.fullNameAr != null)
				return false;
		} else if (!fullNameAr.equals(other.fullNameAr))
			return false;
		if (fullNameEn == null) {
			if (other.fullNameEn != null)
				return false;
		} else if (!fullNameEn.equals(other.fullNameEn))
			return false;
		if (isEnabled == null) {
			if (other.isEnabled != null)
				return false;
		} else if (!isEnabled.equals(other.isEnabled))
			return false;
		if (isReset == null) {
			if (other.isReset != null)
				return false;
		} else if (!isReset.equals(other.isReset))
			return false;
		if (isSystemUser == null) {
			if (other.isSystemUser != null)
				return false;
		} else if (!isSystemUser.equals(other.isSystemUser))
			return false;
		if (lastAccessDate == null) {
			if (other.lastAccessDate != null)
				return false;
		} else if (!lastAccessDate.equals(other.lastAccessDate))
			return false;
		if (mobileNo == null) {
			if (other.mobileNo != null)
				return false;
		} else if (!mobileNo.equals(other.mobileNo))
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
		if (oldEmployeeId == null) {
			if (other.oldEmployeeId != null)
				return false;
		} else if (!oldEmployeeId.equals(other.oldEmployeeId))
			return false;
		if (passwordExpiryDate == null) {
			if (other.passwordExpiryDate != null)
				return false;
		} else if (!passwordExpiryDate.equals(other.passwordExpiryDate))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (portId == null) {
			if (other.portId != null)
				return false;
		} else if (!portId.equals(other.portId))
			return false;
		if (selected != other.selected)
			return false;
		if (unifiedNumber == null) {
			if (other.unifiedNumber != null)
				return false;
		} else if (!unifiedNumber.equals(other.unifiedNumber))
			return false;
		if (userActivationDate == null) {
			if (other.userActivationDate != null)
				return false;
		} else if (!userActivationDate.equals(other.userActivationDate))
			return false;
		if (userAllowedEmirates == null) {
			if (other.userAllowedEmirates != null)
				return false;
		} else if (!userAllowedEmirates.equals(other.userAllowedEmirates))
			return false;
		if (userCriteriaTag == null) {
			if (other.userCriteriaTag != null)
				return false;
		} else if (!userCriteriaTag.equals(other.userCriteriaTag))
			return false;
		if (userExpiryDate == null) {
			if (other.userExpiryDate != null)
				return false;
		} else if (!userExpiryDate.equals(other.userExpiryDate))
			return false;
		if (userGroup == null) {
			if (other.userGroup != null)
				return false;
		} else if (!userGroup.equals(other.userGroup))
			return false;
		if (userId != other.userId)
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (userPrivileges == null) {
			if (other.userPrivileges != null)
				return false;
		} else if (!userPrivileges.equals(other.userPrivileges))
			return false;
		if (userRoles == null) {
			if (other.userRoles != null)
				return false;
		} else if (!userRoles.equals(other.userRoles))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApplicationUserDTO [userId=" + userId + ", userLogin="
				+ userLogin + ", userPassword=" + userPassword + ", userName="
				+ userName + ", userExpiryDate=" + userExpiryDate
				+ ", passwordExpiryDate=" + passwordExpiryDate + ", isReset="
				+ isReset + ", isEnabled=" + isEnabled + ", phoneNo=" + phoneNo
				+ ", mobileNo=" + mobileNo + ", email=" + email
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", userRoles=" + userRoles
				+ ", userPrivileges=" + userPrivileges + ", userCriteriaTag="
				+ userCriteriaTag + ", canProcessUrgent=" + canProcessUrgent
				+ ", employee=" + employee + ", appUserLoginAttempts="
				+ appUserLoginAttempts + ", userGroup=" + userGroup
				+ ", oldEmployeeId=" + oldEmployeeId + ", portId=" + portId
				+ ", lastAccessDate=" + lastAccessDate + ", selected="
				+ selected + ", fullNameEn=" + fullNameEn + ", fullNameAr="
				+ fullNameAr + ", isSystemUser=" + isSystemUser
				+ ", unifiedNumber=" + unifiedNumber + ", userActivationDate="
				+ userActivationDate + ", emirate=" + emirate
				+ ", userAllowedEmirates=" + userAllowedEmirates + "]";
	}
	
	
	
	
}
