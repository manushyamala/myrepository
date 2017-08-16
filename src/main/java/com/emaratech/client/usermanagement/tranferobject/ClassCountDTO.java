package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassCountDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3035243622695428767L;
	
	private String loadedClassCount;
	private String unloadedClassCount;
	private String totalLoadedClassCount;
	private String percentageCount;
	
	public String getLoadedClassCount() {
		return loadedClassCount;
	}
	public void setLoadedClassCount(String loadedClassCount) {
		this.loadedClassCount = loadedClassCount;
	}
	public String getUnloadedClassCount() {
		return unloadedClassCount;
	}
	public void setUnloadedClassCount(String unloadedClassCount) {
		this.unloadedClassCount = unloadedClassCount;
	}
	public String getTotalLoadedClassCount() {
		return totalLoadedClassCount;
	}
	public void setTotalLoadedClassCount(String totalLoadedClassCount) {
		this.totalLoadedClassCount = totalLoadedClassCount;
	}
	public String getPercentageCount() {
		float percentLoadedCount = 0.0f;
		if(null != this.loadedClassCount && null != unloadedClassCount && null != totalLoadedClassCount){
			float loadedClassCount_long = Float.valueOf(loadedClassCount);
			float totalLoadedClassCount_long = Float.valueOf(totalLoadedClassCount);
			percentLoadedCount = (loadedClassCount_long * 100.0f) / totalLoadedClassCount_long;
		}		
		 percentageCount = String.valueOf(percentLoadedCount);
		 return percentageCount;
	}
	public void setPercentageCount(String percentageCount) {
		this.percentageCount = percentageCount;
	}

}
