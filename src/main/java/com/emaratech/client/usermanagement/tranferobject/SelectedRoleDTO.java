package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class SelectedRoleDTO implements Serializable {


	private static final long serialVersionUID = 2647310026127816976L;
	
	private String roleName;
	private String descriptionEn;
	private String descriptionAr;
	private String remarks;
	private String departmentNameEn;
	private String departmentNameAr;
	private boolean active;
	private long selectedRoleID;
	private long departmentID;
	private long[] selectedPrivileges;
	
	public SelectedRoleDTO(){
		
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public String getDepartmentNameEn() {
		return departmentNameEn;
	}

	public void setDepartmentNameEn(String departmentNameEn) {
		this.departmentNameEn = departmentNameEn;
	}

	public String getDepartmentNameAr() {
		return departmentNameAr;
	}

	public void setDepartmentNameAr(String departmentNameAr) {
		this.departmentNameAr = departmentNameAr;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getSelectedRoleID() {
		return selectedRoleID;
	}

	public void setSelectedRoleID(long selectedRoleID) {
		this.selectedRoleID = selectedRoleID;
	}

	public long getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(long departmentID) {
		this.departmentID = departmentID;
	}

	public long[] getSelectedPrivileges() {
		return selectedPrivileges;
	}

	public void setSelectedPrivileges(long[] selectedPrivileges) {
		this.selectedPrivileges = selectedPrivileges;
	}
	
	
	
	
	
	
}
