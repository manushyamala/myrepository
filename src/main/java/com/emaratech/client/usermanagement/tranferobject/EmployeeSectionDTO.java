package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class EmployeeSectionDTO implements Serializable{

	private static final long serialVersionUID = -1593039170624047265L;
	
	private long id;
    private EmployeeDTO employee;
    private SectionDTO section;
    
    public EmployeeSectionDTO(){
    	this.employee = new EmployeeDTO();
    	this.section =  new SectionDTO();
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public EmployeeDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	public SectionDTO getSection() {
		return section;
	}
	public void setSection(SectionDTO section) {
		this.section = section;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		EmployeeSectionDTO other = (EmployeeSectionDTO) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id != other.id)
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeSectionDTO [id=" + id + ", employee=" + employee
				+ ", section=" + section + "]";
	}

}
