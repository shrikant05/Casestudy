package com.cg.loginregister.security.jwtmodel;

import java.util.List;

import org.springframework.http.ResponseCookie;

public class UserInfoResponse {
  private String id;
  private String username;
  private String email;
  private List<String> roles;
  private ResponseCookie jwtToken;

 

  public UserInfoResponse() {
	super();
	// TODO Auto-generated constructor stub
}

public UserInfoResponse(String id, String username, String email, List<String> roles,ResponseCookie jwtToken) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
    this.jwtToken = jwtToken;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

	public ResponseCookie getJwtToken() {
		return jwtToken;
	}

	public void ResponseCookie(ResponseCookie jwtToken) {
		this.jwtToken = jwtToken;
	}
}
