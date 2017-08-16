package com.emaratech.client.usermanagement.service;

import java.util.List;

import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.tranferobject.ManageUserGroupDTO;
import com.emaratech.client.usermanagement.tranferobject.ManageUserGroupWrapperDTO;

public interface UserGroupClientService {

	public ListenableFuture<ManageUserGroupWrapperDTO> fetchManageUserGroupDetails(String userID,List<Long> adminPrivilegeList);
	
	public ListenableFuture<Void> updateManageUserGroupDetails(ManageUserGroupDTO manageUserGroupDTO);
	
	
}
