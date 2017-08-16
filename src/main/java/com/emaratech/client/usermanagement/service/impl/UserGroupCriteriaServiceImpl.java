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
import com.emaratech.client.usermanagement.service.UserGroupCriteriaService;
import com.emaratech.client.usermanagement.tranferobject.ApplicationUserDTO;
import com.emaratech.client.usermanagement.tranferobject.CriteriaTagWrapperDTO;
import com.emaratech.common.async.GenericDTOAsyncAdapter;
import com.emaratech.security.oauth.client.AsyncRestServiceHelper;

@Service
public class UserGroupCriteriaServiceImpl implements UserGroupCriteriaService {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AsyncRestServiceHelper restServiceHelper;
	
	@Autowired
	private RestURLGenertorHelper restURLGenertorHelper;

	@SuppressWarnings("rawtypes")
	@Override
	public ListenableFuture<List> fetchAllUserGroups() {		
		
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserGroupPath());
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<List>> userGroupList = restServiceHelper.asyncRestTemplate().exchange(searchURL,HttpMethod.GET, requestEntity, List.class);		
		return new GenericDTOAsyncAdapter<List>(userGroupList);
	}
	
	
	@Override
	public ListenableFuture<CriteriaTagWrapperDTO> fetchCriteriaTagsForUserGroupID(String userGroupID){
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserCriteriaTagPath());
		StringBuilder queryParamBuilder = new StringBuilder();
		queryParamBuilder.append(UserManagementConstants.QUERY_PARAM_START_SYMBOL);	
		queryParamBuilder.append(UserManagementConstants.GROUP_ID_QUERY_PARAM).append(userGroupID);
		searchURL = new StringBuilder().append(searchURL).append(queryParamBuilder.toString()).toString();	
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<CriteriaTagWrapperDTO>> criteriaTagWrapperDTO = restServiceHelper.asyncRestTemplate().exchange(searchURL, HttpMethod.GET, requestEntity,CriteriaTagWrapperDTO.class);		
		return new GenericDTOAsyncAdapter<CriteriaTagWrapperDTO>(criteriaTagWrapperDTO);
		
		
	}


	@Override
	public ListenableFuture<Void> updateUserGroupCriteriaTagChanges(CriteriaTagWrapperDTO criteriaTagWrapperDTO, String userID,String userGroupID) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserCriteriaTagPath());
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<CriteriaTagWrapperDTO> request = new HttpEntity<CriteriaTagWrapperDTO>(criteriaTagWrapperDTO,multiValueMap);
		StringBuilder queryParamBuilder = new StringBuilder();
		queryParamBuilder.append(UserManagementConstants.QUERY_PARAM_START_SYMBOL);	
		queryParamBuilder.append(UserManagementConstants.GROUP_ID_QUERY_PARAM).append(userGroupID);
		queryParamBuilder.append(UserManagementConstants.AMPERSAND_SYMBOL);	
		queryParamBuilder.append(UserManagementConstants.USER_ID_QUERY_PARAM).append(userID);
		searchURL = new StringBuilder().append(searchURL).append(queryParamBuilder.toString()).toString();
		ListenableFuture<ResponseEntity<Void>> userGroupCriteriaUdpateResource = restServiceHelper.asyncRestTemplate()
				.postForEntity(searchURL, request, Void.class);
		return new GenericDTOAsyncAdapter<Void>(userGroupCriteriaUdpateResource);
	}

}
