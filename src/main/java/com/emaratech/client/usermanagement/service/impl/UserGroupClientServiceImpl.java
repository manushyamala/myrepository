package com.emaratech.client.usermanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;

import com.emaratech.client.usermanagement.common.RestURLGenertorHelper;
import com.emaratech.client.usermanagement.common.UserManagementConstants;
import com.emaratech.client.usermanagement.common.UserManagementUtils;
import com.emaratech.client.usermanagement.service.UserGroupClientService;
import com.emaratech.client.usermanagement.tranferobject.ManageUserGroupDTO;
import com.emaratech.client.usermanagement.tranferobject.ManageUserGroupWrapperDTO;
import com.emaratech.common.async.GenericDTOAsyncAdapter;
import com.emaratech.security.oauth.client.AsyncRestServiceHelper;

@Service
public class UserGroupClientServiceImpl implements UserGroupClientService{
	

	@Autowired
	private AsyncRestServiceHelper restServiceHelper;

	@Autowired
	private RestURLGenertorHelper restURLGenertorHelper;

	@Override
	public ListenableFuture<ManageUserGroupWrapperDTO> fetchManageUserGroupDetails(String userID, List<Long> adminPrivilegeList) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserGroupSearchPath());
		StringBuilder queryParamBuilder = new StringBuilder();
		queryParamBuilder.append(UserManagementConstants.QUERY_PARAM_START_SYMBOL);	
		queryParamBuilder.append(UserManagementConstants.USER_ID_QUERY_PARAM).append(userID);
		searchURL = new StringBuilder().append(searchURL).append(queryParamBuilder.toString()).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<List<Long>> request = new HttpEntity<>(adminPrivilegeList,multiValueMap);
		ListenableFuture<ResponseEntity<ManageUserGroupWrapperDTO>> manageUserGroupDetails = restServiceHelper.asyncRestTemplate().postForEntity(searchURL, request, ManageUserGroupWrapperDTO.class);
		return new GenericDTOAsyncAdapter<ManageUserGroupWrapperDTO>(manageUserGroupDetails);
		
	}

	@Override
	public ListenableFuture<Void> updateManageUserGroupDetails(ManageUserGroupDTO manageUserGroupDTO) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserGroupPath());
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ManageUserGroupDTO> request = new HttpEntity<ManageUserGroupDTO>(manageUserGroupDTO,multiValueMap);
		ListenableFuture<ResponseEntity<Void>> updateManageUserGroupDetails = restServiceHelper.asyncRestTemplate().postForEntity(searchURL, request, Void.class);
		return new GenericDTOAsyncAdapter<Void>(updateManageUserGroupDetails);
	}
	

}
