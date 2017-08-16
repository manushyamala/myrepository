package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.List;


public class RolePrivilegeListDTO implements Serializable{
	

	private static final long serialVersionUID = -5385202169626807320L;
	
	private long userID;
	private List<RoleDTO> roleDTOList;
	private List<PrivilegeDTO> privilegeListDTO;
	private List<LookupDTO> departmentLookupListDTO;
	
	
	public RolePrivilegeListDTO(){		
	}
	
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public List<RoleDTO> getRoleDTOList() {
		return roleDTOList;
	}
	public void setRoleDTOList(List<RoleDTO> roleDTOList) {
		this.roleDTOList = roleDTOList;
	}
	public List<PrivilegeDTO> getPrivilegeListDTO() {
		return privilegeListDTO;
	}
	public void setPrivilegeListDTO(List<PrivilegeDTO> privilegeDTO) {
		this.privilegeListDTO = privilegeDTO;
	}

	public List<LookupDTO> getDepartmentLookupListDTO() {
		return departmentLookupListDTO;
	}

	public void setDepartmentLookupListDTO(List<LookupDTO> departmentLookupListDTO) {
		this.departmentLookupListDTO = departmentLookupListDTO;
	}
	

}
