package com.emaratech.client.usermanagement.service.impl;

import java.util.HashMap;
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
import com.emaratech.client.usermanagement.service.UserRoleClientService;
import com.emaratech.client.usermanagement.tranferobject.ApplicationUserDTO;
import com.emaratech.client.usermanagement.tranferobject.RoleDTO;
import com.emaratech.client.usermanagement.tranferobject.UpdateRoleDTO;
import com.emaratech.common.async.GenericDTOAsyncAdapter;
import com.emaratech.security.oauth.client.AsyncRestServiceHelper;


@Service
public class UserRoleClientServiceImpl implements UserRoleClientService {
	
	@Autowired
	private Environment env;
	

	@Autowired
	private AsyncRestServiceHelper restServiceHelper;
	
	@Autowired
	private RestURLGenertorHelper restURLGenertorHelper;

	
	@Override
	public ListenableFuture<UpdateRoleDTO> getUserRole(long userID) {
		
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getRolePath());
		StringBuilder queryParamBuilder = new StringBuilder();
		queryParamBuilder.append(UserManagementConstants.QUERY_PARAM_START_SYMBOL);		
		queryParamBuilder.append(UserManagementConstants.USER_ID_QUERY_PARAM).append(userID);		
		searchURL = new StringBuilder().append(searchURL).append(queryParamBuilder.toString()).toString();
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<ApplicationUserDTO> requestEntity = new HttpEntity<>(multiValueMap);
		ListenableFuture<ResponseEntity<UpdateRoleDTO>> updateRoleDTO = restServiceHelper.asyncRestTemplate().exchange(searchURL,HttpMethod.GET, requestEntity, UpdateRoleDTO.class);		
		return new GenericDTOAsyncAdapter<UpdateRoleDTO>(updateRoleDTO);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ListenableFuture updateUserRoleChanges(UpdateRoleDTO updateRoleDTO) {
		String searchURL = restURLGenertorHelper.urlForPath(restURLGenertorHelper.getUserRolePath());
		MultiValueMap<String,String> multiValueMap = UserManagementUtils.generateMultivalueMapFromLocale(LocaleContextHolder.getLocale());
		HttpEntity<UpdateRoleDTO> request = new HttpEntity<>(updateRoleDTO,multiValueMap);	
		return new GenericDTOAsyncAdapter(restServiceHelper.asyncRestTemplate().postForEntity(searchURL, request,null));
		
	}

	
	
	public List<RoleDTO> removeUserAssignedRolesFromList(List<RoleDTO> userAssignedRoles, List<RoleDTO> completeRoleList){
		if(null != userAssignedRoles && null != completeRoleList && userAssignedRoles.size()>0 &&
				completeRoleList.size()>0){
			for(RoleDTO userAssignedRoleDTO : userAssignedRoles){
				if(completeRoleList.contains(userAssignedRoleDTO)){
					completeRoleList.remove(userAssignedRoleDTO);
				}
			}
		}
		return completeRoleList;
				
	}
	
	public HashMap<String,RoleDTO> createMapValueForRoleList(List<RoleDTO> completeRoleList,List<RoleDTO> userAssignedRoleListDTO){
		HashMap<String,RoleDTO> roleDTOHashMap = new HashMap<String,RoleDTO>();
		if(null != completeRoleList && completeRoleList.size() > 0){			
			for(RoleDTO roleDTO : completeRoleList){
				roleDTOHashMap.put(String.valueOf(roleDTO.getRoleId()), roleDTO);
			}
						
		}	
		if(null != userAssignedRoleListDTO && userAssignedRoleListDTO.size() > 0){			
			for(RoleDTO assignedRoleDTO : userAssignedRoleListDTO){
				roleDTOHashMap.put(String.valueOf(assignedRoleDTO.getRoleId()), assignedRoleDTO);
			}
						
		}		
		 return roleDTOHashMap;
	}


}
