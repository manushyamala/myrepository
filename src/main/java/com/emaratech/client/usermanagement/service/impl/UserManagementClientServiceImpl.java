package com.emaratech.client.usermanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.RestTemplate;

import com.emaratech.client.usermanagement.common.RestURLGenertorHelper;
import com.emaratech.client.usermanagement.common.UserManagementConstants;
import com.emaratech.client.usermanagement.common.UserManagementUtils;
import com.emaratech.client.usermanagement.service.UserManagementClientService;
import com.emaratech.client.usermanagement.tranferobject.AppUserDetailsDTO;
import com.emaratech.client.usermanagement.tranferobject.ApplicationUserDTO;
import com.emaratech.client.usermanagement.tranferobject.UserSearchDTO;
import com.emaratech.common.async.GenericDTOAsyncAdapter;
import com.emaratech.security.oauth.client.AsyncRestServiceHelper;

@Service
public class UserManagementClientServiceImpl implements UserManagementClientService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AsyncRestServiceHelper restServiceHelper;
	
	@Autowired
	private RestURLGenertorHelper restURLGenertorHelper;

	@Override
	public ListenableFuture<ApplicationUserDTO> findUserBasedonLogin(String login) {

		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserPath());

		searchURL = new StringBuilder().append(searchURL).append(UserManagementConstants.QUERY_PARAM_START_SYMBOL)
				.append(UserManagementConstants.LOGIN_QUERY_PARAM).append(login).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<ApplicationUserDTO>> user = restServiceHelper.asyncRestTemplate()
				.exchange(searchURL,HttpMethod.GET,requestEntity,ApplicationUserDTO.class);
		return new GenericDTOAsyncAdapter<ApplicationUserDTO>(user);
	}

	@Override
	public ListenableFuture<ApplicationUserDTO> findUserBasedonUserID(String userId) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserPath());
		searchURL = new StringBuilder().append(searchURL).append(UserManagementConstants.SLASH_SYMBOL).append(userId).toString();		
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<ApplicationUserDTO>> user = restServiceHelper.asyncRestTemplate().exchange(searchURL, HttpMethod.GET, requestEntity, ApplicationUserDTO.class);
		return new GenericDTOAsyncAdapter<ApplicationUserDTO>(user);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ListenableFuture<Void> updateUserDetails(ApplicationUserDTO userDTO) {

		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserPath());
		searchURL = new StringBuilder().append(searchURL).append(UserManagementConstants.SLASH_SYMBOL)
				.append(userDTO.getUserId()).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> request = new HttpEntity<>(userDTO,multiValueMap);
		ListenableFuture<?> updatedUser = restServiceHelper.asyncRestTemplate().put(searchURL, request);
		return new GenericDTOAsyncAdapter(updatedUser);
	}

	@Override
	public ListenableFuture<ApplicationUserDTO> findUserBasedonSearchCriteria(UserSearchDTO userSearchDTO) {
		boolean queryParamIncluded = false;
		StringBuilder queryParamBuilder = new StringBuilder();
		queryParamBuilder.append(UserManagementConstants.QUERY_PARAM_START_SYMBOL);
		if (null != userSearchDTO.getLoginName() && userSearchDTO.getLoginName().length() > 0) {
			queryParamBuilder.append(UserManagementConstants.LOGIN_QUERY_PARAM).append(userSearchDTO.getLoginName());
			queryParamIncluded = true;
		}
		if (null != userSearchDTO.getMobileNo() && userSearchDTO.getMobileNo().length() > 0) {
			if (queryParamIncluded) {
				queryParamBuilder.append(UserManagementConstants.AMPERSAND_SYMBOL);
			}
			queryParamBuilder.append(UserManagementConstants.MOBILENO_QUERY_PARAM).append(userSearchDTO.getMobileNo());
			queryParamIncluded = true;
		}
		if (null != userSearchDTO.getName() && userSearchDTO.getName().length() > 0) {
			if (queryParamIncluded) {
				queryParamBuilder.append(UserManagementConstants.AMPERSAND_SYMBOL);
			}
			queryParamBuilder.append(UserManagementConstants.NAME_QUERY_PARAM).append(userSearchDTO.getName());
		}
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserPath());
		searchURL = new StringBuilder().append(searchURL).append(queryParamBuilder.toString()).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<ApplicationUserDTO>> user = restServiceHelper.asyncRestTemplate().exchange(searchURL,HttpMethod.GET, requestEntity, ApplicationUserDTO.class);
		return new GenericDTOAsyncAdapter<ApplicationUserDTO>(user);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public ListenableFuture<List> findUserListBasedonSearchCriteria(UserSearchDTO userSearchDTO) {
		
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserSearchPath());
		HttpEntity<UserSearchDTO> request = new HttpEntity<>(userSearchDTO,multiValueMap);
		ListenableFuture<ResponseEntity<List>> user = restServiceHelper.asyncRestTemplate().postForEntity(searchURL,
				request, List.class);
		return new GenericDTOAsyncAdapter<List>(user);
	}

	@Override
	public ResponseEntity<AppUserDetailsDTO> loginUser(String userID) {
		String loginURI = new StringBuilder().append(restServiceHelper.getLoginURI())
				.append(UserManagementConstants.SLASH_SYMBOL).append(userID).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<UserSearchDTO> request = new HttpEntity<>(multiValueMap);
		ResponseEntity<AppUserDetailsDTO> user = restTemplate.postForEntity(loginURI, request, AppUserDetailsDTO.class);
		return user;

	}

}
