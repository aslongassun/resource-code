package com.vmcop.simplethird.findlover;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ProfileInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Record Key
	private Key key;
	// Facebook Id
	private String fuid;
	// Ten user
	private String userName;
    // Gioi tinh
	private String userSex;
    // Sinh nhat
    private String birthday;
    // Url cua user profile
    private String urlImageProfile;
    // Quoc gia
    private String locale;
    // Luu nam sinh dang so dung cho query
    private Integer bornYear;
    // User nay co duoc nho upload hay thong qua login
    private Boolean isFromUpload;
    
    public Key getKey() {
        return key;
    }
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUrlImageProfile() {
		return urlImageProfile;
	}
	public void setUrlImageProfile(String urlImageProfile) {
		this.urlImageProfile = urlImageProfile;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getFuid() {
		return fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	public Integer getBornYear() {
		return bornYear;
	}

	public void setBornYear(Integer bornYear) {
		this.bornYear = bornYear;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Boolean getIsFromUpload() {
		return isFromUpload;
	}

	public void setIsFromUpload(Boolean isFromUpload) {
		this.isFromUpload = isFromUpload;
	}
}
