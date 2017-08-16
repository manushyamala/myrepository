package com.emaratech.client.usermanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.tranferobject.AppUserDetailsDTO;
import com.emaratech.client.usermanagement.tranferobject.ApplicationUserDTO;
import com.emaratech.client.usermanagement.tranferobject.UserSearchDTO;



public interface UserManagementClientService {
	
	
	public ListenableFuture<ApplicationUserDTO> findUserBasedonLogin(String login);
	
	public ListenableFuture<ApplicationUserDTO> findUserBasedonUserID(String userId);
	
	public ListenableFuture<Void> updateUserDetails(ApplicationUserDTO userDTO);
	
	public ListenableFuture<ApplicationUserDTO> findUserBasedonSearchCriteria(UserSearchDTO userSearchDTO);	
	
	@SuppressWarnings("rawtypes")
	public ListenableFuture<List> findUserListBasedonSearchCriteria(UserSearchDTO userSearchDTO);
	
	public ResponseEntity<AppUserDetailsDTO>  loginUser(String userID); 
	
	
	
}
