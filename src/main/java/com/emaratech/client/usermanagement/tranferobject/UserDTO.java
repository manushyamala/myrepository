package com.emaratech.client.usermanagement.tranferobject;

import java.util.Date;

public class UserDTO {
	
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
	private Date lastAccessDate;
	private Date userActivationDate;
	private String rank;
	private String employeeNumber;
	private String shift;
	private String gender;
	private String education;
	private Date dateOfBirth;
	private String firstNameEn;
	private String middleNameEn;
	private String lastNameEn;
	private String firstNameAr;
	private String middleNameAr;
	private String lastNameAr;
	private Long jobTitleId;
	private Long hrTitleId;
	private Long sectorId;
	private Long branchId;
	private Long departmentId;
	private Long blackListDepartmentId;
	private Long blackListSectorId;
	private Long militaryNo;
	

	
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
	public Date getLastAccessDate() {
		return lastAccessDate;
	}
	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	public Date getUserActivationDate() {
		return userActivationDate;
	}
	public void setUserActivationDate(Date userActivationDate) {
		this.userActivationDate = userActivationDate;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFirstNameEn() {
		return firstNameEn;
	}
	public void setFirstNameEn(String firstNameEn) {
		this.firstNameEn = firstNameEn;
	}
	public String getMiddleNameEn() {
		return middleNameEn;
	}
	public void setMiddleNameEn(String middleNameEn) {
		this.middleNameEn = middleNameEn;
	}
	public String getLastNameEn() {
		return lastNameEn;
	}
	public void setLastNameEn(String lastNameEn) {
		this.lastNameEn = lastNameEn;
	}
	public String getFirstNameAr() {
		return firstNameAr;
	}
	public void setFirstNameAr(String firstNameAr) {
		this.firstNameAr = firstNameAr;
	}
	public String getMiddleNameAr() {
		return middleNameAr;
	}
	public void setMiddleNameAr(String middleNameAr) {
		this.middleNameAr = middleNameAr;
	}
	public String getLastNameAr() {
		return lastNameAr;
	}
	public void setLastNameAr(String lastNameAr) {
		this.lastNameAr = lastNameAr;
	}
	public Long getJobTitleId() {
		return jobTitleId;
	}
	public void setJobTitleId(Long jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	public Long getHrTitleId() {
		return hrTitleId;
	}
	public void setHrTitleId(Long hrTitleId) {
		this.hrTitleId = hrTitleId;
	}
	public Long getSectorId() {
		return sectorId;
	}
	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getBlackListDepartmentId() {
		return blackListDepartmentId;
	}
	public void setBlackListDepartmentId(Long blackListDepartmentId) {
		this.blackListDepartmentId = blackListDepartmentId;
	}
	public Long getBlackListSectorId() {
		return blackListSectorId;
	}
	public void setBlackListSectorId(Long blackListSectorId) {
		this.blackListSectorId = blackListSectorId;
	}
	public Long getMilitaryNo() {
		return militaryNo;
	}
	public void setMilitaryNo(Long militaryNo) {
		this.militaryNo = militaryNo;
	}	

}
