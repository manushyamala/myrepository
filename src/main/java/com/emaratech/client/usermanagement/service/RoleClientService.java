package com.emaratech.client.usermanagement.service;

import java.util.List;

import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.tranferobject.RoleDTO;
import com.emaratech.client.usermanagement.tranferobject.RolePrivilegeListDTO;
import com.emaratech.client.usermanagement.tranferobject.SelectedRoleDTO;


public interface RoleClientService {
		
	@SuppressWarnings("rawtypes")
	public ListenableFuture<List> getAllRoles();
		
	@SuppressWarnings("rawtypes")
	public ListenableFuture<List> retreiveUserAssignedRole(List<RoleDTO> userAssignedRoleListDTO);
	
	public ListenableFuture<RolePrivilegeListDTO> fetchAllRoleForManagingUserRole(List<Long> adminPrivilegeList,String userID);
	
	public ListenableFuture<RolePrivilegeListDTO> fetchAllRoleForManagingRoles(List<Long> adminPrivilegeList,String userID);	
	
	public ListenableFuture<Void> updateManageRoleChanges(SelectedRoleDTO selectedRoleDTO,String userID);
	
}
