package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;


public class AdminPrivilegeEmiratesDepartmentDTO implements Serializable{
	
	private static final long serialVersionUID = 376057089755642214L;
	
	private long id;
    private PrivilegeDTO privilege ;
    private EmiratesDepartmentDTO emiratesDepartment;
    
    public AdminPrivilegeEmiratesDepartmentDTO(){
    	this.privilege = new PrivilegeDTO();
    	this.emiratesDepartment = new EmiratesDepartmentDTO();
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PrivilegeDTO getPrivilege() {
		return privilege;
	}
	public void setPrivilege(PrivilegeDTO privilege) {
		this.privilege = privilege;
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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((privilege == null) ? 0 : privilege.hashCode());
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
		AdminPrivilegeEmiratesDepartmentDTO other = (AdminPrivilegeEmiratesDepartmentDTO) obj;
		if (emiratesDepartment == null) {
			if (other.emiratesDepartment != null)
				return false;
		} else if (!emiratesDepartment.equals(other.emiratesDepartment))
			return false;
		if (id != other.id)
			return false;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdminPrivilegeEmiratesDepartmentDTO [id=" + id + ", privilege="
				+ privilege + ", emiratesDepartment=" + emiratesDepartment
				+ "]";
	}
    
	
	
	
	
    

}
