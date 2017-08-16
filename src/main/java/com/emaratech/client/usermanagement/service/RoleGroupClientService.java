package com.emaratech.client.usermanagement.service;

import java.util.List;

import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.tranferobject.ManageRoleGroupDTO;
import com.emaratech.client.usermanagement.tranferobject.ManageRoleGroupWrapperDTO;

public interface RoleGroupClientService {
	
	public ListenableFuture<ManageRoleGroupWrapperDTO> fetchRoleGroupDetails(List<Long> adminPrivilegeList,String userID);
	
	@SuppressWarnings("rawtypes")
	public ListenableFuture<List> fetchRoleGroupDetailsForSelectedGroupID(long roleGroupID);
	
	public ListenableFuture<Void> updateManageRoleGroupDetails(ManageRoleGroupDTO manageRoleGroupDTO);
	
	
	
	

}
