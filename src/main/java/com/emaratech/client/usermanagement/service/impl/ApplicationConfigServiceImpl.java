package com.emaratech.client.usermanagement.service.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.management.MalformedObjectNameException;

import org.jolokia.client.J4pClient;
import org.jolokia.client.exception.J4pException;
import org.jolokia.client.request.J4pExecRequest;
import org.jolokia.client.request.J4pExecResponse;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pReadResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emaratech.client.usermanagement.service.ApplicationConfigService;
import com.emaratech.client.usermanagement.tranferobject.ApplicationMonitoringDTO;

@Service
public class ApplicationConfigServiceImpl implements ApplicationConfigService{
	
	private static final String JOLOKIA_SERVER_URL = "http://localhost:9001/jolokia";
	
	private static final String THREAD_URL_PATH_SUFFIX = "java.lang:type=Threading";
	private static final String TOTAL_STARTED_THREAD_COUNT = "TotalStartedThreadCount";	
	private static final String PEAK_THREAD_COUNT = "PeakThreadCount";
	private static final String THREAD_COUNT = "ThreadCount";
	
	private static final String HEAP_SIZE_URL_PATH_SUFFIX = "java.lang:type=Memory";
	private static final String HEAP_SIZE_ATTRIBUTE_NAME = "HeapMemoryUsage";
	private static final String HEAP_SIZE_CURRENT = "used";
	private static final String HEAP_SIZE_MAX = "max";
	private static final String HEAP_SIZE_COMMITTED = "committed";
	private static final String HEAP_SIZE_INIT = "init";
	
	private static final String OPERATING_SYSTEM_URL_PATH_SUFFIX = "java.lang:type=OperatingSystem";
	private static final String TOTAL_SWAP_SPACE_SIZE = "TotalSwapSpaceSize";
	private static final String FREE_SWAP_SPACE_SIZE = "FreeSwapSpaceSize";
	private static final String TOTAL_PHYSICAL_MEMORY_SIZE = "TotalPhysicalMemorySize";
	private static final String FREE_PHYSICAL_MEMORY_SIZE = "FreePhysicalMemorySize";
	
	
	private static final String RUNTIME_URL_PATH_SUFFIX = "java.lang:type=Runtime";
	private static final String SERVER_NAME = "Name";
	private static final String LIBRARY_PATH = "LibraryPath";
	private static final String VM_ARGUEMENTS = "InputArguments";
	private static final String BOOT_CLASS_PATH = "BootClassPath";
	private static final String UPTIME = "Uptime";
	private static final String VM_NAME = "VmName";
	
	private static final String LOGGER_URL_PATH_SUFFIX = "com.emaratech:name=LogManager";
	private static final String LOGGER_LOG_LEVEL = "LogLevel";
	private static final String DEBUG_LEVEL_ATTRIBUTE = "setLogLevel";
	
	private static final String GARBAGE_COLLECTOR_PATH_SUFFIX = "java.lang:type=GarbageCollector,name=PS MarkSweep";
	private static final String LAST_GC_INFO = "LastGcInfo";
	private static final String GC_COLLECTION_COUNT = "CollectionCount";
	private static final String GC_COLLECTION_TIME = "CollectionTime";
	private static final String MEMORY_USAGE_AFTER_GC = "memoryUsageAfterGc";
	private static final String MEMORY_USAGE_BEFORE_GC = "memoryUsageBeforeGc";
	private static final String GC_MEMORY_MAX = "max";
	private static final String GC_MEMORY_USED = "used";
	private static final String PS_OLD_SPACE = "PS Old Gen";
	
	
	
	
	

	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponseEntity<ApplicationMonitoringDTO> retrieveApplicationMonitoringDetails() {
		
		Map<String,Object> valueMap = null;
        ApplicationMonitoringDTO applicationMonitoringDTO = new ApplicationMonitoringDTO();
        J4pReadResponse response = null;
        J4pClient j4pClient = new J4pClient(JOLOKIA_SERVER_URL);
        try{        	
        	response = initiateJolokiaReadRequest(THREAD_URL_PATH_SUFFIX,j4pClient,null);   
        	if(null != response && null != response.getValue()){
        		valueMap = response.getValue();
        		applicationMonitoringDTO.setThreadCount((long) valueMap.get(THREAD_COUNT));
        		applicationMonitoringDTO.setTotalStartedThreadCount((long) valueMap.get(TOTAL_STARTED_THREAD_COUNT));
        		applicationMonitoringDTO.setPeakThreadCount((long) valueMap.get(PEAK_THREAD_COUNT));
        	}        	
        	response = initiateJolokiaReadRequest(HEAP_SIZE_URL_PATH_SUFFIX,j4pClient,HEAP_SIZE_ATTRIBUTE_NAME);           	
           	if(null != response && null != response.getValue()){
           		valueMap = response.getValue();
        		applicationMonitoringDTO.setInitHeapSize((long) valueMap.get(HEAP_SIZE_INIT));
        		applicationMonitoringDTO.setCommittedHeapSize((long) valueMap.get(HEAP_SIZE_COMMITTED));
        		applicationMonitoringDTO.setMaxHeapSize((long) valueMap.get(HEAP_SIZE_MAX));
        		applicationMonitoringDTO.setCurrentHeapSize((long) valueMap.get(HEAP_SIZE_CURRENT));
        	}        	
        	response = initiateJolokiaReadRequest(OPERATING_SYSTEM_URL_PATH_SUFFIX,j4pClient,null);       
        	if(null != response && null != response.getValue()){
            	valueMap = response.getValue();
        		applicationMonitoringDTO.setTotalSwapSpace((long) valueMap.get(TOTAL_SWAP_SPACE_SIZE));
        		applicationMonitoringDTO.setFreeSwapSpace((long) valueMap.get(FREE_SWAP_SPACE_SIZE));
        		applicationMonitoringDTO.setTotalPhysicalMemory((long) valueMap.get(TOTAL_PHYSICAL_MEMORY_SIZE));
        		applicationMonitoringDTO.setFreePhysicalMemory((long) valueMap.get(FREE_PHYSICAL_MEMORY_SIZE));
        		        		
        	}
        	response = initiateJolokiaReadRequest(RUNTIME_URL_PATH_SUFFIX,j4pClient,null);      
        	if(null != response && null != response.getValue()){
            	valueMap = response.getValue();
        		applicationMonitoringDTO.setServerName((String) valueMap.get(SERVER_NAME));
        		applicationMonitoringDTO.setLibraryPath((String) valueMap.get(LIBRARY_PATH));
        		applicationMonitoringDTO.setVmarguments(((JSONArray) valueMap.get(VM_ARGUEMENTS)));
        		applicationMonitoringDTO.setBootClassPath((String) valueMap.get(BOOT_CLASS_PATH));
        		applicationMonitoringDTO.setUptime((generateDateFormat((long) valueMap.get(UPTIME))));
        		applicationMonitoringDTO.setVmname((String) valueMap.get(VM_NAME));
        		        		
        	}        
        	response = initiateJolokiaReadRequest(GARBAGE_COLLECTOR_PATH_SUFFIX,j4pClient,null);      
        	if(null != response && null != response.getValue()){
        		valueMap = response.getValue();
        		applicationMonitoringDTO.setGcCount((long)valueMap.get(GC_COLLECTION_COUNT));
        		applicationMonitoringDTO.setGcExecutionTime(getTimeInSeconds((long)valueMap.get(GC_COLLECTION_TIME)));        		
        	}
        	response = initiateJolokiaReadRequest(GARBAGE_COLLECTOR_PATH_SUFFIX,j4pClient,LAST_GC_INFO);      
        	if(null != response && null != response.getValue()){
        		valueMap = response.getValue();  	
        		JSONObject memoryUsageBeforeGcObject = null;
        		JSONObject edenSpaceObject = null;
        		memoryUsageBeforeGcObject = (JSONObject)valueMap.get(MEMORY_USAGE_BEFORE_GC);
        		if(null != memoryUsageBeforeGcObject){
        			edenSpaceObject = (JSONObject)memoryUsageBeforeGcObject.get(PS_OLD_SPACE);
        			if(null != edenSpaceObject){
        				applicationMonitoringDTO.setUsedMemoryBeforeGC((long)edenSpaceObject.get(GC_MEMORY_USED));
        				applicationMonitoringDTO.setMaxMemoryBeforeGC((long)edenSpaceObject.get(GC_MEMORY_MAX));
        			}
        		}
        		memoryUsageBeforeGcObject = (JSONObject)valueMap.get(MEMORY_USAGE_AFTER_GC);
        		if(null != memoryUsageBeforeGcObject){
        			edenSpaceObject = (JSONObject)memoryUsageBeforeGcObject.get(PS_OLD_SPACE);
        			if(null != edenSpaceObject){
        				applicationMonitoringDTO.setUsedMemoryAfterGC((long)edenSpaceObject.get(GC_MEMORY_USED));
        				applicationMonitoringDTO.setMaxMemoryAfterGC((long)edenSpaceObject.get(GC_MEMORY_MAX));
        			}
        		}        		       
        		
        	}
        	        	
        }
        catch ( J4pException | MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return new ResponseEntity<ApplicationMonitoringDTO>(applicationMonitoringDTO,HttpStatus.OK);
		
	}
	
	@Override
	public ResponseEntity<String> retrieveCurrentLoggingLevel(){
		//"http://localhost:9001/jolokia/read/com.emaratech:name=LogManager"
		String loggerLevel = null;
		Map<String,Object> valueMap = null;
        J4pReadResponse response = null;
        J4pClient j4pClient = new J4pClient(JOLOKIA_SERVER_URL);
        try{     
        	response = initiateJolokiaReadRequest(LOGGER_URL_PATH_SUFFIX,j4pClient,null);   
        	if(null != response && null != response.getValue()){
            	valueMap = response.getValue();
            	loggerLevel =(String) valueMap.get(LOGGER_LOG_LEVEL);
        	}
        }catch ( J4pException | MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<String>(loggerLevel,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> updateLoggingLevel(String logLevel){
        J4pClient j4pClient = new J4pClient(JOLOKIA_SERVER_URL);
        try{     
        	initiateJolokiaWriteRequest(LOGGER_URL_PATH_SUFFIX,j4pClient,DEBUG_LEVEL_ATTRIBUTE,logLevel);   
        }catch ( J4pException | MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return new ResponseEntity<String>(logLevel,HttpStatus.OK);
	}

	private J4pReadResponse initiateJolokiaReadRequest(String urlPathSuffix, J4pClient j4pClient,String attribute) throws MalformedObjectNameException,J4pException{
		J4pReadResponse resp = null;
		if(null != attribute && attribute.length() > 0){
			J4pReadRequest request = new J4pReadRequest(urlPathSuffix,attribute);
			resp = j4pClient.execute(request);	
		}else{
			resp = j4pClient.execute(new J4pReadRequest(urlPathSuffix));	
		}		
		return resp;
		
	}
	
	
	private J4pExecResponse initiateJolokiaWriteRequest(String urlPathSuffix,J4pClient j4pClient,String attribute,String value)throws MalformedObjectNameException,J4pException{
		J4pExecResponse resp = null;
		J4pExecRequest request = new J4pExecRequest(urlPathSuffix,attribute,value);
		resp = j4pClient.execute(request);	
		return resp;		
	}
	
	
	
	private String generateDateFormat(long milliseconds){
		  int hrs = (int) TimeUnit.MILLISECONDS.toHours(milliseconds) % 24;
		  int min = (int) TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60;
		  int sec = (int) TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60;
		  return String.format("%02d:%02d:%02d", hrs, min, sec);
	}
	
	
	private double getTimeInSeconds(long milliseconds){
		return (double)milliseconds/1000.0;
	}
	
	
}
