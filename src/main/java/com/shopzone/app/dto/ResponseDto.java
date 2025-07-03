package com.shopzone.app.dto;

import java.util.List;

public class ResponseDto<T> {

    private String message;
    private String error;
    private int responseCd;
    private String token;
    private String username;
    
    private List<T> result;

    public ResponseDto(String message, String error, int status) {
        this.message = message;
        this.error = error;
        this.responseCd = status;
    }

	public ResponseDto() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getStatus() {
		return responseCd;
	}

	public void setStatus(int responseCd) {
		this.responseCd = responseCd;
	}

	public List<T> getResult() {
	    return result;
	}

	public void setResult(List<T> result) {
	    this.result = result;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    
 }
