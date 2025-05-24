package com.booking.app.dto;

public class ResponseDto {

    private String message;
    private String error;
    private int status;
    private String token;
    private String username;
    
    private String data;

    public ResponseDto(String message, String error, int status) {
        this.message = message;
        this.error = error;
        this.status = status;
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
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
