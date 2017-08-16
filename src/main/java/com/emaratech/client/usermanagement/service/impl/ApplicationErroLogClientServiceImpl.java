package com.emaratech.client.usermanagement.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.service.ApplicationErroLogClientService;
import com.emaratech.client.usermanagement.tranferobject.ApplicationErrorLogDTO;

@Service
public class ApplicationErroLogClientServiceImpl implements ApplicationErroLogClientService{

	@Override
	public ListenableFuture<ApplicationErrorLogDTO> fetchApplicationErrorLogBasedonUserID(
			String userID) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
