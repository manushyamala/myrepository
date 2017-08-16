package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



public class CriteriaTagWrapperDTO implements Serializable {

	private static final long serialVersionUID = -2795934133308186484L;
		
	private List<CriteriaTagDTO> assignedCriteriaTagDTOList;
	
	private List<CriteriaTagDTO> unassignedCriteriaTagDTOList;
	
	private Map<String,CriteriaTagDTO> criteriaTagDTOMap;
	
	public CriteriaTagWrapperDTO(){
		
	}

	public List<CriteriaTagDTO> getAssignedCriteriaTagDTOList() {
		return assignedCriteriaTagDTOList;
	}

	public void setAssignedCriteriaTagDTOList(
			List<CriteriaTagDTO> assignedCriteriaTagDTOList) {
		this.assignedCriteriaTagDTOList = assignedCriteriaTagDTOList;
	}

	public List<CriteriaTagDTO> getUnassignedCriteriaTagDTOList() {
		return unassignedCriteriaTagDTOList;
	}

	public void setUnassignedCriteriaTagDTOList(
			List<CriteriaTagDTO> unassignedCriteriaTagDTOList) {
		this.unassignedCriteriaTagDTOList = unassignedCriteriaTagDTOList;
	}

	public Map<String, CriteriaTagDTO> getCriteriaTagDTOMap() {
		return criteriaTagDTOMap;
	}

	public void setCriteriaTagDTOMap(Map<String, CriteriaTagDTO> criteriaTagDTOMap) {
		this.criteriaTagDTOMap = criteriaTagDTOMap;
	}


	
	
			
}
