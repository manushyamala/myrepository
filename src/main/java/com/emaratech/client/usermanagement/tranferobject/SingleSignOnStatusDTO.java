package com.emaratech.client.usermanagement.tranferobject;

public class SingleSignOnStatusDTO {
	
	private String statusCode;
	private String statusDescription;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime
				* result
				+ ((statusDescription == null) ? 0 : statusDescription
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
		SingleSignOnStatusDTO other = (SingleSignOnStatusDTO) obj;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		if (statusDescription == null) {
			if (other.statusDescription != null)
				return false;
		} else if (!statusDescription.equals(other.statusDescription))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "SingleSignOnStatusDTO [statusCode=" + statusCode
				+ ", statusDescription=" + statusDescription + "]";
	}
	
	

}
