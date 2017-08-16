package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class EmiratesDepartmentDTO implements Serializable{
	
	private static final long serialVersionUID = -4023360146999221290L;
	private long id;
	private String descEnglish;
	private String descArabic;
	private boolean readOnly;
	private  boolean archived;
	  
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescEnglish() {
		return descEnglish;
	}
	public void setDescEnglish(String descEnglish) {
		this.descEnglish = descEnglish;
	}
	public String getDescArabic() {
		return descArabic;
	}
	public void setDescArabic(String descArabic) {
		this.descArabic = descArabic;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public boolean isArchived() {
		return archived;
	}
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (archived ? 1231 : 1237);
		result = prime * result
				+ ((descArabic == null) ? 0 : descArabic.hashCode());
		result = prime * result
				+ ((descEnglish == null) ? 0 : descEnglish.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (readOnly ? 1231 : 1237);
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
		EmiratesDepartmentDTO other = (EmiratesDepartmentDTO) obj;
		if (archived != other.archived)
			return false;
		if (descArabic == null) {
			if (other.descArabic != null)
				return false;
		} else if (!descArabic.equals(other.descArabic))
			return false;
		if (descEnglish == null) {
			if (other.descEnglish != null)
				return false;
		} else if (!descEnglish.equals(other.descEnglish))
			return false;
		if (id != other.id)
			return false;
		if (readOnly != other.readOnly)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "EmiratesDepartmentDTO [id=" + id + ", descEnglish="
				+ descEnglish + ", descArabic=" + descArabic + ", readOnly="
				+ readOnly + ", archived=" + archived + "]";
	}
	
	
	

}
