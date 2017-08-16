package com.emaratech.client.usermanagement.service;

import java.util.List;

import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.tranferobject.RolePrivilegeDTOListHolder;

public interface RolePrivilegeClientService {

	public ListenableFuture<List> fetchRolePrivilegeForPrivilegeID(long privilegeID);
	
	public ListenableFuture<Void> updateRolePrivilegeChanges(RolePrivilegeDTOListHolder rolePrivilegeDTOListHolder);
	
	
}
