package com.gshy.web.pc.entity;

import java.util.Date;

public class Employee {
	
	private long id;
	
	private String useName;
	
	private String phone;
	
	private String email;
	
	private String password;
	
	private Date addTime;
	
	private Date updateTime;
	
	private int valid; // -1 逻辑删除;默认为1

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUseName() {
		return useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


}
