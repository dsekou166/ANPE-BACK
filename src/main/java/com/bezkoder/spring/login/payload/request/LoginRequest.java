package com.bezkoder.spring.login.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
	//@NotBlank
	private String emailadmin;

	//@NotBlank
	private String passwordadmin;

	/*public String getEmail_admin() {
		return email_admin;
	}

	public void setEmail_admin(String email_admin) {
		this.email_admin = email_admin;
	}

	public String getPassword_admin() {
		return password_admin;
	}

	public void setPassword_admin(String password) {
		this.password_admin = password_admin;
	}*/
}
