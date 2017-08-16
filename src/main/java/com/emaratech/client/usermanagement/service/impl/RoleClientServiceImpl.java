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
import com.emaratech.client.usermanagement.service.RoleClientService;
import com.emaratech.client.usermanagement.tranferobject.ApplicationUserDTO;
import com.emaratech.client.usermanagement.tranferobject.RoleDTO;
import com.emaratech.client.usermanagement.tranferobject.RolePrivilegeListDTO;
import com.emaratech.client.usermanagement.tranferobject.SelectedRoleDTO;
import com.emaratech.common.async.GenericDTOAsyncAdapter;
import com.emaratech.security.oauth.client.AsyncRestServiceHelper;



@Service
public class RoleClientServiceImpl implements RoleClientService {

	@Autowired
	private Environment env;
	
	@Autowired
	private AsyncRestServiceHelper restServiceHelper;
	
	@Autowired
	private RestURLGenertorHelper restURLGenertorHelper;
	
	@SuppressWarnings("rawtypes")
	@Override
	public ListenableFuture<List> getAllRoles() {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserSearchPath());
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<List>> userList = restServiceHelper.asyncRestTemplate().exchange(searchURL, HttpMethod.GET, requestEntity, List.class);		
		return new GenericDTOAsyncAdapter<List>(userList);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ListenableFuture<List> retreiveUserAssignedRole(List<RoleDTO> userAssignedRoleListDTO) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserSearchPath());
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<List<RoleDTO>> request = new HttpEntity<>(userAssignedRoleListDTO,multiValueMap);
		ListenableFuture<ResponseEntity<List>> userAssignedList = restServiceHelper.asyncRestTemplate().postForEntity(searchURL, request, List.class);
		return new GenericDTOAsyncAdapter<List>(userAssignedList);
		
	}
	
	@SuppressWarnings("rawtypes")
	public ListenableFuture<RolePrivilegeListDTO> fetchAllRoleForManagingUserRole(List<Long> adminPrivilegeList,String userID){
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserRoleSearchPath());
		searchURL = new StringBuilder().append(searchURL).append(UserManagementConstants.SLASH_SYMBOL).append(userID).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<List<Long>> request = new HttpEntity<>(adminPrivilegeList,multiValueMap);
		ListenableFuture<ResponseEntity<RolePrivilegeListDTO>> rolePrivilegeListDTO = restServiceHelper.asyncRestTemplate().postForEntity(searchURL, request, RolePrivilegeListDTO.class);
		return new GenericDTOAsyncAdapter<RolePrivilegeListDTO>(rolePrivilegeListDTO);
	}

	@Override
	public ListenableFuture<RolePrivilegeListDTO> fetchAllRoleForManagingRoles(List<Long> adminPrivilegeList, String userID) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getRoleSearchPath());
		searchURL = new StringBuilder().append(searchURL).append(UserManagementConstants.SLASH_SYMBOL)
				.append(userID).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<List<Long>> request = new HttpEntity<>(adminPrivilegeList,multiValueMap);
		ListenableFuture<ResponseEntity<RolePrivilegeListDTO>> rolePrivilegeListDTO = restServiceHelper.asyncRestTemplate().postForEntity(searchURL, request, RolePrivilegeListDTO.class);
		return new GenericDTOAsyncAdapter<RolePrivilegeListDTO>(rolePrivilegeListDTO);
	}
	
	public ListenableFuture<Void> updateManageRoleChanges(SelectedRoleDTO selectedRoleDTO,String userID){
		
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getRolePath());
		searchURL = new StringBuilder().append(searchURL).append(UserManagementConstants.QUERY_PARAM_START_SYMBOL)
				.append(UserManagementConstants.USER_ID_QUERY_PARAM).append(userID).toString();		
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<SelectedRoleDTO> request = new HttpEntity<>(selectedRoleDTO,multiValueMap);
		ListenableFuture<ResponseEntity<Void>> rolePrivilegeListDTO = restServiceHelper.asyncRestTemplate().postForEntity(searchURL, request, Void.class);
		return new GenericDTOAsyncAdapter<Void>(rolePrivilegeListDTO);	
	}

}
