package com.example.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.admin.config.ResourceNotFoundException;
import com.example.admin.model.ErrorMessage;

import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class Advicer   {
	 @ExceptionHandler(ResourceNotFoundException.class)
	  public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED,"401");
	    
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
	  }
	
}
