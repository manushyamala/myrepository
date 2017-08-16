package com.emaratech.client.usermanagement.controller;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.async.DeferredResult;

import com.emaratech.client.usermanagement.common.UserManagementConstants;
import com.emaratech.client.usermanagement.common.UserManagementUtils;
import com.emaratech.client.usermanagement.service.ApplicationConfigService;
import com.emaratech.client.usermanagement.service.PrivilegeClientService;
import com.emaratech.client.usermanagement.service.RoleClientService;
import com.emaratech.client.usermanagement.service.RoleGroupClientService;
import com.emaratech.client.usermanagement.service.RolePrivilegeClientService;
import com.emaratech.client.usermanagement.service.UserGroupClientService;
import com.emaratech.client.usermanagement.service.UserGroupCriteriaService;
import com.emaratech.client.usermanagement.service.UserManagementClientService;
import com.emaratech.client.usermanagement.service.UserRoleClientService;
import com.emaratech.client.usermanagement.tranferobject.ApplicationMonitoringDTO;
import com.emaratech.client.usermanagement.tranferobject.ApplicationUserDTO;
import com.emaratech.client.usermanagement.tranferobject.CriteriaTagDTO;
import com.emaratech.client.usermanagement.tranferobject.CriteriaTagWrapperDTO;
import com.emaratech.client.usermanagement.tranferobject.LookupDTO;
import com.emaratech.client.usermanagement.tranferobject.ManageRoleGroupDTO;
import com.emaratech.client.usermanagement.tranferobject.ManageRoleGroupWrapperDTO;
import com.emaratech.client.usermanagement.tranferobject.ManageUserGroupDTO;
import com.emaratech.client.usermanagement.tranferobject.ManageUserGroupWrapperDTO;
import com.emaratech.client.usermanagement.tranferobject.PrivilegeDTO;
import com.emaratech.client.usermanagement.tranferobject.RoleDTO;
import com.emaratech.client.usermanagement.tranferobject.RoleGroupDTO;
import com.emaratech.client.usermanagement.tranferobject.RoleGroupRoleDTO;
import com.emaratech.client.usermanagement.tranferobject.RolePrivilegeDTOListHolder;
import com.emaratech.client.usermanagement.tranferobject.RolePrivilegeListDTO;
import com.emaratech.client.usermanagement.tranferobject.SelectedRoleDTO;
import com.emaratech.client.usermanagement.tranferobject.UpdateRoleDTO;
import com.emaratech.client.usermanagement.tranferobject.UserGroupDTO;
import com.emaratech.client.usermanagement.tranferobject.UserSearchDTO;
import com.emaratech.common.dto.ServerErrorResponse;
import com.emaratech.common.dto.UserLoginDTO;
import com.emaratech.common.utils.ServletResponseUtils;
import com.emaratech.security.user.AppUserClientDetails;
import com.emaratech.security.user.AppUserPrivilegeDTO;



@Controller
public class UserManagementController {		

	private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);
	

	@Autowired
	private UserManagementClientService userManagementService;

	@Autowired
	private RoleClientService roleService;
	
	@Autowired
	private RolePrivilegeClientService rolePrivilegeClientService;
	
	@Autowired
	private PrivilegeClientService privilegeService;

	@Autowired
	private UserRoleClientService userRoleService;
	
	@Autowired
	private ApplicationConfigService applicationConfigService;
	
	@Autowired
	private UserGroupCriteriaService userGroupCriteriaService;
	
	@Autowired
	private UserGroupClientService userGroupClientService;
	
	@Autowired
	private RoleGroupClientService roleGroupClientService;
	
	@Autowired
	private MessageSource messageSource;
	

	@RequestMapping(value = "/getUserDetails/{login}")
	public DeferredResult<String> findUserBasedonLogin(Model model, @PathVariable("login") String login) {		
		logger.info("triggering findUserBasedonLogin method,with login {}", login);
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		logger.debug("triggering userManagementService.findUserBasedonLogin method,with login {}", login);
		ListenableFuture<ApplicationUserDTO> future = userManagementService.findUserBasedonLogin(login);
		logger.debug("completed call findUserBasedonLogin method,with login {}");
		future.addCallback(new ListenableFutureCallback<ApplicationUserDTO>() {
			@Override
			public void onSuccess(ApplicationUserDTO userDTO) {
				logger.debug("findUserBasedonLogin completed successfully,returning userDTO {}",userDTO);
				model.addAttribute("userObj", userDTO);
				deferredResult.setResult("index :: resultsList");
			}

			@Override
			public void onFailure(Throwable t) {
				logger.error("findUserBasedonLogin call failed with error {}",t);				
				ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
				if(null != errorResponse && errorResponse.getStatus() == 404){
					model.addAttribute("searchcompletestatus", "complete");
					model.addAttribute("warnResponseDesc",errorResponse.getMessage());
					deferredResult.setResult("index :: resultsList");
				}else{
					ResponseEntity<String> responseEntity = new ResponseEntity<String>(errorResponse.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
					deferredResult.setErrorResult(responseEntity);
				}
				
			}
		});

		return deferredResult;

	}

	@RequestMapping(value={"/home"})
	public String index() {
		return "home";
	}

	@RequestMapping("/login")
	public String login(Model model,HttpServletRequest request) {
		
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		model.addAttribute("loginobj", userLoginDTO);
		return "login";
	}

	/**
	//TODO needs to convert the logic to fetch the credentials from DB instead of Property file.
	@RequestMapping(value = "/validatelogin", method = RequestMethod.POST)
	public String validateLogin(@ModelAttribute(value = "loginobj") UserLoginDTO userLoginDTO, Model model,
			HttpServletRequest request) {
		logger.info("triggering validateLogin method");
		HttpSession httpSession = null;
		String userName = env.getProperty("login.username");
		String passWord = env.getProperty("login.password");
		if (userLoginDTO.getUserName().equals(userName) && userLoginDTO.getPassword().equals(passWord)) {
			httpSession = request.getSession();
			httpSession.setAttribute("userName", userLoginDTO.getUserName());
			return "home";
		} else {
			model.addAttribute("loginstatus", "failed");
			return "login";
		}
	}
	**/
	@RequestMapping(value={"/", "/loginSuccess"} , method = RequestMethod.GET)
	public String loginSuccess(HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		AppUserClientDetails userDetails =
				 (AppUserClientDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		httpSession.setAttribute("userName", request.getUserPrincipal().getName());
		httpSession.setAttribute("userDetailObj", userDetails.getAppUserDetailsDTO());
		httpSession.setAttribute("userID", String.valueOf(userDetails.getAppUserDetailsDTO().getUserID()));
		httpSession.setAttribute("privilegeObjList", userDetails.getAppUserDetailsDTO().getPrivilegeList());
		httpSession.setAttribute("adminPrivilegeIDList",getAdminPrivilgeIDList(userDetails.getAppUserDetailsDTO().getPrivilegeList()));
		return "home";
	}

	
	@RequestMapping(value={"/selectLocale"})
	public String selectLocale(Model model,HttpServletRequest request) {
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		model.addAttribute("loginobj", userLoginDTO);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("localSelected", true);
		return "login";
	}
	
	private List<Long> getAdminPrivilgeIDList(List<AppUserPrivilegeDTO> list){
		List<Long> adminPrivilegeIDList = null;
		if(UserManagementUtils.isNotEmptyAndNotNull(list)){
			adminPrivilegeIDList = new ArrayList<Long>();
			for(AppUserPrivilegeDTO privilegeDTO : list){
				if(null != privilegeDTO.getAdminPrivilege() && privilegeDTO.getAdminPrivilege().booleanValue()){
					adminPrivilegeIDList.add(privilegeDTO.getPrivilegeId());
				}
			}
			
		}
		return adminPrivilegeIDList;
	}
	
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(Model model, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		Exception exception = (Exception)httpSession.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		String message = getLoginFailedMessage(exception);
		if(null != message){
			model.addAttribute("loginError", message);
		}
		return "login";
	}
		
	
	private String getLoginFailedMessage(Exception exception){
		String message = null;
		if (exception instanceof InternalAuthenticationServiceException) {
			message = messageSource.getMessage("login.failed.connection", null, LocaleContextHolder.getLocale());
		}
		
		if (exception instanceof BadCredentialsException) {
			message = messageSource.getMessage("login.failed.badpassword", null, LocaleContextHolder.getLocale());
		}
		return message;
	}
	

	@RequestMapping(value = "/initiateLogout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		if (null != session) {
			session.invalidate();
		}
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		model.addAttribute("loginobj", userLoginDTO);
		return "login";
	}

	@RequestMapping(value = "/showusersearch", method = RequestMethod.GET)
	public String showViewUserPage(Model model) {
		UserSearchDTO userSearchDTO = new UserSearchDTO();
		model.addAttribute("userSearchObj", userSearchDTO);
		return "viewuser";
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/viewuser", method = RequestMethod.POST)
	public DeferredResult<String> retrieveUserDetails(
			@ModelAttribute(value = "userSearchObj") UserSearchDTO userSearchDTO, Model model) {
		logger.info("triggering retrievUserDetailsForUpdate method,with userSearchDTO {}", userSearchDTO);
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		logger.debug("triggering userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}");
		try{
			ListenableFuture<List> future = userManagementService.findUserListBasedonSearchCriteria(userSearchDTO);
			logger.debug("completed call userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}");
			future.addCallback(new ListenableFutureCallback<List>() {
				@Override
				public void onSuccess(List userDTOList) {
					logger.debug("retrievUserDetailsForUpdate completed successfully,returning userDTOList {}");
					model.addAttribute("searchcompletestatus", "complete");
					model.addAttribute("userObjList", userDTOList);
					deferredResult.setResult("viewuser :: viewuser-searchdata-result-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("retrievUserDetailsForUpdate call failed with error {}",t);	
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("viewuser :: viewuser-searchdata-result-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("viewuser :: viewuser-searchdata-result-frag");
					}
					
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("viewuser :: viewuser-searchdata-result-frag");
		}

		return deferredResult;

	}

	@RequestMapping(value = "/viewuser/{user_id}", method = RequestMethod.GET)
	public DeferredResult<String> retrieveSelectedUserDetails(Model model,
			@PathVariable("user_id") String userID) {
		logger.info("triggering retrieveSelectedUserDetailForUpdate method,with userID {}", userID);
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		logger.debug("triggering userManagementService.findUserBasedonUserID method,with user_id {}");
		try{
			ListenableFuture<ApplicationUserDTO> future = userManagementService.findUserBasedonUserID(userID);
			logger.debug("completed call userManagementService.retrieveSelectedUserDetailForUpdate method,with user_id {}");
			future.addCallback(new ListenableFutureCallback<ApplicationUserDTO>() {
				@Override
				public void onSuccess(ApplicationUserDTO userDTO) {
					logger.debug("retrieveSelectedUserDetailForUpdate completed successfully,returning userID {}");
					model.addAttribute("searchcompletestatus", "complete");
					model.addAttribute("userObj", userDTO);
					deferredResult.setResult("viewuser :: viewuser-searchdata-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("viewuser :: viewuser-searchdata-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("viewuser :: viewuser-searchdata-frag");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageuser :: manageuser-searchdata-result-frag");
		}

		return deferredResult;

	}
	
	/**

	@RequestMapping(value = "/viewuser", method = RequestMethod.POST)
	public DeferredResult<String> retrievUserDetails(
			@ModelAttribute(value = "userSearchObj") UserSearchDTO userSearchDTO, Model model) {
		logger.info("triggering retrievUserDetails method,with userSearchDTO {}", userSearchDTO);
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		logger.debug("triggering userManagementService.findUserBasedonSearchCriteria method,with userSearchDTO {}");
		ListenableFuture<ApplicationUserDTO> future = userManagementService.findUserBasedonSearchCriteria(userSearchDTO);
		logger.debug("completed call userManagementService.findUserBasedonSearchCriteria method,with userSearchDTO {}");
		future.addCallback(new ListenableFutureCallback<ApplicationUserDTO>() {
			@Override
			public void onSuccess(ApplicationUserDTO userDTO) {
				logger.debug("retrievUserDetails completed successfully,returning userDTO {}",userDTO);
				model.addAttribute("searchcompletestatus", "complete");
				model.addAttribute("userObj", userDTO);
				deferredResult.setResult("viewuser::viewuser-searchdata-frag");
			}

			@Override
			public void onFailure(Throwable t) {
				logger.error("retrievUserDetails call failed with error {}",t);
				ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
				deferredResult.setErrorResult(responseEntity);
			}
		});

		return deferredResult;
	}

	**/
	
	@RequestMapping(value = "/showmanageuser", method = RequestMethod.GET)
	public String showManageUserPage(Model model) {
		UserSearchDTO userSearchDTO = new UserSearchDTO();
		model.addAttribute("userSearchObj", userSearchDTO);
		return "manageuser";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/manageuser", method = RequestMethod.POST)
	public DeferredResult<String> retrievUserDetailsForUpdate(
			@ModelAttribute(value = "userSearchObj") UserSearchDTO userSearchDTO, Model model) {
		logger.info("triggering retrievUserDetailsForUpdate method,with userSearchDTO {}", userSearchDTO);
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		logger.debug("triggering userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}");
		try{
			ListenableFuture<List> future = userManagementService.findUserListBasedonSearchCriteria(userSearchDTO);
			logger.debug("completed call userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}");
			future.addCallback(new ListenableFutureCallback<List>() {
				@Override
				public void onSuccess(List userDTOList) {
					logger.debug("retrievUserDetailsForUpdate completed successfully,returning userDTOList {}");
					model.addAttribute("searchcompletestatus", "complete");
					model.addAttribute("userObjList", userDTOList);
					deferredResult.setResult("manageuser :: manageuser-searchdata-result-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("retrievUserDetailsForUpdate call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageuser :: manageuser-searchdata-result-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageuser :: manageuser-searchdata-result-frag");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageuser :: manageuser-searchdata-result-frag");
		}
		
		return deferredResult;

	}

	@RequestMapping(value = "/getUserDetailsForUpdate/{user_id}", method = RequestMethod.GET)
	public DeferredResult<String> retrieveSelectedUserDetailForUpdate(Model model,
			@PathVariable("user_id") String userID) {
		logger.info("triggering retrieveSelectedUserDetailForUpdate method,with userID {}", userID);
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		logger.debug("triggering userManagementService.findUserBasedonUserID method,with user_id {}");
		try{
		ListenableFuture<ApplicationUserDTO> future = userManagementService.findUserBasedonUserID(userID);
		logger.debug("completed call userManagementService.retrieveSelectedUserDetailForUpdate method,with user_id {}");
		future.addCallback(new ListenableFutureCallback<ApplicationUserDTO>() {
			@Override
			public void onSuccess(ApplicationUserDTO userDTO) {
				logger.debug("retrieveSelectedUserDetailForUpdate completed successfully,returning userID {}");
				model.addAttribute("searchcompletestatus", "complete");
				model.addAttribute("selectedUserObj", userDTO);
				ApplicationUserDTO updatedUserDTO = new ApplicationUserDTO();
				model.addAttribute("updatedUserObj", updatedUserDTO);
				deferredResult.setResult("manageuser :: manageuser-updatedata-frag");
			}

			@Override
			public void onFailure(Throwable t) {
				logger.error("retrievUserDetailsForUpdate call failed with error {}",t);
				ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
				if(null != errorResponse && errorResponse.getStatus() == 404){
					model.addAttribute("searchcompletestatus", "complete");
					model.addAttribute("warnResponseDesc",errorResponse.getMessage());
					deferredResult.setResult("manageuser :: manageuser-updatedata-frag");
				}else{
					model.addAttribute("errorResponseDesc",errorResponse.getMessage());
					deferredResult.setResult("manageuser :: manageuser-updatedata-frag");
				}
			}
		});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageuser :: manageuser :: manageuser-updatedata-frag");
		}
		
		return deferredResult;

	}

	@RequestMapping(value = "/updateUserDetails/{user_id}", method = RequestMethod.POST)
	public DeferredResult<String> updateUserDetails(@ModelAttribute(value = "updatedUserObj") ApplicationUserDTO userDTO,
		Model model, @PathVariable("user_id") String user_ID) {
		logger.info("triggering updateUserDetails method,with userDTO {}", userDTO );
		if (null != user_ID && user_ID.length() > 0) {
			long userID = Long.parseLong(user_ID);
			userDTO.setUserId(userID);
		}
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		logger.debug("triggering userManagementService.updateUserDetails method,with userDTO {}", userDTO);
		try{
			ListenableFuture<Void> future = userManagementService.updateUserDetails(userDTO);
			logger.debug("completed call userManagementService.updateUserDetails method,with userDTO {}");
			future.addCallback(new ListenableFutureCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					model.addAttribute("updateUserStatus", "complete");
					deferredResult.setResult("manageuser :: manageuser-updatedata-status-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("updateUserDetails call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageuser :: manageuser-updatedata-status-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageuser :: manageuser-updatedata-status-frag");
					}
				}
	
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageuser :: manageuser-updatedata-status-frag");
		}
		return deferredResult;
	}

	@RequestMapping(value = "/addupdateuserrole", method = RequestMethod.GET)
	public String showAddUpdateUserRolePage(Model model) {
		UserSearchDTO userSearchDTO = new UserSearchDTO();
		model.addAttribute("userSearchObj", userSearchDTO);
		return "addupdateuserrole";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addupdateuserrole-searchuser", method = RequestMethod.POST)
	public DeferredResult<String> retrieveUserDetailsForAddUpdateUserRoles(
			@ModelAttribute(value = "userSearchObj") UserSearchDTO userSearchDTO, Model model) {
		logger.info("triggering retrieveUserDetailsForAddUpdateUserRoles method,with userSearchDTO {}",userSearchDTO );
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		logger.debug("triggering  userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}", userSearchDTO);
		try{
			ListenableFuture<List> future = userManagementService.findUserListBasedonSearchCriteria(userSearchDTO);
			logger.debug("completed call userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}");
			future.addCallback(new ListenableFutureCallback<List>() {
				@Override
				public void onSuccess(List userDTOList) {
					logger.debug("retrieveUserDetailsForAddUpdateUserRoles completed successfully,returning userDTOList {}");
					model.addAttribute("searchcompletestatus", "complete");
					model.addAttribute("userObjList", userDTOList);
					deferredResult.setResult("addupdateuserrole :: addupdateuserrole-searchdata-result-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("retrieveUserDetailsForAddUpdateUserRoles call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("addupdateuserrole :: addupdateuserrole-searchdata-result-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("addupdateuserrole :: addupdateuserrole-searchdata-result-frag");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("addupdateuserrole :: addupdateuserrole-searchdata-result-frag");
		}
		return deferredResult;
	}

	@RequestMapping(value = "/addupdateuserrole-fetchroledetailsforselecteduser/{user_id}", method = RequestMethod.GET)
	public DeferredResult<String> fetchRoleDetailsForSelectedUser(Model model, @PathVariable("user_id") String user_ID,
			HttpSession session) {
		logger.info("triggering fetchRoleDetailsForSelectedUser method,with user_ID {}",user_ID );
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		if (null != user_ID && user_ID.length() > 0) {
			long userID = Long.parseLong(user_ID);		
			logger.debug("triggering userRoleService.getUserRole method,with user_ID {}", user_ID);
			try{
				ListenableFuture<UpdateRoleDTO> futureForAssignedRolesList = userRoleService.getUserRole(userID);
				logger.debug("completed call userRoleService.getUserRole method,with user_ID {}");
				futureForAssignedRolesList.addCallback(new ListenableFutureCallback<UpdateRoleDTO>() {
					@Override
					public void onSuccess(UpdateRoleDTO updateRoleDTO) {
						logger.debug("fetchRoleDetailsForSelectedUser completed successfully,returning userDTOList {}");
						updateRoleDetails(updateRoleDTO,model,session);
						deferredResult.setResult("addupdateuserrole :: addupdateuserrole-populateroles-result-frag");
					}
	
					@Override
					public void onFailure(Throwable t) {
						logger.error("fetchRoleDetailsForSelectedUser call failed with error {}",t);
						ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
						if(null != errorResponse && errorResponse.getStatus() == 404){
							model.addAttribute("searchcompletestatus", "complete");
							model.addAttribute("warnResponseDesc",errorResponse.getMessage());
							deferredResult.setResult("addupdateuserrole :: addupdateuserrole-populateroles-result-frag");
						}else{
							model.addAttribute("errorResponseDesc",errorResponse.getMessage());
							deferredResult.setResult("addupdateuserrole :: addupdateuserrole-populateroles-result-frag");
						}
					}
				});
			}catch(Exception e){
				logger.error("Error Occured while accessing Server :"+e );
				model.addAttribute("errorResponseDesc","Unable to access Server");
				deferredResult.setResult("addupdateuserrole :: addupdateuserrole-populateroles-result-frag");
			}
		}
		return deferredResult;
	}

	
	private void updateRoleDetails(UpdateRoleDTO updateRoleDTO,Model model,HttpSession session){
		model.addAttribute("userAssignedRolesList", updateRoleDTO.getUserAssignedRoleListDTO());
		model.addAttribute("completeRoleList", updateRoleDTO.getCompleteRoleListDTO());
		session.setAttribute("userAssignedRolesList", updateRoleDTO.getUserAssignedRoleListDTO());
		session.setAttribute("completeRoleList", updateRoleDTO.getCompleteRoleListDTO());
		session.setAttribute("completeRoleMap", updateRoleDTO.getRoleDTOHashMap());
		session.setAttribute("user_id", updateRoleDTO.getUserID());		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addupdateuserrole-modifyInMemoryRoleDetails/{action}", method = RequestMethod.GET)
	public String modifyInMemoryRoleDetailsForBulkUpdate(Model model, @PathVariable("action") String action,
			HttpSession session) {
		logger.info("triggering modifyInMemoryRoleDetailsForBulkUpdate method,with action {}",action );
		List<RoleDTO> completeRoleListDTO = (List<RoleDTO>) session.getAttribute("completeRoleList");
		List<RoleDTO> userAssignedRoleListDTO = (List<RoleDTO>) session.getAttribute("userAssignedRolesList");
		if (null != action && action.equals("add")) {
			if (null != userAssignedRoleListDTO && null != completeRoleListDTO) {
				for (RoleDTO roleListDTO : completeRoleListDTO) {
					userAssignedRoleListDTO.add(roleListDTO);
				}
				completeRoleListDTO.removeAll(completeRoleListDTO);
				model.addAttribute("userAssignedRolesList", userAssignedRoleListDTO);
				model.addAttribute("completeRoleList", completeRoleListDTO);
			}
		} else if (null != action && action.equals("remove")) {
			if (null != userAssignedRoleListDTO && null != completeRoleListDTO) {
				for (RoleDTO roleListDTO : userAssignedRoleListDTO) {
					completeRoleListDTO.add(roleListDTO);
				}
				userAssignedRoleListDTO.removeAll(userAssignedRoleListDTO);
				model.addAttribute("userAssignedRolesList", userAssignedRoleListDTO);
				model.addAttribute("completeRoleList", completeRoleListDTO);
			}
		}
		session.setAttribute("userAssignedRolesList", userAssignedRoleListDTO);
		session.setAttribute("completeRoleList", completeRoleListDTO);
		return "addupdateuserrole :: addupdateuserrole-populateroles-result-frag";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addupdateuserrole-modifyInMemoryRoleDetailsForSingleUpdates/{role_id}/{action}", method = RequestMethod.GET)
	public String modifyInMemoryRoleDetailsForSingleUpdate(Model model, @PathVariable("role_id") String role_id,
			@PathVariable("action") String action, HttpSession session) {
		logger.info("triggering modifyInMemoryRoleDetailsForSingleUpdate method,with action {}",action );
		List<RoleDTO> completeRoleListDTO = (List<RoleDTO>) session.getAttribute("completeRoleList");
		List<RoleDTO> userAssignedRoleListDTO = (List<RoleDTO>) session.getAttribute("userAssignedRolesList");
		HashMap<Long, RoleDTO> roleDTOHashMap = (HashMap<Long, RoleDTO>) session.getAttribute("completeRoleMap");
		if (null != action && action.equals("add") && null != role_id) {
			if (null != userAssignedRoleListDTO && null != completeRoleListDTO) {
				RoleDTO roleDTO = roleDTOHashMap.get(role_id);
				if (null != roleDTO) {
					userAssignedRoleListDTO.add(roleDTO);
					completeRoleListDTO.remove(roleDTO);
				}
				model.addAttribute("userAssignedRolesList", userAssignedRoleListDTO);
				model.addAttribute("completeRoleList", completeRoleListDTO);
			}
		} else if (null != action && action.equals("remove") && null != role_id) {
			if (null != userAssignedRoleListDTO && null != completeRoleListDTO) {
				RoleDTO roleDTO = roleDTOHashMap.get(role_id);
				if (null != roleDTO) {
					userAssignedRoleListDTO.remove(roleDTO);
					completeRoleListDTO.add(roleDTO);
				}
				model.addAttribute("userAssignedRolesList", userAssignedRoleListDTO);
				model.addAttribute("completeRoleList", completeRoleListDTO);
			}
		}
		session.setAttribute("userAssignedRolesList", userAssignedRoleListDTO);
		session.setAttribute("completeRoleList", completeRoleListDTO);
		return "addupdateuserrole :: addupdateuserrole-populateroles-result-frag";

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addupdateuserrole-updateUserRoleChanges", method = RequestMethod.GET)
	public DeferredResult<String> updateUserRoleChanges(Model model, HttpSession session) {
		logger.info("triggering updateUserRoleChanges method");
		List<RoleDTO> userAssignedRoleListDTO = (List<RoleDTO>) session.getAttribute("userAssignedRolesList");
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		String userID = (String) session.getAttribute("user_id");
		UpdateRoleDTO updateRoleDTO = new UpdateRoleDTO();
		updateRoleDTO.setUserAssignedRoleListDTO(userAssignedRoleListDTO);
		updateRoleDTO.setUserID(userID);		
		logger.debug("triggering userRoleService.updateUserRoleChanges method,with updateRoleDTO {}", updateRoleDTO);
		try{
		ListenableFuture futureForUpdateRoles = userRoleService.updateUserRoleChanges(updateRoleDTO);	
		logger.debug("completed call userRoleService.updateUserRoleChanges method,with updateRoleDTO {}");
		futureForUpdateRoles.addCallback(new ListenableFutureCallback<UpdateRoleDTO>() {
			@Override
			public void onSuccess(UpdateRoleDTO updateRoleDTO) {
				logger.debug("updateUserRoleChanges completed successfully,returning updateRoleDTO {}");
				model.addAttribute("updateUserRoleStatus", "complete");
				deferredResult.setResult("addupdateuserrole :: addupdateuserrole-updatedata-result-status-frag");
			}

			@Override
			public void onFailure(Throwable t) {
				logger.error("updateUserRoleChanges call failed with error {}",t);
				ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
				if(null != errorResponse && errorResponse.getStatus() == 404){
					model.addAttribute("searchcompletestatus", "complete");
					model.addAttribute("warnResponseDesc",errorResponse.getMessage());
					deferredResult.setResult("addupdateuserrole :: addupdateuserrole-updatedata-result-status-frag");
				}else{
					model.addAttribute("errorResponseDesc",errorResponse.getMessage());
					deferredResult.setResult("addupdateuserrole :: addupdateuserrole-updatedata-result-status-frag");
				}
			}
		});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("addupdateuserrole :: addupdateuserrole-updatedata-result-status-frag");
		}
		
		return deferredResult;

	}

	@RequestMapping(value = "/viewlog", method = RequestMethod.GET)
	public String viewLogs(Model model) {
		UserSearchDTO userSearchDTO = new UserSearchDTO();
		model.addAttribute("userSearchObj", userSearchDTO);
		return "viewlogs";
	}

	@RequestMapping(value = "/angularTest", method = RequestMethod.GET)
	public String angularTest(Model model) {
		UserSearchDTO userSearchDTO = new UserSearchDTO();
		model.addAttribute("userSearchObj", userSearchDTO);
		return "angularTest";
	}

	@RequestMapping(value = "/angularResource", method = RequestMethod.GET)
	public String home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return "angularTest";
	}
	
	@RequestMapping(value = "/adminconsole", method = RequestMethod.GET)
	public String adminConsole(Model model) {
		return "adminconsole";
	}
	
	@RequestMapping(value = "/monitoring", method = RequestMethod.GET)
	public String viewMonitoringDetails(Model model) {
		logger.info("triggering viewMonitoringDetails method");	
		ResponseEntity<ApplicationMonitoringDTO> applicationMonitoringDTOResource = applicationConfigService.retrieveApplicationMonitoringDetails();
		if(null != applicationMonitoringDTOResource){
			model.addAttribute("applicationMonitoringDTO", applicationMonitoringDTOResource.getBody());
		}
		return "monitoring";
	}
	
	@RequestMapping(value = "/configuration", method = RequestMethod.GET)
	public String accessConfiguartion(Model model) {
		logger.info("triggering accessConfiguartion method");
		ResponseEntity<String> currentLoggingResource = applicationConfigService.retrieveCurrentLoggingLevel();
		if(null != currentLoggingResource){
			model.addAttribute("currentLogLevel", currentLoggingResource.getBody());
		}
		return "configuration";
	}
	
	
	@RequestMapping(value = "/configuration/update/{logLevel}", method = RequestMethod.GET)
	public String updateApplicationLoggingLevel(Model model,@PathVariable("logLevel") String logLevel) {
		logger.info("triggering updateApplicationLoggingLevel method");
		if(null != logLevel && !logLevel.equals(UserManagementConstants.NO_VAL)){
			ResponseEntity<String> updatedLoggingResource = applicationConfigService.updateLoggingLevel(logLevel);
			if(null != updatedLoggingResource){
				model.addAttribute("currentLogLevel", updatedLoggingResource.getBody());
				model.addAttribute("updateStatus", "complete");
			}
			return "configuration :: logging-level-update-status-frag";
		}else{
			model.addAttribute("showLoggingLevelForUpdate","true");
			return "configuration :: logging-level-selection-frag";
		}
		
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/roleprivilege", method = RequestMethod.GET)
	public DeferredResult<String>  managerUserRoles(Model model, HttpSession session) {
		logger.info("triggering managerUserRoles method");
		List<Long> adminPrivilegeList = (List<Long>) session.getAttribute("adminPrivilegeIDList");
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		String userID = (String) session.getAttribute("userID");	
		try{
			ListenableFuture futureForUpdateRoles = roleService.fetchAllRoleForManagingUserRole(adminPrivilegeList,userID);	
			futureForUpdateRoles.addCallback(new ListenableFutureCallback<RolePrivilegeListDTO>() {
				@Override
				public void onSuccess(RolePrivilegeListDTO rolePrivilegeListDTO) {
					logger.debug("managerUserRoles completed successfully,returning rolePrivilegeListDTO {}");
					model.addAttribute("rolePrivilegeListDTO", rolePrivilegeListDTO);
					session.setAttribute("originalPrivilegeList", rolePrivilegeListDTO.getPrivilegeListDTO() != null ? rolePrivilegeListDTO.getPrivilegeListDTO() : new ArrayList<PrivilegeDTO>());
					session.setAttribute("initialRoleListDTO", rolePrivilegeListDTO.getRoleDTOList());
					deferredResult.setResult("roleprivilege");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("managerUserRoles call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("roleprivilege");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("roleprivilege");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("roleprivilege");
		}
		
		return deferredResult;

	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/fetchprivilegesforroleid/{roleID}", method = RequestMethod.GET)
	public DeferredResult<String>  fetchPrivilegesForRoleID(Model model, HttpSession session,@PathVariable("roleID") String roleID) {
		logger.info("triggering fetchPrivilegesForRoleID method");
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		try{
			ListenableFuture futureForUpdateRoles = privilegeService.getPrivilegeForRoleID(Long.parseLong(roleID));	
			futureForUpdateRoles.addCallback(new ListenableFutureCallback<List>() {
				@Override
				public void onSuccess(List privilegeListDTO) {
					logger.debug("fetchPrivilegesForRoleID completed successfully,returning privilegeListDTO {}");
					RolePrivilegeDTOListHolder privilegeDTOListHolder = new RolePrivilegeDTOListHolder();
					privilegeDTOListHolder.setRoleAssignedPrivilegDTOList(privilegeListDTO);
					List<PrivilegeDTO>  unassignedPrivilegeList = (List<PrivilegeDTO>) session.getAttribute("originalPrivilegeList");
					privilegeDTOListHolder.setUnassignedPrivilegDTOList(unassignedPrivilegeList);
					model.addAttribute("privilegeDTOHolder", privilegeDTOListHolder);
					session.setAttribute("roleAssignedPrivilegeList", new ArrayList<PrivilegeDTO>());
					session.setAttribute("privilegeDTOMap", generatePrivilegeDTOMap(privilegeListDTO,unassignedPrivilegeList,session));
					session.setAttribute("selectedRoleID",roleID);
					deferredResult.setResult("roleprivilege :: show-privilege-list-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("fetchPrivilegesForRoleID call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("roleprivilege :: show-privilege-list-frag");
					}else{
						model.addAttribute("errorResponseDesc","Unable to access Server");
						deferredResult.setResult("roleprivilege :: show-privilege-list-frag");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("roleprivilege :: show-privilege-list-frag");
		}
		
		return deferredResult;
	}
	
	@SuppressWarnings("rawtypes")
	private HashMap<Long,PrivilegeDTO> generatePrivilegeDTOMap(List privilegeListLinkedMapDTO,List<PrivilegeDTO> unassignedPrivilegeList,HttpSession session){
		
		List<PrivilegeDTO> privilegeDTOList = null;
		HashMap<Long,PrivilegeDTO> privilegeDTOMap =  new HashMap<Long, PrivilegeDTO>();;
		try{
			if(UserManagementUtils.isNotEmptyAndNotNull(privilegeListLinkedMapDTO)){
				privilegeDTOList = new ArrayList<PrivilegeDTO>();		
				for(int i =0;i<privilegeListLinkedMapDTO.size();i++){
					LinkedHashMap linkedMap = (LinkedHashMap) privilegeListLinkedMapDTO.get(i);
					PrivilegeDTO privilegeDTO = new PrivilegeDTO();
					privilegeDTO.setPrivilegeId( linkedMap.get("privilegeId") != null ? (Integer) linkedMap.get("privilegeId") : null);
					privilegeDTO.setPrivilegeNameEn( linkedMap.get("privilegeNameEn") != null ? (String) linkedMap.get("privilegeNameEn") : null);
					privilegeDTO.setPrivilegeNameAr( linkedMap.get("privilegeNameAr") != null ? (String) linkedMap.get("privilegeNameAr") : null);
					privilegeDTOList.add(privilegeDTO);
				}
			
				if(UserManagementUtils.isNotEmptyAndNotNull(privilegeDTOList)){
					session.setAttribute("roleAssignedPrivilegeList", privilegeDTOList);
					for(PrivilegeDTO privilegeDTO : privilegeDTOList){
						privilegeDTOMap.put(privilegeDTO.getPrivilegeId(), privilegeDTO);
					}
				}
				
			}	
			if(UserManagementUtils.isNotEmptyAndNotNull(unassignedPrivilegeList)){
				for(PrivilegeDTO privilegeDTO : unassignedPrivilegeList){
					privilegeDTOMap.put(privilegeDTO.getPrivilegeId(), privilegeDTO);
				}
			}
			
			if(UserManagementUtils.isNotEmptyAndNotNull(unassignedPrivilegeList) && UserManagementUtils.isNotEmptyAndNotNull(privilegeDTOList)){
				unassignedPrivilegeList.removeAll(privilegeDTOList);
				session.setAttribute("originalPrivilegeList",unassignedPrivilegeList);
			}
			

		}catch(Exception e){
			e.printStackTrace();
		}

		return privilegeDTOMap;
	
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/fetchroleprivilegesforprivilegeid/{privilegeID}", method = RequestMethod.GET)
	public DeferredResult<String>  fetchRolePrivilegesForPrivilegeID(Model model, HttpSession session,@PathVariable("privilegeID") String privilegeID) {
		logger.info("triggering fetchRolePrivilegesForPrivilegeID method");
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		try{
		ListenableFuture futureFoRolePrivileges = rolePrivilegeClientService.fetchRolePrivilegeForPrivilegeID(Long.parseLong(privilegeID));	
		futureFoRolePrivileges.addCallback(new ListenableFutureCallback<List>() {
			@Override
			public void onSuccess(List rolePrivilegeListDTO) {
				logger.debug("fetchRolePrivilegesForPrivilegeID completed successfully,returning rolePrivilegeListDTO {}");
				List<RoleDTO> initialRoleDTOList = (List<RoleDTO>) session.getAttribute("initialRoleListDTO");
				List<RoleDTO> matchedRoleDTOList = findAssocaitedRoleDTOListForPrivilegeID(rolePrivilegeListDTO,initialRoleDTOList);			
				model.addAttribute("matchedRoleDTOList", matchedRoleDTOList);
				deferredResult.setResult("roleprivilege :: show-associated-roles-frag");
			}

			@Override
			public void onFailure(Throwable t) {
				logger.error("fetchRolePrivilegesForPrivilegeID call failed with error {}",t);
				ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
				if(null != errorResponse && errorResponse.getStatus() == 404){
					model.addAttribute("searchcompletestatus", "complete");
					model.addAttribute("warnResponseDesc",errorResponse.getMessage());
					deferredResult.setResult("roleprivilege :: show-associated-roles-frag");
				}else{
					model.addAttribute("errorResponseDesc","Unable to access Server");
					deferredResult.setResult("roleprivilege :: show-associated-roles-frag");
				}
			}
		});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("roleprivilege :: show-associated-roles-frag");
		}
		return deferredResult;
	}

	
	@SuppressWarnings("rawtypes")
	private List<RoleDTO> findAssocaitedRoleDTOListForPrivilegeID(List<LinkedHashMap> rolePrivilegeListDTO,List<RoleDTO> roleDTOList){
		List<RoleDTO> matchedRoleDTOList = new ArrayList<RoleDTO>();
		if(UserManagementUtils.isNotEmptyAndNotNull(rolePrivilegeListDTO) && UserManagementUtils.isNotEmptyAndNotNull(roleDTOList)){
			for(LinkedHashMap rolePrivilegeObjectMap : rolePrivilegeListDTO){				
				LinkedHashMap roleDTOMap =  (LinkedHashMap)rolePrivilegeObjectMap.get("roleDTO");
				if(null != roleDTOMap){
					Integer roleIDObj = (Integer)roleDTOMap.get("roleId");		
					if(null != roleIDObj){
						long role_ID = roleIDObj.longValue();				
						for(RoleDTO roleDTO : roleDTOList){
							if(role_ID == roleDTO.getRoleId()){
								matchedRoleDTOList.add(roleDTO);
							}
						}
					}
				}
			}
			
		}
		
		return matchedRoleDTOList;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addupdateuserrole-modifyInMemoryPrivilegeDetailsForSingleUpdates/{privilege_id}/{action}", method = RequestMethod.GET)
	public String modifyInMemoryPrivilegeDetailsForSingleUpdate(Model model, @PathVariable("privilege_id") String privilege_id,
			@PathVariable("action") String action, HttpSession session) {
		logger.info("triggering modifyInMemoryRoleDetailsForSingleUpdate method,with action {}",action );
		List<PrivilegeDTO> originalPrivilegeListDTO = (List<PrivilegeDTO>) session.getAttribute("originalPrivilegeList");
		List<PrivilegeDTO> roleAssignedPrivilegeList = (List<PrivilegeDTO>) session.getAttribute("roleAssignedPrivilegeList");
		HashMap<Long, PrivilegeDTO> privilegeDTOHashMap = (HashMap<Long, PrivilegeDTO>) session.getAttribute("privilegeDTOMap");
					
		if (null != action && action.equals("add") && null != privilege_id) {
			if (null != roleAssignedPrivilegeList && null != originalPrivilegeListDTO) {				
				PrivilegeDTO privilegeDTO = privilegeDTOHashMap.get(Long.parseLong(privilege_id));
				if (null != privilegeDTO) {
					roleAssignedPrivilegeList.add(privilegeDTO);
					originalPrivilegeListDTO.remove(privilegeDTO);
				}
				
				RolePrivilegeDTOListHolder privilegeDTOListHolder = new RolePrivilegeDTOListHolder();
				privilegeDTOListHolder.setUnassignedPrivilegDTOList(originalPrivilegeListDTO);
				privilegeDTOListHolder.setRoleAssignedPrivilegDTOList(roleAssignedPrivilegeList);
				model.addAttribute("privilegeDTOHolder", privilegeDTOListHolder);
			}
		} else if (null != action && action.equals("remove") && null != privilege_id) {
			if (null != roleAssignedPrivilegeList && null != originalPrivilegeListDTO) {
				PrivilegeDTO privilegeDTO = privilegeDTOHashMap.get(Long.parseLong(privilege_id));
				if (null != privilegeDTO) {
					roleAssignedPrivilegeList.remove(privilegeDTO);
					originalPrivilegeListDTO.add(privilegeDTO);
				}
				RolePrivilegeDTOListHolder privilegeDTOListHolder = new RolePrivilegeDTOListHolder();
				privilegeDTOListHolder.setUnassignedPrivilegDTOList(originalPrivilegeListDTO);
				privilegeDTOListHolder.setRoleAssignedPrivilegDTOList(roleAssignedPrivilegeList);
				model.addAttribute("privilegeDTOHolder", privilegeDTOListHolder);
			}
		}
		session.setAttribute("originalPrivilegeList", originalPrivilegeListDTO);
		session.setAttribute("roleAssignedPrivilegeList", roleAssignedPrivilegeList);
		return "roleprivilege :: show-privilege-list-frag";

	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addupdateuserrole-modifyInMemoryPrivilegeDetails/{action}", method = RequestMethod.GET)
	public String modifyInMemoryPrivilegeDetailsForBulkUpdate(Model model, @PathVariable("action") String action,
			HttpSession session) {
		logger.info("triggering modifyInMemoryRoleDetailsForBulkUpdate method,with action {}",action );
		List<PrivilegeDTO> originalPrivilegeListDTO = (List<PrivilegeDTO>) session.getAttribute("originalPrivilegeList");
		List<PrivilegeDTO> roleAssignedPrivilegeList = (List<PrivilegeDTO>) session.getAttribute("roleAssignedPrivilegeList");
		if (null != action && action.equals("add")) {
			if (null != roleAssignedPrivilegeList && null != originalPrivilegeListDTO) {
				for (PrivilegeDTO privilegeDTO : originalPrivilegeListDTO) {
					roleAssignedPrivilegeList.add(privilegeDTO);
				}
				originalPrivilegeListDTO.removeAll(originalPrivilegeListDTO);
				RolePrivilegeDTOListHolder privilegeDTOListHolder = new RolePrivilegeDTOListHolder();
				privilegeDTOListHolder.setUnassignedPrivilegDTOList(originalPrivilegeListDTO);
				privilegeDTOListHolder.setRoleAssignedPrivilegDTOList(roleAssignedPrivilegeList);
				model.addAttribute("privilegeDTOHolder", privilegeDTOListHolder);
			}
		} else if (null != action && action.equals("remove")) {
			if (null != roleAssignedPrivilegeList && null != originalPrivilegeListDTO) {
				for (PrivilegeDTO privilegeDTO : roleAssignedPrivilegeList) {
					originalPrivilegeListDTO.add(privilegeDTO);
				}
				roleAssignedPrivilegeList.removeAll(roleAssignedPrivilegeList);
				RolePrivilegeDTOListHolder privilegeDTOListHolder = new RolePrivilegeDTOListHolder();
				privilegeDTOListHolder.setUnassignedPrivilegDTOList(originalPrivilegeListDTO);
				privilegeDTOListHolder.setRoleAssignedPrivilegDTOList(roleAssignedPrivilegeList);
				model.addAttribute("privilegeDTOHolder", privilegeDTOListHolder);
			}
		}
		session.setAttribute("roleAssignedPrivilegeList", roleAssignedPrivilegeList);
		session.setAttribute("originalPrivilegeList", originalPrivilegeListDTO);
		return "roleprivilege :: show-privilege-list-frag";

	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addupdateuserrole-updateRolePrivilegeChanges", method = RequestMethod.POST)
	public DeferredResult<String> updateRolePrivilegeChanges(Model model, HttpSession session) {
		logger.info("triggering updateUserRoleChanges method");
		long userID = 0;
		List<PrivilegeDTO> roleAssignedPrivilegeList = (List<PrivilegeDTO>) session.getAttribute("roleAssignedPrivilegeList");
		String roleID = (String) session.getAttribute("selectedRoleID");
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		RolePrivilegeDTOListHolder privilegeDTOListHolder = new RolePrivilegeDTOListHolder();
		privilegeDTOListHolder.setRoleAssignedPrivilegDTOList(roleAssignedPrivilegeList);
		privilegeDTOListHolder.setRoleID(roleID != null ? Long.parseLong(roleID) : 0);
		String sUserID = (String)session.getAttribute("userID");
		if(null != sUserID){
			userID = Long.parseLong(sUserID);
		}
		privilegeDTOListHolder.setCreatedBy(userID);
		logger.debug("triggering userRoleService.updateRolePrivilegeChanges method,with updateRoleDTO {}", privilegeDTOListHolder);
		try{
			ListenableFuture futureForUpdateRoles = rolePrivilegeClientService.updateRolePrivilegeChanges(privilegeDTOListHolder);	
			logger.debug("completed call userRoleService.updateUserRoleChanges method,with updateRoleDTO {}");
			futureForUpdateRoles.addCallback(new ListenableFutureCallback<UpdateRoleDTO>() {
				@Override
				public void onSuccess(UpdateRoleDTO updateRoleDTO) {
					logger.debug("updateUserRoleChanges completed successfully,returning updateRoleDTO {}");
					model.addAttribute("updateUserRoleStatus", "complete");
					deferredResult.setResult("roleprivilege :: addupdateuserrole-updatedata-result-status-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("updateUserRoleChanges call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("roleprivilege :: addupdateuserrole-updatedata-result-status-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("roleprivilege :: addupdateuserrole-updatedata-result-status-frag");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("roleprivilege :: addupdateuserrole-updatedata-result-status-frag");
		}
		
		return deferredResult;

	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/manageusergroupcriteria", method = RequestMethod.GET)
	public DeferredResult<String>  fetchAllUserGroups(Model model, HttpSession session) {
		logger.info("triggering fetchAllUserGroups method");
		DeferredResult<String> deferredResult = new DeferredResult<String>();	
		try{
			ListenableFuture futureForAllUserGroups = userGroupCriteriaService.fetchAllUserGroups();
			futureForAllUserGroups.addCallback(new ListenableFutureCallback<List>() {
				@Override
				public void onSuccess(List allUserGroupList) {
					logger.debug("fetchAllUserGroups completed successfully,returning allUserGroupList {}");
					model.addAttribute("allUserGroupList", allUserGroupList);
					deferredResult.setResult("manageusergroupcriteria");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("fetchAllUserGroups call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroupcriteria");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroupcriteria");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageusergroupcriteria");
		}
		
		return deferredResult;

	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/fetchcriteriatagsforusergroupid/{usergroupid}", method = RequestMethod.GET)
	public DeferredResult<String>  fetchCriteriaDetailsforUserGroupID(Model model, HttpSession session,@PathVariable("usergroupid") String userGroupID) {
		logger.info("triggering fetchCriteriaDetailsforUserGroupID method");
		DeferredResult<String> deferredResult = new DeferredResult<String>();		
		try{
			ListenableFuture futureForAllUserGroups = userGroupCriteriaService.fetchCriteriaTagsForUserGroupID(userGroupID);
			futureForAllUserGroups.addCallback(new ListenableFutureCallback<CriteriaTagWrapperDTO>() {
				@Override
				public void onSuccess(CriteriaTagWrapperDTO criteriaTagWrapperDTO) {
					logger.debug("fetchCriteriaDetailsforUserGroupID completed successfully,returning criteriaTagWrapperDTO {}");
					model.addAttribute("criteriaTagObjectWrapper", criteriaTagWrapperDTO);
					session.setAttribute("criteriaTagObjectWrapper", criteriaTagWrapperDTO);
					session.setAttribute("userGroupID", userGroupID);
					deferredResult.setResult("manageusergroupcriteria :: show-criteriatag-list-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("fetchCriteriaDetailsforUserGroupID call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroupcriteria :: show-criteriatag-list-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroupcriteria :: show-criteriatag-list-frag");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageusergroupcriteria :: show-criteriatag-list-frag");
		}
		
		return deferredResult;

	}
	
	
	@RequestMapping(value = "/addupdatecriteriaTags-modifyInMemoryCriteriaTagDetailsForSingleUpdates/{criteria_id}/{action}", method = RequestMethod.GET)
	public String modifyInMemoryCriteriaDetailsForSingleUpdate1s(Model model, @PathVariable("criteria_id") String criteria_id,
			@PathVariable("action") String action, HttpSession session) {
		logger.info("triggering modifyInMemoryCriteriaDetailsForSingleUpdate1s method,with action "+ action + " criteria_id : "+ criteria_id);
		CriteriaTagWrapperDTO criteriaTagWrapperDTO = (CriteriaTagWrapperDTO) session.getAttribute("criteriaTagObjectWrapper");		
		if (null != action && action.equals("add") && null != criteria_id) {
			if (null != criteriaTagWrapperDTO) {	
				CriteriaTagDTO criteriaTagDTO = criteriaTagWrapperDTO.getCriteriaTagDTOMap().get(criteria_id);
				if (null != criteriaTagDTO) {
					criteriaTagWrapperDTO.getAssignedCriteriaTagDTOList().add(criteriaTagDTO);
					criteriaTagWrapperDTO.getUnassignedCriteriaTagDTOList().remove(criteriaTagDTO);
				}				
				model.addAttribute("criteriaTagObjectWrapper", criteriaTagWrapperDTO);
			}
		} else if (null != action && action.equals("remove") && null != criteria_id) {
			if (null != criteriaTagWrapperDTO && null != criteriaTagWrapperDTO.getCriteriaTagDTOMap()) {	
				CriteriaTagDTO criteriaTagDTO = criteriaTagWrapperDTO.getCriteriaTagDTOMap().get(criteria_id);
				if (null != criteriaTagDTO) {
					criteriaTagWrapperDTO.getAssignedCriteriaTagDTOList().remove(criteriaTagDTO);
					criteriaTagWrapperDTO.getUnassignedCriteriaTagDTOList().add(criteriaTagDTO);
				}
				model.addAttribute("criteriaTagObjectWrapper", criteriaTagWrapperDTO);
			}
		}
		session.setAttribute("criteriaTagObjectWrapper", criteriaTagWrapperDTO);
		return "manageusergroupcriteria :: show-criteriatag-list-frag";

	}
	



	@RequestMapping(value = "/addupdatecriteriaTags-modifyInMemoryCriteriaTagDetails/{action}", method = RequestMethod.GET)
	public String modifyInMemoryCriteriaTagDetailsForBulkUpdate(Model model, @PathVariable("action") String action,
			HttpSession session) {
		logger.info("triggering modifyInMemoryCriteriaTagDetailsForBulkUpdate method,with action "+ action);
		CriteriaTagWrapperDTO criteriaTagWrapperDTO = (CriteriaTagWrapperDTO) session.getAttribute("criteriaTagObjectWrapper");			
		if (null != action && action.equals("add")) {
			if (null != criteriaTagWrapperDTO) {	
				for (CriteriaTagDTO criteriaTagDTO : criteriaTagWrapperDTO.getUnassignedCriteriaTagDTOList()) {
					criteriaTagWrapperDTO.getAssignedCriteriaTagDTOList().add(criteriaTagDTO);
				}
				criteriaTagWrapperDTO.getUnassignedCriteriaTagDTOList().removeAll(criteriaTagWrapperDTO.getUnassignedCriteriaTagDTOList());				
				model.addAttribute("criteriaTagObjectWrapper", criteriaTagWrapperDTO);
			}
		} else if (null != action && action.equals("remove")) {
			if (null != criteriaTagWrapperDTO) {	
				for (CriteriaTagDTO criteriaTagDTO : criteriaTagWrapperDTO.getAssignedCriteriaTagDTOList()) {
					criteriaTagWrapperDTO.getUnassignedCriteriaTagDTOList().add(criteriaTagDTO);
				}
				criteriaTagWrapperDTO.getAssignedCriteriaTagDTOList().removeAll(criteriaTagWrapperDTO.getAssignedCriteriaTagDTOList());				
				model.addAttribute("criteriaTagObjectWrapper", criteriaTagWrapperDTO);
			}
		}	
		
		session.setAttribute("criteriaTagObjectWrapper", criteriaTagWrapperDTO);
		return "manageusergroupcriteria :: show-criteriatag-list-frag";

	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateCriteriaTagDetails", method = RequestMethod.POST)
	public DeferredResult<String> updateCriteriaTagDetailChanges(Model model, HttpSession session) {
		logger.info("triggering updateCriteriaTagDetailChanges method");
		DeferredResult<String> deferredResult = new DeferredResult<String>();	
		CriteriaTagWrapperDTO criteriaTagWrapperDTO = (CriteriaTagWrapperDTO) session.getAttribute("criteriaTagObjectWrapper");			
		String sUserID = (String)session.getAttribute("userID");
		String userGroupID = (String)session.getAttribute("userGroupID");
		if(null != criteriaTagWrapperDTO){
			criteriaTagWrapperDTO.setCriteriaTagDTOMap(null);
			criteriaTagWrapperDTO.setUnassignedCriteriaTagDTOList(null);
		}
		try{
			ListenableFuture futureForUpdateRoles = userGroupCriteriaService.updateUserGroupCriteriaTagChanges(criteriaTagWrapperDTO, sUserID, userGroupID);	
			futureForUpdateRoles.addCallback(new ListenableFutureCallback<UpdateRoleDTO>() {
				@Override
				public void onSuccess(UpdateRoleDTO updateRoleDTO) {
					logger.debug("updateCriteriaTagDetailChanges completed successfully,returning updateRoleDTO {}");
					model.addAttribute("updateCriteriaStatus", "complete");
					deferredResult.setResult("manageusergroupcriteria :: addupdatecriteriatag-updatedata-result-status-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("updateCriteriaTagDetailChanges call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroupcriteria :: addupdatecriteriatag-updatedata-result-status-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroupcriteria :: addupdatecriteriatag-updatedata-result-status-frag");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageusergroupcriteria :: addupdatecriteriatag-updatedata-result-status-frag");
		}
		
		return deferredResult;

	}

	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/manageroles", method = RequestMethod.GET)
	public DeferredResult<String>  manageRole(Model model, HttpSession session) {
		logger.info("triggering manageRole method");
		SelectedRoleDTO selectedRoleDTO = new SelectedRoleDTO();		
		List<Long> adminPrivilegeList = (List<Long>) session.getAttribute("adminPrivilegeIDList");
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		String userID = (String) session.getAttribute("userID");	
		try{
			ListenableFuture futureForUpdateRoles = roleService.fetchAllRoleForManagingRoles(adminPrivilegeList,userID);	
			futureForUpdateRoles.addCallback(new ListenableFutureCallback<RolePrivilegeListDTO>() {
				@Override
				public void onSuccess(RolePrivilegeListDTO rolePrivilegeListDTO) {
					logger.debug("manageRole completed successfully,returning rolePrivilegeListDTO {}");
					model.addAttribute("rolePrivilegeListDTO", rolePrivilegeListDTO);
					session.setAttribute("allPivilegeListDTO", rolePrivilegeListDTO.getPrivilegeListDTO() != null ? rolePrivilegeListDTO.getPrivilegeListDTO() : new ArrayList<PrivilegeDTO>());
					session.setAttribute("allRoleListDTO", rolePrivilegeListDTO.getRoleDTOList());
					session.setAttribute("departmentlLookupDTOList", rolePrivilegeListDTO.getDepartmentLookupListDTO());
					session.setAttribute("departmentlLookupMap", generateLookupDTOMap(rolePrivilegeListDTO.getDepartmentLookupListDTO()));
					session.setAttribute("roleDTOMap", generateRoleDTOMap(rolePrivilegeListDTO.getRoleDTOList()));				
					model.addAttribute("selectedRoleObj", selectedRoleDTO);
					model.addAttribute("departmentlLookupDTOList", rolePrivilegeListDTO.getDepartmentLookupListDTO());
					model.addAttribute("allPivilegeListDTO",rolePrivilegeListDTO.getPrivilegeListDTO() != null ? rolePrivilegeListDTO.getPrivilegeListDTO() : new ArrayList<PrivilegeDTO>());
					deferredResult.setResult("manageroles");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("manageRole call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("selectedRoleObj", selectedRoleDTO);
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageroles");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageroles");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageroles");
		}
		return deferredResult;
		
	}
	
	private HashMap<Long,RoleDTO> generateRoleDTOMap(List<RoleDTO> roleDTOList){
		HashMap<Long,RoleDTO> roleDTOHashMap = new HashMap<Long, RoleDTO>();
		if(UserManagementUtils.isNotEmptyAndNotNull(roleDTOList)){
			for(RoleDTO roleDTO : roleDTOList){
				roleDTOHashMap.put(roleDTO.getRoleId(), roleDTO);
			}
		}
		return roleDTOHashMap;
	}
	
	
	private HashMap<Long,LookupDTO> generateLookupDTOMap(List<LookupDTO> lookupDTOList){
		HashMap<Long,LookupDTO> lookUpDTOHashMap = new HashMap<Long, LookupDTO>();
		if(UserManagementUtils.isNotEmptyAndNotNull(lookupDTOList)){
			for(LookupDTO lookupDTO : lookupDTOList){
				lookUpDTOHashMap.put(lookupDTO.getId(), lookupDTO);
			}
		}
		return lookUpDTOHashMap;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/loadprivilegedetailsforselectedroleid/{role_id}", method = RequestMethod.GET)
	public DeferredResult<String>  loadPrivilegeDetailsForSelectedRoleID(Model model, HttpSession session, @PathVariable("role_id") long roleID) {
		logger.info("triggering loadPrivilegeDetailsForSelectedRoleID method");
		SelectedRoleDTO selectedRoleDTO = new SelectedRoleDTO();		
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		try{
			ListenableFuture futureForPrivilegeListDTO = privilegeService.getPrivilegeForRoleID(roleID);	
			futureForPrivilegeListDTO.addCallback(new ListenableFutureCallback<List<PrivilegeDTO>>() {
				@Override
				public void onSuccess(List<PrivilegeDTO> privilegeDTOList) {
					logger.debug("loadPrivilegeDetailsForSelectedRoleID completed successfully,returning updateRoleDTO {}");
					List<PrivilegeDTO>	privilegeListWithRoleAssigPrivSelected = checkForRoleAssignedPrivileges((List<PrivilegeDTO>)session.getAttribute("allPivilegeListDTO"),privilegeDTOList);
					model.addAttribute("allPivilegeListDTO", privilegeListWithRoleAssigPrivSelected != null ? privilegeListWithRoleAssigPrivSelected : new ArrayList<PrivilegeDTO>());
					model.addAttribute("selectedRoleObj", populateSelectedRoleDTO(roleID,(Map<Long,RoleDTO>)session.getAttribute("roleDTOMap"),(Map<Long,LookupDTO>)session.getAttribute("departmentlLookupMap"),selectedRoleDTO));
					model.addAttribute("departmentlLookupDTOList", session.getAttribute("departmentlLookupDTOList"));
					deferredResult.setResult("manageroles :: manageroles-form-load-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("loadPrivilegeDetailsForSelectedRoleID call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("selectedRoleObj", selectedRoleDTO);
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageroles :: manageroles-form-load-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageroles :: manageroles-form-load-frag");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageroles :: manageroles-form-load-frag");
		}
		return deferredResult;
		
	}
	
	

	@SuppressWarnings("rawtypes")
	private List<PrivilegeDTO> checkForRoleAssignedPrivileges(List<PrivilegeDTO> allPrivilegeListDTO,List privilegeDTOList){
		List<PrivilegeDTO> privilegeListWithRoleAssigPrivSelected =  null;		
		if(UserManagementUtils.isNotEmptyAndNotNull(allPrivilegeListDTO) && UserManagementUtils.isNotEmptyAndNotNull(privilegeDTOList)){
			Map<String,LinkedHashMap> privilegeDTOMap = new HashMap<String, LinkedHashMap>();
			for(Object privilegeDTOObj : privilegeDTOList){
				LinkedHashMap privilegeDTOObjMap = (LinkedHashMap)privilegeDTOObj;
				privilegeDTOMap.put(String.valueOf(privilegeDTOObjMap.get("privilegeId")), privilegeDTOObjMap);
			}
			privilegeListWithRoleAssigPrivSelected = new ArrayList<PrivilegeDTO>();
			for(PrivilegeDTO privilegeDTO : allPrivilegeListDTO){
				if(privilegeDTOMap.get(String.valueOf(privilegeDTO.getPrivilegeId())) != null){
					privilegeDTO.setSelected(true);
				}
				privilegeListWithRoleAssigPrivSelected.add(privilegeDTO);
			}
		}
		return privilegeListWithRoleAssigPrivSelected;
	}
	
	
	private SelectedRoleDTO populateSelectedRoleDTO(long roleID,Map<Long,RoleDTO> roleDTOMap,Map<Long,LookupDTO> departementDTOMap,SelectedRoleDTO selectedRoleDTO){
		RoleDTO roleDTO = roleDTOMap.get(roleID);
		if(null != roleDTO){
			selectedRoleDTO.setActive(roleDTO.isActive());
			selectedRoleDTO.setSelectedRoleID(roleDTO.getRoleId());
			selectedRoleDTO.setDepartmentID(roleDTO.getDepartmentId());
			selectedRoleDTO.setDescriptionAr(roleDTO.getDescriptionAr());
			selectedRoleDTO.setDescriptionEn(roleDTO.getDescriptionEn());
			selectedRoleDTO.setRemarks(roleDTO.getRemarks());
			selectedRoleDTO.setRoleName(roleDTO.getRoleName());			
			LookupDTO lookupDTO =  departementDTOMap.get(roleDTO.getDepartmentId());
			if(null != lookupDTO){
				selectedRoleDTO.setDepartmentNameAr(lookupDTO.getDescArabic());
				selectedRoleDTO.setDepartmentNameEn(lookupDTO.getDescEnglish());
			}
			
		}		
		
		return selectedRoleDTO;				
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/manageRoles/{selected_role_id}/{department_id}", method = RequestMethod.POST)
	public DeferredResult<String> updateManageRoleChanges(Model model, HttpSession session,@ModelAttribute(value = "selectedRoleObj") SelectedRoleDTO selectedRoleDTO,@PathVariable("selected_role_id") long selectedRoleID,
																							@PathVariable("department_id") long departmentId) {
		logger.info("triggering updateManageRoleChanges method");
		DeferredResult<String> deferredResult = new DeferredResult<String>();	
		String sUserID = (String)session.getAttribute("userID");
		selectedRoleDTO.setSelectedRoleID(selectedRoleID);
		selectedRoleDTO.setDepartmentID(departmentId);
		try{
			ListenableFuture futureForUpdateRoles = roleService.updateManageRoleChanges(selectedRoleDTO, sUserID);
			logger.debug("completed call userRoleService.updateUserRoleChanges method,with updateRoleDTO {}");
			futureForUpdateRoles.addCallback(new ListenableFutureCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					logger.debug("updateManageRoleChanges completed successfully,returning Void {}");
					model.addAttribute("updateRoleStatus", "complete");
					deferredResult.setResult("manageroles :: managerole-updatedata-status-frag");
					
				}			
				@Override
				public void onFailure(Throwable t) {
					logger.error("updateManageRoleChanges call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageroles :: managerole-updatedata-status-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageroles :: managerole-updatedata-status-frag");
					}
				}

		});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageroles :: managerole-updatedata-status-frag");
		}
		
		return deferredResult;

	}
	
	
	private void populateManageUserGroupDTOMapAttributes(ManageUserGroupWrapperDTO manageUserGroupWrapperDTO){
		if(null != manageUserGroupWrapperDTO){
			if(UserManagementUtils.isNotEmptyAndNotNull(manageUserGroupWrapperDTO.getRoleGroups())){
				Map<Long,RoleGroupDTO> roleGroupDTOHashMap = new HashMap<Long,RoleGroupDTO>();
				for(RoleGroupDTO roleGroupDTO : manageUserGroupWrapperDTO.getRoleGroups()){
					roleGroupDTOHashMap.put(roleGroupDTO.getRoleGroupId(), roleGroupDTO);
				}
				manageUserGroupWrapperDTO.setRoleGroupsMap(roleGroupDTOHashMap);
			}
			if(UserManagementUtils.isNotEmptyAndNotNull(manageUserGroupWrapperDTO.getApplicationUsers())){
				Map<Long,ApplicationUserDTO> applicationUserDTOHashMap = new HashMap<Long,ApplicationUserDTO>();
				for(ApplicationUserDTO applicationUserDTO : manageUserGroupWrapperDTO.getApplicationUsers()){
					applicationUserDTOHashMap.put(applicationUserDTO.getUserId(), applicationUserDTO);
				}
				manageUserGroupWrapperDTO.setApplicationUserMap(applicationUserDTOHashMap);
			}
			if(UserManagementUtils.isNotEmptyAndNotNull(manageUserGroupWrapperDTO.getUserGroups())){
				Map<Long,UserGroupDTO> userGroupDTOHashMap = new HashMap<Long,UserGroupDTO>();
				for(UserGroupDTO userGroupDTO : manageUserGroupWrapperDTO.getUserGroups()){
					userGroupDTOHashMap.put(userGroupDTO.getUserGroupId(), userGroupDTO);
				}
				manageUserGroupWrapperDTO.setUserGroupsMap(userGroupDTOHashMap);
			}
			
			
		}
		
	}
	
	private void populateManageRoleGroupDTOMapAttributes(ManageRoleGroupWrapperDTO manageRoleGroupWrapperDTO){
		if(null != manageRoleGroupWrapperDTO){
			if(UserManagementUtils.isNotEmptyAndNotNull(manageRoleGroupWrapperDTO.getRoleGroupDTOList())){
				Map<Long,RoleGroupDTO> roleGroupDTOHashMap = new HashMap<Long,RoleGroupDTO>();
				for(RoleGroupDTO roleGroupDTO : manageRoleGroupWrapperDTO.getRoleGroupDTOList()){
					roleGroupDTOHashMap.put(roleGroupDTO.getRoleGroupId(), roleGroupDTO);
				}
				manageRoleGroupWrapperDTO.setRoleGroupDTOMap(roleGroupDTOHashMap);
			}
			if(UserManagementUtils.isNotEmptyAndNotNull(manageRoleGroupWrapperDTO.getRoleDTOList())){
				Map<Long,RoleDTO> roleDTOHashMap = new HashMap<Long,RoleDTO>();
				for(RoleDTO roleDTO : manageRoleGroupWrapperDTO.getRoleDTOList()){
					roleDTOHashMap.put(roleDTO.getRoleId(), roleDTO);
				}
				manageRoleGroupWrapperDTO.setRoleDTOMap(roleDTOHashMap);
			}						
		}
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/manageusergroups", method = RequestMethod.GET)
	public DeferredResult<String>  manageUserGroup(Model model, HttpSession session) {
		logger.info("triggering manageRole method");
		SelectedRoleDTO selectedRoleDTO = new SelectedRoleDTO();		
		List<Long> adminPrivilegeList = (List<Long>) session.getAttribute("adminPrivilegeIDList");
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		String userID = (String) session.getAttribute("userID");	
		try{
			ListenableFuture futureForManageUserGroupDetails = userGroupClientService.fetchManageUserGroupDetails(userID, adminPrivilegeList);
			futureForManageUserGroupDetails.addCallback(new ListenableFutureCallback<ManageUserGroupWrapperDTO>() {
				@Override
				public void onSuccess(ManageUserGroupWrapperDTO manageUserGroupWrapperDTO) {				
					logger.debug("manageRole completed successfully,returning rolePrivilegeListDTO {}");
					populateManageUserGroupDTOMapAttributes(manageUserGroupWrapperDTO);
					model.addAttribute("manageUserGroupWrapperDTO", manageUserGroupWrapperDTO);
					model.addAttribute("manageUserGroupDTO",new ManageUserGroupDTO());
					model.addAttribute("appUserList", manageUserGroupWrapperDTO.getApplicationUsers());
					session.setAttribute("manageUserGroupWrapperDTO", manageUserGroupWrapperDTO);
					deferredResult.setResult("manageusergroup");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("manageRole call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("selectedRoleObj", selectedRoleDTO);
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroup");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroup");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageusergroup");
		}
		
		return deferredResult;
		
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchuser-manageeusergroup", method = RequestMethod.GET)
	public DeferredResult<String> retrieveUserDetailsForManageUserGroup(
			@RequestParam (name = "login", required = false) String login,@RequestParam (name = "name", required =false ) 
			String name,@RequestParam (name = "mobile_no",required = false) String mobileNo, Model model) {		
		UserSearchDTO userSearchDTO = new UserSearchDTO();
		userSearchDTO.setLoginName(login);
		userSearchDTO.setMobileNo(mobileNo);
		userSearchDTO.setName(name);
		logger.info("triggering retrievUserDetailsForUpdate method,with userSearchDTO {}", userSearchDTO);
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		logger.debug("triggering userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}");
		try{
			ListenableFuture<List> future = userManagementService.findUserListBasedonSearchCriteria(userSearchDTO);
			logger.debug("completed call userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}");
			future.addCallback(new ListenableFutureCallback<List>() {
				@Override
				public void onSuccess(List userDTOList) {
					logger.debug("retrievUserDetailsForUpdate completed successfully,returning userDTOList {}");
					model.addAttribute("searchcompletestatus", "complete");
					model.addAttribute("appUserList", userDTOList);
					deferredResult.setResult("manageusergroup :: manageusergroup-appuser-list-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("retrievUserDetailsForUpdate call failed with error {}",t);	
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroup :: manageusergroup-appuser-list-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroup :: manageusergroup-appuser-list-frag");
					}
					
				}
		});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageusergroup :: manageusergroup-appuser-list-frag");
		}

		return deferredResult;

	}
	
	
	
	@RequestMapping(value = "/loadusergroupdetails-forselected-groupid/{user_group_id}", method = RequestMethod.GET)
	public String loadUserGroupDetaiksForSelectedGroupID(Model model, HttpSession session,@PathVariable("user_group_id") long userGroupID) {		
		logger.debug("triggering userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}");
		ManageUserGroupWrapperDTO manageUserGroupWrapperDTO = (ManageUserGroupWrapperDTO)session.getAttribute("manageUserGroupWrapperDTO");
		if(null != manageUserGroupWrapperDTO){
			Map<Long, UserGroupDTO> userGroupMap = manageUserGroupWrapperDTO.getUserGroupsMap();
			if(null != userGroupMap){
				UserGroupDTO userGroupDTO = userGroupMap.get(userGroupID);
				if(null != userGroupDTO){				
					ManageUserGroupDTO manageUserGroupDTO = new ManageUserGroupDTO();
					manageUserGroupDTO.setGroupNameAr(userGroupDTO.getNameAr());
					manageUserGroupDTO.setGroupNameEn(userGroupDTO.getNameEn());
					manageUserGroupDTO.setRoleGroupID(userGroupDTO.getRoleGroup().getRoleGroupId());
					manageUserGroupDTO.setSelectedUserGroupID(userGroupDTO.getUserGroupId());
					manageUserGroupDTO.setSelectedUserGroupRoleNameAr(userGroupDTO.getRoleGroup().getNameAr());
					manageUserGroupDTO.setSelectedUserGroupRoleNameEn(userGroupDTO.getRoleGroup().getNameEn());
					model.addAttribute("manageUserGroupDTO",manageUserGroupDTO);
					model.addAttribute("manageUserGroupWrapperDTO", manageUserGroupWrapperDTO);
					model.addAttribute("appUserList", markApplicationUsersForSelectedGroupID(manageUserGroupWrapperDTO.getApplicationUsers(),userGroupID));
				}
				
			}
		}		
		return "manageusergroup :: manageusergroup-form-load-frag";

	}
	
	
	private List<ApplicationUserDTO> markApplicationUsersForSelectedGroupID(List<ApplicationUserDTO> applicationUserList, long userGroupID){
		List<ApplicationUserDTO> applicationUserDTOs = new ArrayList<ApplicationUserDTO>();
		if(UserManagementUtils.isNotEmptyAndNotNull(applicationUserList)){
			for(ApplicationUserDTO applicationUserDTO : applicationUserList){
				if(null != applicationUserDTO.getUserGroup() && applicationUserDTO.getUserGroup().getUserGroupId() == userGroupID){
					applicationUserDTO.setSelected(true);
				}
				applicationUserDTOs.add(applicationUserDTO);
			}
		}		
		return applicationUserDTOs;
		
	}
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/manageusergroups", method = RequestMethod.POST)
	public DeferredResult<String> updateManageUserGroupChanges(Model model, HttpSession session,@ModelAttribute(value = "manageUserGroupDTO") ManageUserGroupDTO manageUserGroupDTO) {
		logger.info("triggering updateManageRoleChanges method");
		DeferredResult<String> deferredResult = new DeferredResult<String>();	
		try{
			ListenableFuture futureForUpdateManageUserGroup = userGroupClientService.updateManageUserGroupDetails(manageUserGroupDTO);
			logger.debug("completed call userRoleService.updateUserRoleChanges method,with updateRoleDTO {}");
			futureForUpdateManageUserGroup.addCallback(new ListenableFutureCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					logger.debug("updateManageRoleChanges completed successfully,returning Void {}");
					model.addAttribute("updateManageUserGroupStatus", "complete");
					deferredResult.setResult("manageusergroup :: manageusergroup-updatedata-status-frag");
					
				}			
				@Override
				public void onFailure(Throwable t) {
					logger.error("updateManageRoleChanges call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroup :: manageusergroup-updatedata-status-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroup :: manageusergroup-updatedata-status-frag");
					}
				}

		});		
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageusergroup :: manageusergroup-updatedata-status-frag");
		}
		return deferredResult;

	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/managerolegroups", method = RequestMethod.GET)
	public DeferredResult<String>  manageRoleGroup(Model model, HttpSession session) {
		logger.info("triggering manageRole method");
		SelectedRoleDTO selectedRoleDTO = new SelectedRoleDTO();		
		List<Long> adminPrivilegeList = (List<Long>) session.getAttribute("adminPrivilegeIDList");
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		String userID = (String) session.getAttribute("userID");	
		try{
			ListenableFuture futureForManageRoleGroupDetails = roleGroupClientService.fetchRoleGroupDetails(adminPrivilegeList,userID);
			futureForManageRoleGroupDetails.addCallback(new ListenableFutureCallback<ManageRoleGroupWrapperDTO>() {
				@Override
				public void onSuccess(ManageRoleGroupWrapperDTO manageRoleGroupWrapperDTO) {				
					logger.debug("manageRole completed successfully,returning rolePrivilegeListDTO {}");
					populateManageRoleGroupDTOMapAttributes(manageRoleGroupWrapperDTO);
					model.addAttribute("manageRoleGroupWrapperDTO", manageRoleGroupWrapperDTO);
					model.addAttribute("manageRoleGroupDTO",new ManageRoleGroupDTO());
					model.addAttribute("roleDTOList",manageRoleGroupWrapperDTO.getRoleDTOList());
					session.setAttribute("manageRoleGroupWrapperDTO", manageRoleGroupWrapperDTO);
					deferredResult.setResult("managerolegroup");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("manageRole call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("selectedRoleObj", selectedRoleDTO);
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("managerolegroup");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("managerolegroup");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("managerolegroup");
		}
		return deferredResult;
		
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/loadrolegroupdetails-forselected-groupid/{role_group_id}", method = RequestMethod.GET)
	public DeferredResult<String> loadRoleGroupDetaiksForSelectedGroupID(Model model, HttpSession session,@PathVariable("role_group_id") long roleGroupID) {		
		logger.debug("triggering userManagementService.findUserListBasedonSearchCriteria method,with userSearchDTO {}");
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		ManageRoleGroupWrapperDTO manageRoleGroupWrapperDTO = (ManageRoleGroupWrapperDTO)session.getAttribute("manageRoleGroupWrapperDTO");
		try{
			ListenableFuture futureForManageRoleGroupDetails = roleGroupClientService.fetchRoleGroupDetailsForSelectedGroupID(roleGroupID);
			futureForManageRoleGroupDetails.addCallback(new ListenableFutureCallback<List<RoleGroupRoleDTO>>() {
				@Override
				public void onSuccess(List<RoleGroupRoleDTO> roleGroupRoleDTOList) {				
					logger.debug("manageRole completed successfully,returning rolePrivilegeListDTO {}");
					model.addAttribute("manageRoleGroupDTO",generateManageRoleGroupDTO(roleGroupRoleDTOList,roleGroupID));
					model.addAttribute("roleDTOList",generateRoleDTOListForSelectedRoleGroup(roleGroupRoleDTOList,manageRoleGroupWrapperDTO));
					deferredResult.setResult("managerolegroup :: managerolegroup-form-load-frag");
				}
	
				@Override
				public void onFailure(Throwable t) {
					logger.error("manageRole call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("managerolegroup :: managerolegroup-form-load-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("managerolegroup :: managerolegroup-form-load-frag");
					}
				}
			});
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("managerolegroup :: managerolegroup-form-load-frag");
		}
		return deferredResult;
		


	}
	
	@SuppressWarnings("rawtypes")
	private List<RoleDTO> generateRoleDTOListForSelectedRoleGroup(List roleGroupRoleDTOList,ManageRoleGroupWrapperDTO manageRoleGroupWrapperDTO){
		List<RoleDTO> roleDTOListMarkedForSelected = new ArrayList<RoleDTO>();
		List<RoleDTO> roleDTOList = manageRoleGroupWrapperDTO.getRoleDTOList();
		if(UserManagementUtils.isNotEmptyAndNotNull(roleDTOList)){
			for(RoleDTO roleDTO : roleDTOList){
				for(Object object : roleGroupRoleDTOList){
					LinkedHashMap roleGroupRoleDTOObjMap = (LinkedHashMap)object;
					LinkedHashMap roleDTOMapFromRoleGroupRoleObj = (LinkedHashMap)roleGroupRoleDTOObjMap.get("role");
					if(null != roleDTOMapFromRoleGroupRoleObj && null != roleDTOMapFromRoleGroupRoleObj.get("roleId") && (Integer)roleDTOMapFromRoleGroupRoleObj.get("roleId") == roleDTO.getRoleId()){
						roleDTO.setSelected(true);
					}
					roleDTOListMarkedForSelected.add(roleDTO);
				}
			}
		}				
		
		return roleDTOListMarkedForSelected;
	}
	
	@SuppressWarnings("rawtypes")
	private ManageRoleGroupDTO generateManageRoleGroupDTO(List roleGroupRoleDTOList,long roleGroupID){
		ManageRoleGroupDTO manageRoleGroupDTO = new ManageRoleGroupDTO();
		if(UserManagementUtils.isNotEmptyAndNotNull(roleGroupRoleDTOList)){
			LinkedHashMap privilegeDTOObjMap = (LinkedHashMap)roleGroupRoleDTOList.get(0);			
			LinkedHashMap roleGroupDTOMap = (LinkedHashMap)privilegeDTOObjMap.get("roleGroup");
			if(null != roleGroupDTOMap){
				manageRoleGroupDTO.setActive(roleGroupDTOMap.get("isActive") != null ? (Boolean)roleGroupDTOMap.get("isActive") : false);
				manageRoleGroupDTO.setGroupNameAr(roleGroupDTOMap.get("nameAr") != null ? (String)roleGroupDTOMap.get("nameAr") : "");
				manageRoleGroupDTO.setGroupNameEn(roleGroupDTOMap.get("nameEn") != null ? (String)roleGroupDTOMap.get("nameEn") : "");
				manageRoleGroupDTO.setSelectedRoleGroupID(roleGroupID);
			}
		}
		
		return manageRoleGroupDTO;
	}
	
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/managerolegroups", method = RequestMethod.POST)
	public DeferredResult<String> updateManageRoleGroupChanges(Model model, HttpSession session,@ModelAttribute(value = "manageRoleGroupDTO") ManageRoleGroupDTO manageRoleGroupDTO) {
		logger.info("triggering updateManageRoleChanges method");
		DeferredResult<String> deferredResult = new DeferredResult<String>();	
		String userID = (String)session.getAttribute("userID");
		if(null != userID){
			manageRoleGroupDTO.setUserID(Long.parseLong(userID));
		}
		try{
			ListenableFuture futureForUpdateManageUserGroup = roleGroupClientService.updateManageRoleGroupDetails(manageRoleGroupDTO);
			logger.debug("completed call userRoleService.updateUserRoleChanges method,with updateRoleDTO {}");
			futureForUpdateManageUserGroup.addCallback(new ListenableFutureCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					logger.debug("updateManageRoleChanges completed successfully,returning Void {}");
					model.addAttribute("updateManageUserGroupStatus", "complete");
					deferredResult.setResult("manageusergroup :: manageusergroup-updatedata-status-frag");
					
				}			
				@Override
				public void onFailure(Throwable t) {
					logger.error("updateManageRoleChanges call failed with error {}",t);
					ServerErrorResponse errorResponse = ServletResponseUtils.handleErrorResponse(t);				
					if(null != errorResponse && errorResponse.getStatus() == 404){
						model.addAttribute("searchcompletestatus", "complete");
						model.addAttribute("warnResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroup :: manageusergroup-updatedata-status-frag");
					}else{
						model.addAttribute("errorResponseDesc",errorResponse.getMessage());
						deferredResult.setResult("manageusergroup :: manageusergroup-updatedata-status-frag");
					}
				}
	
			});		
		}catch(Exception e){
			logger.error("Error Occured while accessing Server :"+e );
			model.addAttribute("errorResponseDesc","Unable to access Server");
			deferredResult.setResult("manageusergroup :: manageusergroup-updatedata-status-frag");
		}
		return deferredResult;

	}
	
	
	
}
