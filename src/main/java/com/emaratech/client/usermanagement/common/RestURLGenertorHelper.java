package com.emaratech.client.usermanagement.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestURLGenertorHelper {
	
	
	private String serviceHost = null;

	// If service.host is set as a system property, the host name will be taken
	// from this property otherwise the service.host property defined in the
	// service_config.properties will be taken.
	@PostConstruct
	public void initialize() {
		serviceHost = System.getProperty("userservice.host");
		if (serviceHost == null || serviceHost.isEmpty()) {
			serviceHost = hostName;
		}
		serviceHost = serviceHost.trim();
	}


	@Value("${userservice.host}")
	private String hostName;
	
	@Value("${service.uri}")
	private String serviceURI;

	@Value("${usergroup.path}")
	private String userGroupPath;

	@Value("${criteriatag.path}")
	private String criteriaTagPath;

	@Value("${user.path}")
	private String userPath;

	@Value("${user.search.path}")
	private String userSearchPath;
	
	@Value("${role.path}")
	private String rolePath;
	
	@Value("${role.search.path}")
	private String roleSearchPath;

	@Value("${privilege.path}")
	private String privilegePath;
	
	@Value("${userrole.path}")
	private String userRolePath;
	
	@Value("${roleprivilege.path}")
	private String rolePrivilegePath;
	
	@Value("${userrole.search.path}")
	private String userRoleSearchPath;
	
	@Value("${usercriteriatag.path}")
	private String userCriteriaTagPath;
	
	@Value("${usergroup.search.path}")
	private String userGroupSearchPath;
	
	@Value("${rolegroup.search.path}")
	private String roleGroupSearchPath;

	@Value("${rolegroup.path}")
	private String roleGroupPath;
		
	

	public String getUserPath() {
		return userPath;
	}

	public String getRolePath() {
		return rolePath;
	}

	public String getPrivilegePath() {
		return privilegePath;
	}

	public String getUserGroupPath() {
		return userGroupPath;
	}

	public String getCriteriaTagPath() {
		return criteriaTagPath;
	}

	public String getUserSearchPath() {
		return userSearchPath;
	}

	public String getRoleSearchPath() {
		return roleSearchPath;
	}

	public String getUserRolePath() {
		return userRolePath;
	}

	public String urlForPath(String restPath) {
		return new StringBuilder().append(getServiceURI()).append(restPath).toString();
	}

	public String getServiceURI() {
		return serviceHost + serviceURI;
	}

	public String getRolePrivilegePath() {
		return rolePrivilegePath;
	}

	public String getUserRoleSearchPath() {
		return userRoleSearchPath;
	}

	public String getUserCriteriaTagPath() {
		return userCriteriaTagPath;
	}

	public String getUserGroupSearchPath() {
		return userGroupSearchPath;
	}

	public String getRoleGroupSearchPath() {
		return roleGroupSearchPath;
	}

	public String getRoleGroupPath() {
		return roleGroupPath;
	}


}
