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
	private Key key;
	
	private String fuid;
	private String userName;
    private String userSex;
    private String birthday;
    private String urlImageProfile;
    private String locale;
    
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
}
