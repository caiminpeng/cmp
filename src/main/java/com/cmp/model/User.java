package com.cmp.model;

import java.util.Date;

public class User {
	
	private long id;
	private String user_name;
	private String login_name;
	private String login_pass;
	private Integer sex;
	private String tele_phone;
	private String mail;
	private Date create_time;
	private String province_name;
	private String city_name;
	private String county_name;
	private String job_num;
	private String county_code;
	private String city_code;
	private String province_code;
	private String role_code;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getLogin_pass() {
		return login_pass;
	}
	public void setLogin_pass(String login_pass) {
		this.login_pass = login_pass;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getTele_phone() {
		return tele_phone;
	}
	public void setTele_phone(String tele_phone) {
		this.tele_phone = tele_phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCounty_name() {
		return county_name;
	}
	public void setCounty_name(String county_name) {
		this.county_name = county_name;
	}
	public String getJob_num() {
		return job_num;
	}
	public void setJob_num(String job_num) {
		this.job_num = job_num;
	}
	public String getCounty_code() {
		return county_code;
	}
	public void setCounty_code(String county_code) {
		this.county_code = county_code;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	
}