package com.emaratech.client.usermanagement.service;

import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.tranferobject.ApplicationErrorLogDTO;



public interface ApplicationErroLogClientService {
	
	
	public ListenableFuture<ApplicationErrorLogDTO> fetchApplicationErrorLogBasedonUserID(String userID);
	
	

}
