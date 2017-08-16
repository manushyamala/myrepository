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
import com.emaratech.client.usermanagement.service.RoleGroupClientService;
import com.emaratech.client.usermanagement.tranferobject.ApplicationUserDTO;
import com.emaratech.client.usermanagement.tranferobject.ManageRoleGroupDTO;
import com.emaratech.client.usermanagement.tranferobject.ManageRoleGroupWrapperDTO;
import com.emaratech.common.async.GenericDTOAsyncAdapter;
import com.emaratech.security.oauth.client.AsyncRestServiceHelper;

@Service
public class RoleGroupClientServiceImpl implements RoleGroupClientService {

	@Autowired
	private Environment env;
	
	@Autowired
	private AsyncRestServiceHelper restServiceHelper;
	
	@Autowired
	private RestURLGenertorHelper restURLGenertorHelper;
	
	@Override
	public ListenableFuture<ManageRoleGroupWrapperDTO> fetchRoleGroupDetails(List<Long> adminPrivilegeList, String userID) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getRoleGroupSearchPath());
		StringBuilder queryParamBuilder = new StringBuilder();
		queryParamBuilder.append(UserManagementConstants.QUERY_PARAM_START_SYMBOL);	
		queryParamBuilder.append(UserManagementConstants.USER_ID_QUERY_PARAM).append(userID);
		searchURL = new StringBuilder().append(searchURL).append(queryParamBuilder.toString()).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<List<Long>> request = new HttpEntity<>(adminPrivilegeList,multiValueMap);
		ListenableFuture<ResponseEntity<ManageRoleGroupWrapperDTO>> manageRoleGroupWrapperDTOResource = restServiceHelper.asyncRestTemplate().postForEntity(searchURL, request, ManageRoleGroupWrapperDTO.class);
		return new GenericDTOAsyncAdapter<ManageRoleGroupWrapperDTO>(manageRoleGroupWrapperDTOResource);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ListenableFuture<List> fetchRoleGroupDetailsForSelectedGroupID(long roleGroupID) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getRoleGroupPath());
		searchURL = new StringBuilder().append(searchURL).append(UserManagementConstants.SLASH_SYMBOL).append(roleGroupID).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<List>> userList = restServiceHelper.asyncRestTemplate().exchange(searchURL, HttpMethod.GET, requestEntity, List.class);		
		return new GenericDTOAsyncAdapter<List>(userList);
	}
	
	
	public ListenableFuture<Void> updateManageRoleGroupDetails(ManageRoleGroupDTO manageRoleGroupDTO){
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getRoleGroupPath());
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ManageRoleGroupDTO> request = new HttpEntity<ManageRoleGroupDTO>(manageRoleGroupDTO,multiValueMap);
		ListenableFuture<ResponseEntity<Void>> updateManageRoleGroupDetails = restServiceHelper.asyncRestTemplate()
				.postForEntity(searchURL, request, Void.class);
		return new GenericDTOAsyncAdapter<Void>(updateManageRoleGroupDetails);
		
	}
	
	

}
