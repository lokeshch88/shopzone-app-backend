package com.shopzone.app.exceptions;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shopzone.app.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private String errMsg;
	private String error;
	private int statusCode;

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>("You are not authorized to access this endpoint", HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class) //for userdto fields validation
    public ResponseEntity<ResponseDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("| "));

        ResponseDto errorResponse = new ResponseDto();
        errorResponse.setMessage("Validation failed");
        errorResponse.setError(errorMessage);
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleGeneralException(Exception ex) {
    	errMsg="Something went wrong";
    	
    	error=ex.getMessage();
    	
    	statusCode=HttpStatus.INTERNAL_SERVER_ERROR.value();
    	
    	ResponseDto errorResponse= new ResponseDto();
    	errorResponse.setMessage(errMsg);
    
    	errorResponse.setError(error);
    	errorResponse.setStatus(statusCode);
        return ResponseEntity.ok(errorResponse);
    }
    
    
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
}
