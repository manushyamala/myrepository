package com.emaratech.client.usermanagement.service;

import org.springframework.http.ResponseEntity;

import com.emaratech.client.usermanagement.tranferobject.ApplicationMonitoringDTO;

public interface ApplicationConfigService {
	
	public ResponseEntity<ApplicationMonitoringDTO> retrieveApplicationMonitoringDetails();
	
	public ResponseEntity<String> retrieveCurrentLoggingLevel();
	
	public ResponseEntity<String> updateLoggingLevel(String logLevel);
	
	
	

}
