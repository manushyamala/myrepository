package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class UpdateRoleDTO implements Serializable{
	
	private static final long serialVersionUID = -5434297897421767834L;
	
	private List<RoleDTO> completeRoleListDTO;
	private List<RoleDTO> userAssignedRoleListDTO;
	private HashMap<String,RoleDTO> roleDTOHashMap;
	private String userID;
	
	public List<RoleDTO> getCompleteRoleListDTO() {
		return completeRoleListDTO;
	}
	public void setCompleteRoleListDTO(List<RoleDTO> completeRoleListDTO) {
		this.completeRoleListDTO = completeRoleListDTO;
	}
	public List<RoleDTO> getUserAssignedRoleListDTO() {
		return userAssignedRoleListDTO;
	}
	public void setUserAssignedRoleListDTO(List<RoleDTO> userAssignedRoleListDTO) {
		this.userAssignedRoleListDTO = userAssignedRoleListDTO;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public HashMap<String, RoleDTO> getRoleDTOHashMap() {
		return roleDTOHashMap;
	}
	public void setRoleDTOHashMap(HashMap<String, RoleDTO> roleDTOHashMap) {
		this.roleDTOHashMap = roleDTOHashMap;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((completeRoleListDTO == null) ? 0 : completeRoleListDTO
						.hashCode());
		result = prime * result
				+ ((roleDTOHashMap == null) ? 0 : roleDTOHashMap.hashCode());
		result = prime
				* result
				+ ((userAssignedRoleListDTO == null) ? 0
						: userAssignedRoleListDTO.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		UpdateRoleDTO other = (UpdateRoleDTO) obj;
		if (completeRoleListDTO == null) {
			if (other.completeRoleListDTO != null)
				return false;
		} else if (!completeRoleListDTO.equals(other.completeRoleListDTO))
			return false;
		if (roleDTOHashMap == null) {
			if (other.roleDTOHashMap != null)
				return false;
		} else if (!roleDTOHashMap.equals(other.roleDTOHashMap))
			return false;
		if (userAssignedRoleListDTO == null) {
			if (other.userAssignedRoleListDTO != null)
				return false;
		} else if (!userAssignedRoleListDTO
				.equals(other.userAssignedRoleListDTO))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UpdateRoleDTO [completeRoleListDTO=" + completeRoleListDTO
				+ ", userAssignedRoleListDTO=" + userAssignedRoleListDTO
				+ ", roleDTOHashMap=" + roleDTOHashMap + ", userID=" + userID
				+ "]";
	}
	
	
	

}
