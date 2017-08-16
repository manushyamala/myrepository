package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class UserSearchDTO implements Serializable{
	
	private static final long serialVersionUID = -6351731158717403899L;
	
	private String name;
	private String loginName;
	private String mobileNo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result
				+ ((mobileNo == null) ? 0 : mobileNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		UserSearchDTO other = (UserSearchDTO) obj;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (mobileNo == null) {
			if (other.mobileNo != null)
				return false;
		} else if (!mobileNo.equals(other.mobileNo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UserSearchDTO [name=" + name + ", loginName=" + loginName
				+ ", mobileNo=" + mobileNo + "]";
	}

}
