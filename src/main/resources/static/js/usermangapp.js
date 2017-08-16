 /* Application Framework Related/Misc Scripts Starts */


var pleaseWaitDiv = $('<div class="modal" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false role="dialog">\
        <div class="modal-dialog">\
        <div class="modal-content">\
            <div class="modal-header">\
                <h4 class="modal-title">Please wait...</h4>\
            </div>\
		<div class="modal-body" style="margin-left:auto;margin-right:auto;display:block;margin-top:5%;margin-bottom:0%" >\
		<div class = "loader">\
		</div>\
        </div>\
    </div>\
</div>');


var errorOccuredDiv = $('<div style="background:#F78181 !important" class="jumbotron form-top-left">\
                <h4 class="modal-title">Error Occured while processing, Please try again...</h4>\
</div>');

 
 
 function initiateAjaxRequest(requestUrl,methodType,formObj,removeDivArray,responseRenderDiv,responseDivToHide){
	 
	 $.ajax({
	      url: requestUrl,
	      type: methodType,
	      data: serializeFormInstance(formObj),
	      beforeSend: function(jqXHR, settings) {	    	  
	    	   $("div#divLoading").addClass('show');
	      },
	      success: function(response) {
	    	  $("div#divLoading").removeClass('show');
	    	  initiatePreRenderingForSuccessResponse();
	          //Removing any existing search results and no result outputs  	           
	          if(removeDivArray){
	        	  for (var i in removeDivArray) {	        		    
	        		  $(removeDivArray[i]).remove();
	        	  }
	           }	           
	          //append the response to the result div.
	          $(responseRenderDiv).append(response);	        
	      },
	      error: function(xhr, status, error) {
	    	  $("div#divLoading").removeClass('show');
	    	  renderErrorResponse(responseDivToHide);	    	   	    	   
	    	   var errorResponseDiv = $('<div class="modal fade" id="myModal" role="dialog">\
	    			   <div class="modal-dialog">\
	    			     <div class="modal-content">\
	    			       <div class="modal-header">\
	    			         <button type="button" class="close" data-dismiss="modal">&times;</button>\
	    			         <h4 class="modal-title">Error Occured</h4>\
	    			       </div>\
	    			 		<div class="modal-body" id="errorTextDiv"><p><b>'
	    			   		+	xhr.responseText +
	    			       '</b></p></div>\
	    			       <div class="modal-footer">\
	    			         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>\
	    			       </div>\
	    			     </div>\
	    			   </div>\
	    			 </div>');	 
	    	   
	    	   errorResponseDiv.modal();
	    	  
	    	   
	       }
	  });
	 
	 
 }
 
 
 function initiatePreRenderingForSuccessResponse(){
	  $('#errorDiv').hide();
	  pleaseWaitDiv.modal('hide');
 }
 
 
 function renderErrorResponse(responseDivToHide){
	  pleaseWaitDiv.modal('hide');
      //$('#errorDiv').append(errorOccuredDiv);
      $('#errorDiv').show();
      if(responseDivToHide){
    	  for (var i in responseDivToHide) {	        		    
    		  $(responseDivToHide[i]).hide();
    	  }
      }
 }

 function serializeFormInstance(formObj){
	 if(formObj){
		 return formObj.serialize();
	 }else{
		 return null;
	 }
 }
 
 function setLocalValue(locale) {
		$('#lang').val(locale);
 }
 
 /* Application Framework Related/Misc Scripts Ends */
 
 /* Scripts for View User Screen Starts */
 
 function retrieveUserDetails() {
	 
	 var requestUrl = 'viewuser';
	 var requestMethod = 'post';
	 var responseRenderDivNam = "#viewuser-searchdata";
	 var $form = $('#viewuser-search-form');
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#viewuser-searchdata-details-results';
	 removeDivArray[1] = '#viewuser-searchdata-result';
	 removeDivArray[2] = '#noResultDiv';
	 removeDivArray[3] = '#errorDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 	 	 
 }
 
 function retrieveUserDetailsForViewUser(clicked_id) {
	 
	 var requestUrl = 'viewuser';
	 if (clicked_id  != '') {
		 requestUrl = requestUrl + '/' + clicked_id;
	 }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#viewuser-searchdata-details";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = "#viewuser-searchdata-results";
	 removeDivArray[1] = "#viewuser-searchdata-details-results";
	 removeDivArray[2] = "#noResultDiv";
	 removeDivArray[3] = "#errorDiv";
	 var responseRenderDivNamesToHide = {}; 
	 //responseRenderDivNamesToHide[0] = "#viewuser-searchdata-results";
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
 }
 
 /* Scripts for View User Screen Ends */
 
 /* Scripts for Manage User Screen Starts */
 
 function retrieveUserDetailsForManageUserListView() {
	 
	 var requestUrl = 'manageuser';
	 var requestMethod = 'post';
	 var responseRenderDivNam = "#manageuser-searchdata";
	 var $form = $('#manageuser-search-form');
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#manageuser-searchdata-result';
	 removeDivArray[1] = '#manageuser-updatedata-results';
	 removeDivArray[2] = '#updateUserSuccessDivPanel';
	 removeDivArray[3] = '#noResultDiv';
	 removeDivArray[4] = '#errorDiv';
	 removeDivArray[5] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
 }
 
 
 function retrieveUserDetailsForManageUserUpdateView(clicked_id) {
	 
	 var requestUrl = 'getUserDetailsForUpdate';
	 if (clicked_id  != '') {
		 requestUrl = requestUrl + '/' + clicked_id;
	 }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#manageuser-updatedata";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#manageuser-updatedata-results';
	 removeDivArray[1] = '#updateUserSuccessDivPanel';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
 }
 
 function updateUserDetails(userID) {
	 
	 var requestUrl = 'updateUserDetails';
	 if (userID  != '') {
		 requestUrl = requestUrl + '/' + userID;
	 }
	 var requestMethod = 'post';
	 var responseRenderDivNam = "#manageuser-updatedata-status";
	 var $form = $('#manageuser-update-form');
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#manageuser-updatedata-results';
	 removeDivArray[1] = '#updateUserSuccessDivPanel';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	
 }
 
 function changedAttributes(input){
	 input.classList.add("changed");
	
 } 
 
function bindDatePicker(){
  
	  $(".datepicker").datepicker({
	  	format: "dd/mm/yyyy",
        changeMonth: true,
        changeYear: true,
        dateonly: true
    });


 } 
 
 /* Scripts for Manage User Screen Starts */

 
 
/* Scripts for Application Logging Screen Starts */
 
 function retrieveApplicationLoggingLevel(){
	 var requestUrl = 'configuration/update/NO_VAL'
	 var requestMethod = 'get';
	 var $form = null;
	 var responseRenderDivNam = "#logging-level-selection-div";	 
	 var removeDivArray = {};
	 removeDivArray[0] = '#logging-level-selection-inline-div';
	 $("#application-logging-level-caption").hide();
	 $("#application-logging-level-val").hide();
	 $("#application-logging-level-dynval").hide();
	 $("#logging-level-selection-div").show();	
	 $("#logging-level-update-status-inline-div").hide();
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
 }
 
 function updateApplicationLoggingLevel(){
	 var requestUrl = 'configuration/update';
	 var selectedLogLevelVal = "";
	 var loglevelInput =  $('input[name=loglevel]:checked');
	 if (loglevelInput && loglevelInput.length  > 0) {
		 selectedLogLevelVal = loglevelInput.val();
		 requestUrl = requestUrl + '/' + selectedLogLevelVal;
	 }else{
		 alert('Please choose one Logging level and proceed');
		 return false;
	 }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#logging-level-update-status-div";
	 var $form = null;
	 var removeDivArray = {};
	 removeDivArray[0] = '#logging-level-update-status-inline-div';
	 removeDivArray[1] = '#configuration-details-table';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 $("#application-logging-level-caption").show();
	 $("#application-logging-level-val").show();
	 $("#application-logging-level-dynval").show();
	 $("#logging-level-selection-div").hide();	 
 }
 
 
 function retrieveBackgroundClassName(percentage){
	if(percentage && percentage >= 85){
		return 'progress-bar progress-bar-danger';
	}else if (percentage && percentage >= 70){
		return 'progress-bar progress-bar-warning';
	}else{
		return 'progress-bar progress-bar-success';
	}	
		
}
 
 /* Scripts for Application Logging Screen Starts */
 
 /* Scripts for Add/Update User Role Screen Starts */

function retrieveUserDetailsForAddUpdateUserRoleListView() {
	
	 var requestUrl = 'addupdateuserrole-searchuser';
	 var requestMethod = 'post';
	 var responseRenderDivNam = "#addupdateuserrole-searchdata";
	 var $form = $('#addupdateuserrole-search-form');
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#updateUserSuccessDivPanel';
	 removeDivArray[1] = '#addupdateuserrole-searchdata-result';
	 removeDivArray[2] = '#addupdateuserrole-populate-completeroles-result';
	 removeDivArray[3] = '#addupdateuserrole-populate-assignedroles-result';
	 removeDivArray[4] = '#noResultDiv';
	 removeDivArray[5] = '#errorDiv';
	 removeDivArray[6] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);

}
 
function retrieveRoleDetailsForAddUpdateRoleUserView(userID) {
	
	 var requestUrl = 'addupdateuserrole-fetchroledetailsforselecteduser';
	 if (userID  != '') {
		 requestUrl = requestUrl + '/' + userID;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#addupdateuserrole-populateroles";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#addupdateuserrole-populate-completeroles-result';
	 removeDivArray[1] = '#addupdateuserrole-populate-assignedroles-result';
	 removeDivArray[2] = '#noResultDiv';
	 removeDivArray[3] = '#errorDiv';
	 removeDivArray[4] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);

}
 
 
function modifyInMemoryRoleDetails(action) {
	
	 var requestUrl = 'addupdateuserrole-modifyInMemoryRoleDetails';
	 if (action  != '') {
		 requestUrl = requestUrl + '/' + action;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#addupdateuserrole-populateroles";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#addupdateuserrole-populate-completeroles-result';
	 removeDivArray[1] = '#addupdateuserrole-populate-assignedroles-result';
	 removeDivArray[2] = '#noResultDiv';
	 removeDivArray[3] = '#errorDiv';
	 removeDivArray[4] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);

}

function modifyInMemoryRoleDetailsForSingleUpdate(role_id,action) {
	
	 var requestUrl = 'addupdateuserrole-modifyInMemoryRoleDetailsForSingleUpdates';
	 if (role_id  != '') {
		 requestUrl = requestUrl + '/' + role_id;
	  }
	 if (action  != '') {
		 requestUrl = requestUrl + '/' + action;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#addupdateuserrole-populateroles";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#addupdateuserrole-populate-completeroles-result';
	 removeDivArray[1] = '#addupdateuserrole-populate-assignedroles-result';
	 removeDivArray[2] = '#noResultDiv';
	 removeDivArray[3] = '#errorDiv';
	 removeDivArray[4] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);

}


function saveUserRoleChanges(){
	
	 var requestUrl = 'addupdateuserrole-updateUserRoleChanges';
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#addupdateuserrole-updatedata-result-status";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#updateUserSuccessDivPanel';
	 removeDivArray[1] = '#addupdateuserrole-populate-completeroles-result';
	 removeDivArray[2] = '#addupdateuserrole-populate-assignedroles-result';
	 removeDivArray[3] = '#noResultDiv';
	 removeDivArray[4] = '#errorDiv';
	 removeDivArray[5] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
}

/* Scripts for Add/Update User Role Screen Ends */


/* Scripts for Role Privilege Screen Starts */

function loadPrivilege(){
	if($(roleIDSelector).val()){
		var roleID = $(roleIDSelector).val();		
		var requestUrl = "fetchprivilegesforroleid";
		 if (roleID  != '') {
			 requestUrl = requestUrl + '/' + roleID;
		  }
		var requestMethod = 'get';
		var responseRenderDivNam = "#show-privilege-list";
		var $form = null;
		var removeDivArray = {}; 
		removeDivArray[0] = '#updateRolePrivilegeSuccessDivPanell';
		removeDivArray[1] = '#show-unassigned-privilege-list';
		removeDivArray[2] = '#show-assigned-privilege-list';
		removeDivArray[3] = '#show-assigned-role-details-div';
		removeDivArray[4] = '#noResultDivParent';
		removeDivArray[5] = '#errorDivParent';
		removeDivArray[6] = '#successDiv';
		var responseRenderDivNamesToHide = {}; 
		initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
		
	}
}

function showLoadPrivilegeDiv(){
	if($(roleIDSelector).val() && $(roleIDSelector).val() != 0 ){
		$(loadPrivilgeDiv).show();
	}else{
		$(loadPrivilgeDiv).hide();
	}
}

function fetchassociatedRoles(privilegeID){
	 var requestUrl = 'fetchroleprivilegesforprivilegeid';
	if(privilegeID != ''){
		requestUrl = requestUrl + '/' + privilegeID;
	}
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#show-associated-role-list";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#show-assigned-role-details-div';
	 removeDivArray[1] = '#noResultDivParent';
	 removeDivArray[2] = '#errorDivParent';
	 removeDivArray[3] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
}


function modifyInMemoryPrivilegeDetailsForSingleUpdate(privilege_id,action){
	
	 var requestUrl = 'addupdateuserrole-modifyInMemoryPrivilegeDetailsForSingleUpdates';
	 if (privilege_id  != '') {
		 requestUrl = requestUrl + '/' + privilege_id;
	  }
	 if (action  != '') {
		 requestUrl = requestUrl + '/' + action;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#show-privilege-list";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#show-unassigned-privilege-list';
	 removeDivArray[1] = '#show-assigned-privilege-list';
	 removeDivArray[2] = '#show-assigned-role-details-div';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
	
}


function modifyInMemoryPrivilegeDetailsForBulkUpdate(action){
	
	 var requestUrl = 'addupdateuserrole-modifyInMemoryPrivilegeDetails';
	 if (action  != '') {
		 requestUrl = requestUrl + '/' + action;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#show-privilege-list";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#show-unassigned-privilege-list';
	 removeDivArray[1] = '#show-assigned-privilege-list';
	 removeDivArray[2] = '#show-assigned-role-details-div';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
	
}


function savePrivilegeRoleChanges(){
	
	 var requestUrl = 'addupdateuserrole-updateRolePrivilegeChanges';
	 var requestMethod = 'post';
	 var responseRenderDivNam = "#addupdateroleprivileges-updatedata-result-status";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#updateRolePrivilegeSuccessDivPanell';
	 removeDivArray[1] = '#show-unassigned-privilege-list';
	 removeDivArray[2] = '#show-assigned-privilege-list';
	 removeDivArray[3] = '#show-assigned-role-details-div';
	 removeDivArray[4] = '#noResultDivParent';
	 removeDivArray[5] = '#errorDivParent';
	 removeDivArray[6] = '#successDiv';
	 
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
}

/* Scripts for Role Privilege Screen Ends */


/* Scripts for User Group Criteria Screen Starts */

function loadCriteriaTags(){
	if($(userGroupIDSelector).val()){
		var userGroupID = $(userGroupIDSelector).val();		
		var requestUrl = "fetchcriteriatagsforusergroupid";
		 if (userGroupID  != '') {
			 requestUrl = requestUrl + '/' + userGroupID;
		  }
		var requestMethod = 'get';
		var responseRenderDivNam = "#show-criteriatag-list";
		var $form = null;
		var removeDivArray = {}; 
		removeDivArray[0] = '#show-unassigned-criteriatag-list';
		removeDivArray[1] = '#show-assigned-criteriatag-list';
		removeDivArray[2] = '#addupdatecriteriatagSuccessDivPanel';
		removeDivArray[3] = '#noResultDivParent';
		removeDivArray[4] = '#errorDivParent';
		removeDivArray[5] = '#successDiv';
		var responseRenderDivNamesToHide = {}; 
		initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
		
	}
}


function showLoadCriteriaTagDiv(){
	if($(userGroupIDSelector).val() && $(userGroupIDSelector).val() != 0 ){
		$(loadCriteriaTagDiv).show();
	}else{
		$(loadCriteriaTagDiv).hide();
	}
}


function modifyInMemoryCriteriaTagsForSingleUpdate(criteria_id,action){
	
	 var requestUrl = 'addupdatecriteriaTags-modifyInMemoryCriteriaTagDetailsForSingleUpdates';
	 if (criteria_id  != '') {
		 requestUrl = requestUrl + '/' + criteria_id;
	  }
	 if (action  != '') {
		 requestUrl = requestUrl + '/' + action;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#show-criteriatag-list";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#show-unassigned-criteriatag-list';
	 removeDivArray[1] = '#show-assigned-criteriatag-list';
	 removeDivArray[2] = '#addupdatecriteriatagSuccessDivPanel';
	 removeDivArray[3] = '#errorDivParent';
	 removeDivArray[4] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
	
}


function modifyInMemoryCriteriaTagsForBulkUpdate(action){
	
	 var requestUrl = 'addupdatecriteriaTags-modifyInMemoryCriteriaTagDetails';
	 if (action  != '') {
		 requestUrl = requestUrl + '/' + action;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#show-criteriatag-list";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#show-unassigned-criteriatag-list';
	 removeDivArray[1] = '#show-assigned-criteriatag-list';
	 removeDivArray[2] = '#addupdatecriteriatagSuccessDivPanel';
	 removeDivArray[3] = '#errorDivParent';
	 removeDivArray[4] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
	
}


function saveCriteriaTagsChanges(){
	
	 var requestUrl = 'updateCriteriaTagDetails';
	 var requestMethod = 'post';
	 var responseRenderDivNam = "#addupdatecriteriatag-updatedata-result-status";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#show-unassigned-criteriatag-list';
	 removeDivArray[1] = '#show-assigned-criteriatag-list';
	 removeDivArray[2] = '#addupdatecriteriatagSuccessDivPanel';
	 removeDivArray[3] = '#errorDivParent';
	 removeDivArray[4] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	 
}

/* Scripts for User Group Criteria Screen Ends */

/* Scripts for Manage User Role Screen Starts */

function loadSelectedRoleDetails(roleID){
	 var requestUrl = 'loadprivilegedetailsforselectedroleid';
	 if (roleID  != '') {
		 requestUrl = requestUrl + '/' + roleID;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#manageroles-form-load-div";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#manageroles-form-data-div';
	 removeDivArray[1] = '#errorDivParent';
	 removeDivArray[2] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
	
}


function saveManageRoleChanges() {
	
	 var requestUrl = 'manageRoles'+ '/' + $('#selectedRoleID').val() +  '/' + $('#departmentID').val();
	 var requestMethod = 'post';
	 var responseRenderDivNam = "#managerole-updatedata-status";
	 var $form = $('#managerole-search-form');
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#save-cancel-buttonid';
	 removeDivArray[1] = '#manageroles-privilege-list-result'
	 removeDivArray[2] = '#errorDivParent';
	 removeDivArray[3] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);

}

/* Scripts for Manage User Role Screen Ends */

/* Scripts for Manage User Group Screen Starts */

function searchManageUserrGroupUsers(){
	var requestUrl = 'searchuser-manageeusergroup';
	var requestMethod = 'get';
	var requestParam = "?";
	var responseRenderDivNam = "#manageusergroup-appuser-list";
	var removeDivArray = {}; 
	removeDivArray[0] = '#manageusergroup-appuser-list-result';
	removeDivArray[1] = '#errorDivParent';
	removeDivArray[2] = '#successDiv';
	removeDivArray[3] = '#save-cancel-buttonid';
	removeDivArray[4] = '#noResultDivParent';
	var responseRenderDivNamesToHide = {}; 
	var paramName =  $("#name").val();
	if(paramName){
		paramName = "name="+paramName+'&';
		requestParam = requestParam + paramName;
	}
	var paramLogin =  $("#userName").val();
	if(paramLogin){
		paramLogin = "login="+paramLogin+'&';
		requestParam = requestParam + paramLogin;
	}
	var paramMobile = $("#mobileNo").val();
	if(paramMobile){
		paramMobile = "mobile_no="+paramMobile;
		requestParam = requestParam + paramMobile;
	}
	
	if(requestParam && requestParam.length > 1){
		requestUrl = requestUrl+requestParam;
	}

	
	 initiateAjaxRequest(requestUrl,requestMethod,null,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
}


function loadSelectedUserGroupDetails(groupID){
	 var requestUrl = 'loadusergroupdetails-forselected-groupid';
	 if (groupID  != '') {
		 requestUrl = requestUrl + '/' + groupID;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#manageusergroup-form-load-div";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#manageusergroup-form-data-div';
	 removeDivArray[1] = '#errorDivParent';
	 removeDivArray[2] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
}


function saveManageUserGroupChanges() {
	
	 var requestUrl = 'manageusergroups';
	 var requestMethod = 'post';
	 var responseRenderDivNam = "#manageusergroup-updatedata-status";
	 var $form = $('#manage-usergroup-search-form');
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#save-cancel-buttonid';
	 removeDivArray[1] = '#manageusergroup-appuser-list-result'
	 removeDivArray[2] = '#errorDivParent';
	 removeDivArray[3] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);

}

/* Scripts for Manage User Group Screen Ends */

/* Scripts for Manage Role Group Screen Starts */

function loadSelectedRoleGroupDetails(groupID){
	 var requestUrl = 'loadrolegroupdetails-forselected-groupid';
	 if (groupID  != '') {
		 requestUrl = requestUrl + '/' + groupID;
	  }
	 var requestMethod = 'get';
	 var responseRenderDivNam = "#managerolegroup-form-load-div";
	 var $form = null;
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#managerolegroup-form-data-div';
	 removeDivArray[1] = '#errorDivParent';
	 removeDivArray[2] = '#successDiv';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
}


function saveManageRoleGroupChanges(){
	 var requestUrl = 'managerolegroups';
	 var requestMethod = 'post';
	 var responseRenderDivNam = "#managerolegroup-updatedata-status";
	 var $form = $('#managerolegroup-search-form');
	 var removeDivArray = {}; 
	 removeDivArray[0] = '#save-cancel-buttonid';
	 removeDivArray[1] = '#errorDivParent';
	 removeDivArray[2] = '#successDiv';
	 removeDivArray[3] = '#managerolegroup-role-list-result';
	 var responseRenderDivNamesToHide = {}; 
	 initiateAjaxRequest(requestUrl,requestMethod,$form,removeDivArray,responseRenderDivNam,responseRenderDivNamesToHide);
}

/* Scripts for Manage Role Group Screen Ends */









