package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class UserSystemInfoDTO implements Serializable{
	
	private static final long serialVersionUID = -2285342174024879795L;
	
	private long id;
    private String macAddress;
    private String ipAddress;
    private String machineName;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result
				+ ((macAddress == null) ? 0 : macAddress.hashCode());
		result = prime * result
				+ ((machineName == null) ? 0 : machineName.hashCode());
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
		UserSystemInfoDTO other = (UserSystemInfoDTO) obj;
		if (id != other.id)
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (macAddress == null) {
			if (other.macAddress != null)
				return false;
		} else if (!macAddress.equals(other.macAddress))
			return false;
		if (machineName == null) {
			if (other.machineName != null)
				return false;
		} else if (!machineName.equals(other.machineName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UserSystemInfoDTO [id=" + id + ", macAddress=" + macAddress
				+ ", ipAddress=" + ipAddress + ", machineName=" + machineName
				+ "]";
	}
	
 
}
