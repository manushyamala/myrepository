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
import com.emaratech.client.usermanagement.service.PrivilegeClientService;
import com.emaratech.client.usermanagement.tranferobject.ApplicationUserDTO;
import com.emaratech.common.async.GenericDTOAsyncAdapter;
import com.emaratech.security.oauth.client.AsyncRestServiceHelper;

@Service
public class PrivilegeClientServiceImpl implements PrivilegeClientService {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AsyncRestServiceHelper restServiceHelper;
	
	@Autowired
	private RestURLGenertorHelper restURLGenertorHelper;

	@SuppressWarnings("rawtypes")
	@Override
	public ListenableFuture<List> getPrivilegeForRoleID(long roleID) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getPrivilegePath());
		StringBuilder queryParamBuilder = new StringBuilder();
		queryParamBuilder.append(UserManagementConstants.QUERY_PARAM_START_SYMBOL);		
		queryParamBuilder.append(UserManagementConstants.ROLE_ID_QUERY_PARAM).append(roleID);
		searchURL = new StringBuilder().append(searchURL).append(queryParamBuilder.toString()).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<List>> rolePrivilegeListDTO = restServiceHelper.asyncRestTemplate().exchange(searchURL, HttpMethod.GET,requestEntity,List.class);
		return new GenericDTOAsyncAdapter<List>(rolePrivilegeListDTO);
	}

}
