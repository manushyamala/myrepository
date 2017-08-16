package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.Date;

public class AppUserLoginAttemptDTO implements Serializable {
	
	
	private static final long serialVersionUID = -8226677171800537995L;
	
	private long id;
	private Date loginAttemptDateTime;
	private Long userSystemInfoId;
	private UserSystemInfoDTO userSystemInfo;
	private Boolean isSuccessful;
	private AppUserLoginFailedReasonDTO appUserLoginFailedReason;
	private ApplicationUserDTO applicationUser;
	
	public AppUserLoginAttemptDTO(){
		this.userSystemInfo = new UserSystemInfoDTO();
		this.appUserLoginFailedReason = new AppUserLoginFailedReasonDTO();
		this.applicationUser = new ApplicationUserDTO();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getLoginAttemptDateTime() {
		return loginAttemptDateTime;
	}
	public void setLoginAttemptDateTime(Date loginAttemptDateTime) {
		this.loginAttemptDateTime = loginAttemptDateTime;
	}
	public Long getUserSystemInfoId() {
		return userSystemInfoId;
	}
	public void setUserSystemInfoId(Long userSystemInfoId) {
		this.userSystemInfoId = userSystemInfoId;
	}
	public UserSystemInfoDTO getUserSystemInfo() {
		return userSystemInfo;
	}
	public void setUserSystemInfo(UserSystemInfoDTO userSystemInfo) {
		this.userSystemInfo = userSystemInfo;
	}
	public Boolean getIsSuccessful() {
		return isSuccessful;
	}
	public void setIsSuccessful(Boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	public AppUserLoginFailedReasonDTO getAppUserLoginFailedReason() {
		return appUserLoginFailedReason;
	}
	public void setAppUserLoginFailedReason(
			AppUserLoginFailedReasonDTO appUserLoginFailedReason) {
		this.appUserLoginFailedReason = appUserLoginFailedReason;
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
		result = prime
				* result
				+ ((appUserLoginFailedReason == null) ? 0
						: appUserLoginFailedReason.hashCode());
		result = prime * result
				+ ((applicationUser == null) ? 0 : applicationUser.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((isSuccessful == null) ? 0 : isSuccessful.hashCode());
		result = prime
				* result
				+ ((loginAttemptDateTime == null) ? 0 : loginAttemptDateTime
						.hashCode());
		result = prime * result
				+ ((userSystemInfo == null) ? 0 : userSystemInfo.hashCode());
		result = prime
				* result
				+ ((userSystemInfoId == null) ? 0 : userSystemInfoId.hashCode());
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
		AppUserLoginAttemptDTO other = (AppUserLoginAttemptDTO) obj;
		if (appUserLoginFailedReason == null) {
			if (other.appUserLoginFailedReason != null)
				return false;
		} else if (!appUserLoginFailedReason
				.equals(other.appUserLoginFailedReason))
			return false;
		if (applicationUser == null) {
			if (other.applicationUser != null)
				return false;
		} else if (!applicationUser.equals(other.applicationUser))
			return false;
		if (id != other.id)
			return false;
		if (isSuccessful == null) {
			if (other.isSuccessful != null)
				return false;
		} else if (!isSuccessful.equals(other.isSuccessful))
			return false;
		if (loginAttemptDateTime == null) {
			if (other.loginAttemptDateTime != null)
				return false;
		} else if (!loginAttemptDateTime.equals(other.loginAttemptDateTime))
			return false;
		if (userSystemInfo == null) {
			if (other.userSystemInfo != null)
				return false;
		} else if (!userSystemInfo.equals(other.userSystemInfo))
			return false;
		if (userSystemInfoId == null) {
			if (other.userSystemInfoId != null)
				return false;
		} else if (!userSystemInfoId.equals(other.userSystemInfoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppUserLoginAttemptDTO [id=" + id + ", loginAttemptDateTime="
				+ loginAttemptDateTime + ", userSystemInfoId="
				+ userSystemInfoId + ", userSystemInfo=" + userSystemInfo
				+ ", isSuccessful=" + isSuccessful
				+ ", appUserLoginFailedReason=" + appUserLoginFailedReason
				+ ", applicationUser=" + applicationUser + "]";
	}


	
	



}
