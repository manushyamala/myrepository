package com.emaratech.client.usermanagement.tranferobject;


import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

public class ApplicationMonitoringDTO {	
	
	private long totalStartedThreadCount;
	private long peakThreadCount;
	private long threadCount;
	private long percentageThreadUsage;
	private long currentHeapSize;
	private long maxHeapSize;
	private long committedHeapSize;
	private long initHeapSize;
	private long percentageHeapSizeUsed;
	private long totalSwapSpace;
	private long freeSwapSpace;
	private long percentageSwapSpaceUsed;
	private long totalPhysicalMemory;
	private long freePhysicalMemory;
	private long percentageMemoryUsed;
	private String serverName;
	private String libraryPath;
	private String bootClassPath;
	private String uptime;
	private String vmname;
	private JSONArray vmargumentsJson;
	private List<String> vmArgumentList;
	private long gcCount;
	private double gcExecutionTime;
	private long usedMemoryBeforeGC;
	private long usedMemoryAfterGC;
	private long maxMemoryBeforeGC;
	private long maxMemoryAfterGC;
	private long percentageMemoryUsedBeforeGC;
	private long percentageMemoryUsedAfterGC;
	
	

	public long getTotalStartedThreadCount() {
		return totalStartedThreadCount;
	}

	public void setTotalStartedThreadCount(long totalStartedThreadCount) {
		this.totalStartedThreadCount = totalStartedThreadCount;
	}

	public long getPeakThreadCount() {
		return peakThreadCount;
	}

	public void setPeakThreadCount(long peakThreadCount) {
		this.peakThreadCount = peakThreadCount;
	}

	public long getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(long threadCount) {
		this.threadCount = threadCount;
	}

	public long getPercentageThreadUsage() {	
		percentageThreadUsage = Math.round((threadCount * 100.0) / totalStartedThreadCount);
		return percentageThreadUsage;
	}

	public void setPercentageThreadUsage(long percentageThreadUsage) {
		this.percentageThreadUsage = percentageThreadUsage;
	}

	public long getCurrentHeapSize() {
		return currentHeapSize;
	}

	public void setCurrentHeapSize(long currentHeapSize) {
		this.currentHeapSize = currentHeapSize;
	}

	public long getMaxHeapSize() {
		return maxHeapSize;
	}

	public void setMaxHeapSize(long maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
	}

	public long getCommittedHeapSize() {
		return committedHeapSize;
	}

	public void setCommittedHeapSize(long committedHeapSize) {
		this.committedHeapSize = committedHeapSize;
	}

	public long getInitHeapSize() {
		return initHeapSize;
	}

	public void setInitHeapSize(long initHeapSize) {
		this.initHeapSize = initHeapSize;
	}

	public long getPercentageHeapSizeUsed() {
		percentageHeapSizeUsed = Math.round((currentHeapSize * 100.0) / maxHeapSize);
		return percentageHeapSizeUsed;
	}

	public void setPercentageHeapSizeUsed(long percentageHeapSizeUsed) {
		this.percentageHeapSizeUsed = percentageHeapSizeUsed;
	}	

	public long getTotalSwapSpace() {
		return totalSwapSpace;
	}

	public void setTotalSwapSpace(long totalSwapSpace) {
		this.totalSwapSpace = totalSwapSpace;
	}

	public long getFreeSwapSpace() {
		return freeSwapSpace;
	}

	public void setFreeSwapSpace(long freeSwapSpace) {
		this.freeSwapSpace = freeSwapSpace;
	}

	public long getPercentageSwapSpaceUsed() {
		percentageSwapSpaceUsed = Math.round(((totalSwapSpace - freeSwapSpace) * 100.0) / totalSwapSpace);
		return percentageSwapSpaceUsed;
	}

	public void setPercentageSwapSpaceUsed(long percentageSwapSpaceUsed) {
		this.percentageSwapSpaceUsed = percentageSwapSpaceUsed;
	}

	public long getTotalPhysicalMemory() {
		return totalPhysicalMemory;
	}

	public void setTotalPhysicalMemory(long totalPhysicalMemory) {
		this.totalPhysicalMemory = totalPhysicalMemory;
	}

	public long getFreePhysicalMemory() {
		return freePhysicalMemory;
	}

	public void setFreePhysicalMemory(long freePhysicalMemory) {
		this.freePhysicalMemory = freePhysicalMemory;
	}

	public long getPercentageMemoryUsed() {
		percentageMemoryUsed = Math.round(((totalPhysicalMemory - freePhysicalMemory) * 100.0) / totalPhysicalMemory);
		return percentageMemoryUsed;
	}

	public void setPercentageMemoryUsed(long percentageMemoryUsed) {
		this.percentageMemoryUsed = percentageMemoryUsed;
	}	

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getLibraryPath() {
		return libraryPath;
	}

	public void setLibraryPath(String classPath) {
		this.libraryPath = classPath;
	}

	public String getBootClassPath() {
		return bootClassPath;
	}

	public void setBootClassPath(String bootClassPath) {
		this.bootClassPath = bootClassPath;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getVmname() {
		return vmname;
	}

	public void setVmname(String vmname) {
		this.vmname = vmname;
	}
	
	public JSONArray getVmarguments() {
		return vmargumentsJson;
	}

	public void setVmarguments(JSONArray vmargumentsJson) {
		this.vmargumentsJson = vmargumentsJson;
	}	

	public List<String> getVmArgumentList() {
		vmArgumentList = new ArrayList<String>();
			if(null != vmargumentsJson){
				   for (int i=0;i<vmargumentsJson.size();i++){ 
					   vmArgumentList.add(vmargumentsJson.get(i).toString());
				   }
			}
		return vmArgumentList;
	}

	public void setVmArgumentList(List<String> vmArgumentList) {
		this.vmArgumentList = vmArgumentList;
	}

	public JSONArray getVmargumentsJson() {
		return vmargumentsJson;
	}

	public void setVmargumentsJson(JSONArray vmargumentsJson) {
		this.vmargumentsJson = vmargumentsJson;
	}

	public long getGcCount() {
		return gcCount;
	}

	public void setGcCount(long gcCount) {
		this.gcCount = gcCount;
	}

	public double getGcExecutionTime() {
		return gcExecutionTime;
	}

	public void setGcExecutionTime(double gcExecutionTime) {
		this.gcExecutionTime = gcExecutionTime;
	}

	public long getUsedMemoryBeforeGC() {
		return usedMemoryBeforeGC;
	}

	public void setUsedMemoryBeforeGC(long usedMemoryBeforeGC) {
		this.usedMemoryBeforeGC = usedMemoryBeforeGC;
	}

	public long getUsedMemoryAfterGC() {
		return usedMemoryAfterGC;
	}

	public void setUsedMemoryAfterGC(long usedMemoryAfterGC) {
		this.usedMemoryAfterGC = usedMemoryAfterGC;
	}

	public long getMaxMemoryBeforeGC() {
		return maxMemoryBeforeGC;
	}

	public void setMaxMemoryBeforeGC(long maxMemoryBeforeGC) {
		this.maxMemoryBeforeGC = maxMemoryBeforeGC;
	}

	public long getMaxMemoryAfterGC() {
		return maxMemoryAfterGC;
	}

	public void setMaxMemoryAfterGC(long maxMemoryAfterGC) {
		this.maxMemoryAfterGC = maxMemoryAfterGC;
	}

	public long getPercentageMemoryUsedBeforeGC() {
		percentageMemoryUsedBeforeGC = Math.round(((usedMemoryBeforeGC) * 100.0) / maxMemoryBeforeGC);
		return percentageMemoryUsedBeforeGC;
	}

	public void setPercentageMemoryUsedBeforeGC(long percentageMemoryUsedBeforeGC) {
		this.percentageMemoryUsedBeforeGC = percentageMemoryUsedBeforeGC;
	}

	public long getPercentageMemoryUsedAfterGC() {
		percentageMemoryUsedAfterGC = Math.round(((usedMemoryAfterGC) * 100.0) / maxMemoryAfterGC);
		return percentageMemoryUsedAfterGC;
	}

	public void setPercentageMemoryUsedAfterGC(long percentageMemoryUsedAfterGC) {
		this.percentageMemoryUsedAfterGC = percentageMemoryUsedAfterGC;
	}
	
	
}
