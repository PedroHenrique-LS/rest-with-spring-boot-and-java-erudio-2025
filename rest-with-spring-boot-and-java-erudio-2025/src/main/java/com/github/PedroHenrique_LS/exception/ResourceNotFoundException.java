package com.github.PedroHenrique_LS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	public ResourceNotFoundException(String text) {
		super(text);
	}

}
