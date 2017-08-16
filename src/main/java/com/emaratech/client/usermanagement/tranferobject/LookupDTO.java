package com.emaratech.client.usermanagement.tranferobject;

import java.io.Serializable;

public class LookupDTO implements Serializable {

	private static final long serialVersionUID = -1422302401001298828L;
	
	private long id;
	private String descEnglish;
	private String descArabic;
	
	public LookupDTO(){	
	}

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
	
	
	
}
