package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class EmployeeDTO implements Serializable{
	
	private static final long serialVersionUID = 6887678096121627460L;
	private long id;
	private String rank;
	private String employeeNumber;
	private String shift;
	private long signatureDocRefId;
	private GenderDTO gender;
	private EmiratesDepartmentDTO emiratesDepartment;
	private EducationDTO education;
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
	private Set<EmployeeSectionDTO> allowedSection;
	private Long branchId;
	private Long departmentId;
	private Long blackListDepartmentId;
	private Long blackListSectorId;
	private EmployeeRankDTO employeeRank;
	private EmploymentTypeDTO employmentType;
	private Long militaryNo;
	
	
	public EmployeeDTO(){
		this.gender = new GenderDTO();
		this.emiratesDepartment = new EmiratesDepartmentDTO();
		this.allowedSection = new HashSet<EmployeeSectionDTO>();
		this.employeeRank = new EmployeeRankDTO();
		this.employmentType = new EmploymentTypeDTO();		
		this.education = new EducationDTO();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public long getSignatureDocRefId() {
		return signatureDocRefId;
	}
	public void setSignatureDocRefId(long signatureDocRefId) {
		this.signatureDocRefId = signatureDocRefId;
	}
	public GenderDTO getGender() {
		return gender;
	}
	public void setGender(GenderDTO gender) {
		this.gender = gender;
	}
	public EmiratesDepartmentDTO getEmiratesDepartment() {
		return emiratesDepartment;
	}
	public void setEmiratesDepartment(EmiratesDepartmentDTO emiratesDepartment) {
		this.emiratesDepartment = emiratesDepartment;
	}
	public EducationDTO getEducation() {
		return education;
	}
	public void setEducation(EducationDTO education) {
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
	public Set<EmployeeSectionDTO> getAllowedSection() {
		return allowedSection;
	}
	public void setAllowedSection(Set<EmployeeSectionDTO> allowedSection) {
		this.allowedSection = allowedSection;
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
	public EmployeeRankDTO getEmployeeRank() {
		return employeeRank;
	}
	public void setEmployeeRank(EmployeeRankDTO employeeRank) {
		this.employeeRank = employeeRank;
	}
	public EmploymentTypeDTO getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(EmploymentTypeDTO employmentType) {
		this.employmentType = employmentType;
	}
	public Long getMilitaryNo() {
		return militaryNo;
	}
	public void setMilitaryNo(Long militaryNo) {
		this.militaryNo = militaryNo;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allowedSection == null) ? 0 : allowedSection.hashCode());
		result = prime
				* result
				+ ((blackListDepartmentId == null) ? 0 : blackListDepartmentId
						.hashCode());
		result = prime
				* result
				+ ((blackListSectorId == null) ? 0 : blackListSectorId
						.hashCode());
		result = prime * result
				+ ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result
				+ ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result
				+ ((education == null) ? 0 : education.hashCode());
		result = prime
				* result
				+ ((emiratesDepartment == null) ? 0 : emiratesDepartment
						.hashCode());
		result = prime * result
				+ ((employeeNumber == null) ? 0 : employeeNumber.hashCode());
		result = prime * result
				+ ((employeeRank == null) ? 0 : employeeRank.hashCode());
		result = prime * result
				+ ((employmentType == null) ? 0 : employmentType.hashCode());
		result = prime * result
				+ ((firstNameAr == null) ? 0 : firstNameAr.hashCode());
		result = prime * result
				+ ((firstNameEn == null) ? 0 : firstNameEn.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((hrTitleId == null) ? 0 : hrTitleId.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((jobTitleId == null) ? 0 : jobTitleId.hashCode());
		result = prime * result
				+ ((lastNameAr == null) ? 0 : lastNameAr.hashCode());
		result = prime * result
				+ ((lastNameEn == null) ? 0 : lastNameEn.hashCode());
		result = prime * result
				+ ((middleNameAr == null) ? 0 : middleNameAr.hashCode());
		result = prime * result
				+ ((middleNameEn == null) ? 0 : middleNameEn.hashCode());
		result = prime * result
				+ ((militaryNo == null) ? 0 : militaryNo.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result
				+ ((sectorId == null) ? 0 : sectorId.hashCode());
		result = prime * result + ((shift == null) ? 0 : shift.hashCode());
		result = prime * result
				+ (int) (signatureDocRefId ^ (signatureDocRefId >>> 32));
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
		EmployeeDTO other = (EmployeeDTO) obj;
		if (allowedSection == null) {
			if (other.allowedSection != null)
				return false;
		} else if (!allowedSection.equals(other.allowedSection))
			return false;
		if (blackListDepartmentId == null) {
			if (other.blackListDepartmentId != null)
				return false;
		} else if (!blackListDepartmentId.equals(other.blackListDepartmentId))
			return false;
		if (blackListSectorId == null) {
			if (other.blackListSectorId != null)
				return false;
		} else if (!blackListSectorId.equals(other.blackListSectorId))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		if (education == null) {
			if (other.education != null)
				return false;
		} else if (!education.equals(other.education))
			return false;
		if (emiratesDepartment == null) {
			if (other.emiratesDepartment != null)
				return false;
		} else if (!emiratesDepartment.equals(other.emiratesDepartment))
			return false;
		if (employeeNumber == null) {
			if (other.employeeNumber != null)
				return false;
		} else if (!employeeNumber.equals(other.employeeNumber))
			return false;
		if (employeeRank == null) {
			if (other.employeeRank != null)
				return false;
		} else if (!employeeRank.equals(other.employeeRank))
			return false;
		if (employmentType == null) {
			if (other.employmentType != null)
				return false;
		} else if (!employmentType.equals(other.employmentType))
			return false;
		if (firstNameAr == null) {
			if (other.firstNameAr != null)
				return false;
		} else if (!firstNameAr.equals(other.firstNameAr))
			return false;
		if (firstNameEn == null) {
			if (other.firstNameEn != null)
				return false;
		} else if (!firstNameEn.equals(other.firstNameEn))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (hrTitleId == null) {
			if (other.hrTitleId != null)
				return false;
		} else if (!hrTitleId.equals(other.hrTitleId))
			return false;
		if (id != other.id)
			return false;
		if (jobTitleId == null) {
			if (other.jobTitleId != null)
				return false;
		} else if (!jobTitleId.equals(other.jobTitleId))
			return false;
		if (lastNameAr == null) {
			if (other.lastNameAr != null)
				return false;
		} else if (!lastNameAr.equals(other.lastNameAr))
			return false;
		if (lastNameEn == null) {
			if (other.lastNameEn != null)
				return false;
		} else if (!lastNameEn.equals(other.lastNameEn))
			return false;
		if (middleNameAr == null) {
			if (other.middleNameAr != null)
				return false;
		} else if (!middleNameAr.equals(other.middleNameAr))
			return false;
		if (middleNameEn == null) {
			if (other.middleNameEn != null)
				return false;
		} else if (!middleNameEn.equals(other.middleNameEn))
			return false;
		if (militaryNo == null) {
			if (other.militaryNo != null)
				return false;
		} else if (!militaryNo.equals(other.militaryNo))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		if (sectorId == null) {
			if (other.sectorId != null)
				return false;
		} else if (!sectorId.equals(other.sectorId))
			return false;
		if (shift == null) {
			if (other.shift != null)
				return false;
		} else if (!shift.equals(other.shift))
			return false;
		if (signatureDocRefId != other.signatureDocRefId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", rank=" + rank + ", employeeNumber="
				+ employeeNumber + ", shift=" + shift + ", signatureDocRefId="
				+ signatureDocRefId + ", gender=" + gender
				+ ", emiratesDepartment=" + emiratesDepartment + ", education="
				+ education + ", dateOfBirth=" + dateOfBirth + ", firstNameEn="
				+ firstNameEn + ", middleNameEn=" + middleNameEn
				+ ", lastNameEn=" + lastNameEn + ", firstNameAr=" + firstNameAr
				+ ", middleNameAr=" + middleNameAr + ", lastNameAr="
				+ lastNameAr + ", jobTitleId=" + jobTitleId + ", hrTitleId="
				+ hrTitleId + ", sectorId=" + sectorId + ", allowedSection="
				+ allowedSection + ", branchId=" + branchId + ", departmentId="
				+ departmentId + ", blackListDepartmentId="
				+ blackListDepartmentId + ", blackListSectorId="
				+ blackListSectorId + ", employeeRank=" + employeeRank
				+ ", employmentType=" + employmentType + ", militaryNo="
				+ militaryNo + "]";
	}

	
	
	
	
	
}
