package com.emaratech.client.usermanagement.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.tranferobject.RoleDTO;
import com.emaratech.client.usermanagement.tranferobject.UpdateRoleDTO;


public interface UserRoleClientService {
	
	
	public ListenableFuture<UpdateRoleDTO> getUserRole(long userID);
	
	public List<RoleDTO> removeUserAssignedRolesFromList(List<RoleDTO> userAssignedRoles, List<RoleDTO> completeRoleList);
	
	public HashMap<String,RoleDTO> createMapValueForRoleList(List<RoleDTO> completeRoleList,List<RoleDTO> userAssignedRoleListDTO);
	
	@SuppressWarnings("rawtypes")
	public ListenableFuture updateUserRoleChanges(UpdateRoleDTO updateRoleDTO);
	
	
	
	
	
}
