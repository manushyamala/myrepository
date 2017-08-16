package com.emaratech.client.usermanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.common.RestURLGenertorHelper;
import com.emaratech.client.usermanagement.common.UserManagementConstants;
import com.emaratech.client.usermanagement.common.UserManagementUtils;
import com.emaratech.client.usermanagement.service.RolePrivilegeClientService;
import com.emaratech.client.usermanagement.tranferobject.ApplicationUserDTO;
import com.emaratech.client.usermanagement.tranferobject.RolePrivilegeDTOListHolder;
import com.emaratech.common.async.GenericDTOAsyncAdapter;
import com.emaratech.security.oauth.client.AsyncRestServiceHelper;

@Service
public class RolePrivilegeClientServiceImpl implements RolePrivilegeClientService{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AsyncRestServiceHelper restServiceHelper;
	
	@Autowired
	private RestURLGenertorHelper restURLGenertorHelper;
	
	@SuppressWarnings("rawtypes")
	public ListenableFuture<List> fetchRolePrivilegeForPrivilegeID(long privilegeID){
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getRolePrivilegePath());
		StringBuilder queryParamBuilder = new StringBuilder();
		queryParamBuilder.append(UserManagementConstants.QUERY_PARAM_START_SYMBOL);	
		queryParamBuilder.append(UserManagementConstants.PRIVILEGE_ID_QUERY_PARAM).append(privilegeID);
		searchURL = new StringBuilder().append(searchURL).append(queryParamBuilder.toString()).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<List>> rolePrivilegeListDTO = restServiceHelper.asyncRestTemplate().exchange(searchURL,HttpMethod.GET, requestEntity, List.class);
		return new GenericDTOAsyncAdapter<List>(rolePrivilegeListDTO);
	}

	@Override
	public ListenableFuture<Void> updateRolePrivilegeChanges(RolePrivilegeDTOListHolder rolePrivilegeDTOListHolder) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getRolePrivilegePath());
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<RolePrivilegeDTOListHolder> request = new HttpEntity<RolePrivilegeDTOListHolder>(rolePrivilegeDTOListHolder,multiValueMap);
		ListenableFuture<ResponseEntity<Void>> rolePrivilegeUdpateResource = restServiceHelper.asyncRestTemplate().postForEntity(searchURL, request, Void.class);
		return new GenericDTOAsyncAdapter<Void>(rolePrivilegeUdpateResource);
	}

}
