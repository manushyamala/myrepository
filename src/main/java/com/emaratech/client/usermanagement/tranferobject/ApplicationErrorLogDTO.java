package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;
import java.util.Date;

public class ApplicationErrorLogDTO implements Serializable {
	

	private static final long serialVersionUID = -8082290198744148805L;
	
	private long applicationErrorLogId;
	private String buildNo;
	private String businessKey;
	private Date createdDate;
	private String className;
	private String clientIP;
	private String clientRequestType;
	private String errorCode;
	private String exceptionSummary;
	private String exceptionTrace;
	private String inputData;
	private String lineNo;
	private String loggerLevel;
	private String methodName;
	private Date requestReceivedTime;
	private String serverNodeName;
	private String sessionID;
	private String svnRevisionNo;
	private String threadId;
	private String userId;
	private String webserviceRequestXML;
	private String tokenId;
	private String requestId;
  
	public long getApplicationErrorLogId() {
		return applicationErrorLogId;
	}
	public void setApplicationErrorLogId(long applicationErrorLogId) {
		this.applicationErrorLogId = applicationErrorLogId;
	}
	public String getBuildNo() {
		return buildNo;
	}
	public void setBuildNo(String buildNo) {
		this.buildNo = buildNo;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getClientRequestType() {
		return clientRequestType;
	}
	public void setClientRequestType(String clientRequestType) {
		this.clientRequestType = clientRequestType;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getExceptionSummary() {
		return exceptionSummary;
	}
	public void setExceptionSummary(String exceptionSummary) {
		this.exceptionSummary = exceptionSummary;
	}
	public String getExceptionTrace() {
		return exceptionTrace;
	}
	public void setExceptionTrace(String exceptionTrace) {
		this.exceptionTrace = exceptionTrace;
	}
	public String getInputData() {
		return inputData;
	}
	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getLoggerLevel() {
		return loggerLevel;
	}
	public void setLoggerLevel(String loggerLevel) {
		this.loggerLevel = loggerLevel;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Date getRequestReceivedTime() {
		return requestReceivedTime;
	}
	public void setRequestReceivedTime(Date requestReceivedTime) {
		this.requestReceivedTime = requestReceivedTime;
	}
	public String getServerNodeName() {
		return serverNodeName;
	}
	public void setServerNodeName(String serverNodeName) {
		this.serverNodeName = serverNodeName;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getSvnRevisionNo() {
		return svnRevisionNo;
	}
	public void setSvnRevisionNo(String svnRevisionNo) {
		this.svnRevisionNo = svnRevisionNo;
	}
	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWebserviceRequestXML() {
		return webserviceRequestXML;
	}
	public void setWebserviceRequestXML(String webserviceRequestXML) {
		this.webserviceRequestXML = webserviceRequestXML;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (applicationErrorLogId ^ (applicationErrorLogId >>> 32));
		result = prime * result + ((buildNo == null) ? 0 : buildNo.hashCode());
		result = prime * result
				+ ((businessKey == null) ? 0 : businessKey.hashCode());
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result
				+ ((clientIP == null) ? 0 : clientIP.hashCode());
		result = prime
				* result
				+ ((clientRequestType == null) ? 0 : clientRequestType
						.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime
				* result
				+ ((exceptionSummary == null) ? 0 : exceptionSummary.hashCode());
		result = prime * result
				+ ((exceptionTrace == null) ? 0 : exceptionTrace.hashCode());
		result = prime * result
				+ ((inputData == null) ? 0 : inputData.hashCode());
		result = prime * result + ((lineNo == null) ? 0 : lineNo.hashCode());
		result = prime * result
				+ ((loggerLevel == null) ? 0 : loggerLevel.hashCode());
		result = prime * result
				+ ((methodName == null) ? 0 : methodName.hashCode());
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime
				* result
				+ ((requestReceivedTime == null) ? 0 : requestReceivedTime
						.hashCode());
		result = prime * result
				+ ((serverNodeName == null) ? 0 : serverNodeName.hashCode());
		result = prime * result
				+ ((sessionID == null) ? 0 : sessionID.hashCode());
		result = prime * result
				+ ((svnRevisionNo == null) ? 0 : svnRevisionNo.hashCode());
		result = prime * result
				+ ((threadId == null) ? 0 : threadId.hashCode());
		result = prime * result + ((tokenId == null) ? 0 : tokenId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime
				* result
				+ ((webserviceRequestXML == null) ? 0 : webserviceRequestXML
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationErrorLogDTO other = (ApplicationErrorLogDTO) obj;
		if (applicationErrorLogId != other.applicationErrorLogId)
			return false;
		if (buildNo == null) {
			if (other.buildNo != null)
				return false;
		} else if (!buildNo.equals(other.buildNo))
			return false;
		if (businessKey == null) {
			if (other.businessKey != null)
				return false;
		} else if (!businessKey.equals(other.businessKey))
			return false;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (clientIP == null) {
			if (other.clientIP != null)
				return false;
		} else if (!clientIP.equals(other.clientIP))
			return false;
		if (clientRequestType == null) {
			if (other.clientRequestType != null)
				return false;
		} else if (!clientRequestType.equals(other.clientRequestType))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (exceptionSummary == null) {
			if (other.exceptionSummary != null)
				return false;
		} else if (!exceptionSummary.equals(other.exceptionSummary))
			return false;
		if (exceptionTrace == null) {
			if (other.exceptionTrace != null)
				return false;
		} else if (!exceptionTrace.equals(other.exceptionTrace))
			return false;
		if (inputData == null) {
			if (other.inputData != null)
				return false;
		} else if (!inputData.equals(other.inputData))
			return false;
		if (lineNo == null) {
			if (other.lineNo != null)
				return false;
		} else if (!lineNo.equals(other.lineNo))
			return false;
		if (loggerLevel == null) {
			if (other.loggerLevel != null)
				return false;
		} else if (!loggerLevel.equals(other.loggerLevel))
			return false;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (requestReceivedTime == null) {
			if (other.requestReceivedTime != null)
				return false;
		} else if (!requestReceivedTime.equals(other.requestReceivedTime))
			return false;
		if (serverNodeName == null) {
			if (other.serverNodeName != null)
				return false;
		} else if (!serverNodeName.equals(other.serverNodeName))
			return false;
		if (sessionID == null) {
			if (other.sessionID != null)
				return false;
		} else if (!sessionID.equals(other.sessionID))
			return false;
		if (svnRevisionNo == null) {
			if (other.svnRevisionNo != null)
				return false;
		} else if (!svnRevisionNo.equals(other.svnRevisionNo))
			return false;
		if (threadId == null) {
			if (other.threadId != null)
				return false;
		} else if (!threadId.equals(other.threadId))
			return false;
		if (tokenId == null) {
			if (other.tokenId != null)
				return false;
		} else if (!tokenId.equals(other.tokenId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (webserviceRequestXML == null) {
			if (other.webserviceRequestXML != null)
				return false;
		} else if (!webserviceRequestXML.equals(other.webserviceRequestXML))
			return false;
		return true;
	 }
	
	@Override
	public String toString() {
		return "ApplicationErrorLogDTO [applicationErrorLogId="
				+ applicationErrorLogId + ", buildNo=" + buildNo
				+ ", businessKey=" + businessKey + ", createdDate="
				+ createdDate + ", className=" + className + ", clientIP="
				+ clientIP + ", clientRequestType=" + clientRequestType
				+ ", errorCode=" + errorCode + ", exceptionSummary="
				+ exceptionSummary + ", exceptionTrace=" + exceptionTrace
				+ ", inputData=" + inputData + ", lineNo=" + lineNo
				+ ", loggerLevel=" + loggerLevel + ", methodName=" + methodName
				+ ", requestReceivedTime=" + requestReceivedTime
				+ ", serverNodeName=" + serverNodeName + ", sessionID="
				+ sessionID + ", svnRevisionNo=" + svnRevisionNo
				+ ", threadId=" + threadId + ", userId=" + userId
				+ ", webserviceRequestXML=" + webserviceRequestXML
				+ ", tokenId=" + tokenId + ", requestId=" + requestId + "]";
	}
	
	 
	
	

}
