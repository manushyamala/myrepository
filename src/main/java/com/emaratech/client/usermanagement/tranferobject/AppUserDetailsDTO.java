package com.emaratech.client.usermanagement.tranferobject;

import java.util.List;


public class AppUserDetailsDTO {
	
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private List<String> authoritiesList;
	private long userID;
	private List<PrivilegeDTO> privilegeList;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public List<String> getAuthoritiesList() {
		return authoritiesList;
	}
	public void setAuthoritiesList(List<String> authoritiesList) {
		this.authoritiesList = authoritiesList;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public List<PrivilegeDTO> getPrivilegeList() {
		return privilegeList;
	}
	public void setPrivilegeList(List<PrivilegeDTO> privilegeList) {
		this.privilegeList = privilegeList;
	}

}
