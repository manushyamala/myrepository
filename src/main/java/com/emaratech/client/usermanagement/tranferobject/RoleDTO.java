package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RoleDTO implements Serializable{

	private static final long serialVersionUID = -8227926395920085324L;
	
	private long roleId;
	private String roleName;
	private Long departmentId;
	private Boolean canEscalate;
	private RoleDTO role;
	private Set<UserRoleDTO> userRoles;
	private Set<RolePrivilegeDTO> rolePrivileges;
	private Integer authorityLevel;
	private String descriptionEn;
	private String descriptionAr;
	private String remarks;
	private boolean isActive;
	private boolean isSystemRole;
	private boolean selected;
	
	public RoleDTO(){
		this.userRoles = new HashSet<UserRoleDTO>();
		this.rolePrivileges = new HashSet<RolePrivilegeDTO>();
	}
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(RoleDTO role) {
		this.role = role;
	}
	public Boolean getCanEscalate() {
		return canEscalate;
	}
	public void setCanEscalate(Boolean canEscalate) {
		this.canEscalate = canEscalate;
	}
	public Set<UserRoleDTO> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRoleDTO> userRoles) {
		this.userRoles = userRoles;
	}
	public Set<RolePrivilegeDTO> getRolePrivileges() {
		return rolePrivileges;
	}
	public void setRolePrivileges(Set<RolePrivilegeDTO> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}
	public Integer getAuthorityLevel() {
		return authorityLevel;
	}
	public void setAuthorityLevel(Integer authorityLevel) {
		this.authorityLevel = authorityLevel;
	}
	public String getDescriptionEn() {
		return descriptionEn;
	}
	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}
	public String getDescriptionAr() {
		return descriptionAr;
	}
	public void setDescriptionAr(String descriptionAr) {
		this.descriptionAr = descriptionAr;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isSystemRole() {
		return isSystemRole;
	}
	public void setSystemRole(boolean isSystemRole) {
		this.isSystemRole = isSystemRole;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	
	
	
	
	
	

}
