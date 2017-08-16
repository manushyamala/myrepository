package com.emaratech.client.usermanagement.service;

import java.util.List;

import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.tranferobject.CriteriaTagWrapperDTO;
import com.emaratech.client.usermanagement.tranferobject.RolePrivilegeDTOListHolder;

public interface UserGroupCriteriaService {
	
	@SuppressWarnings("rawtypes")
	public ListenableFuture<List> fetchAllUserGroups();
	
	public ListenableFuture<CriteriaTagWrapperDTO> fetchCriteriaTagsForUserGroupID(String userGroupID);
	
	public ListenableFuture<Void> updateUserGroupCriteriaTagChanges(CriteriaTagWrapperDTO criteriaTagWrapperDTO,String userID,String userGroupID);
	
	

}
