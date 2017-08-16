package com.emaratech.client.usermanagement.tranferobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadStatsDTO {
	
	private String totalStartedThreadCount;
	private String peakThreadCount;
	private String threadCount;
	private String percentageThreadUsage;
	
	public String getTotalStartedThreadCount() {
		return totalStartedThreadCount;
	}
	public void setTotalStartedThreadCount(String totalStartedThreadCount) {
		this.totalStartedThreadCount = totalStartedThreadCount;
	}
	public String getPeakThreadCount() {
		return peakThreadCount;
	}
	public void setPeakThreadCount(String peakThreadCount) {
		this.peakThreadCount = peakThreadCount;
	}
	public String getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(String threadCount) {
		this.threadCount = threadCount;
	}
	public String getPercentageThreadUsage() {
		float percentThreadUsageCount = 0.0f;
		if(null != this.totalStartedThreadCount && null != threadCount ){
			float threadStartCount_long = Float.valueOf(totalStartedThreadCount);
			float threadCurrentCount_long = Float.valueOf(threadCount);
			percentThreadUsageCount = Math.round((threadCurrentCount_long * 100.0f) / threadStartCount_long);
		}		
		percentageThreadUsage = String.valueOf(percentThreadUsageCount);
		return percentageThreadUsage;
	}
	public void setPercentageThreadUsage(String percentageThreadUsage) {
		this.percentageThreadUsage = percentageThreadUsage;
	}
	

}
