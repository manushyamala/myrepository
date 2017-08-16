package com.emaratech.client.usermanagement.service;

import java.util.List;

import org.springframework.util.concurrent.ListenableFuture;

public interface PrivilegeClientService {

	@SuppressWarnings("rawtypes")
	public ListenableFuture<List> getPrivilegeForRoleID(long roleID);
}
