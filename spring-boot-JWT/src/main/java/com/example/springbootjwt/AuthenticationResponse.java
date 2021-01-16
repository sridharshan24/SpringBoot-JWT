package com.example.springbootjwt;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationResponse {
	
	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	

}
