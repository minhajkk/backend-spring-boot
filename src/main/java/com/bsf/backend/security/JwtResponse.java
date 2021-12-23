package com.bsf.backend.security;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;

	public JwtResponse(String token) {
		this.token = token;
	}
}