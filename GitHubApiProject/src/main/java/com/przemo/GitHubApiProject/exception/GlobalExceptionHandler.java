package com.przemo.GitHubApiProject.exception;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler({ LackOfRepositoriesExcpetion.class})
	 public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) 
	{
		HttpHeaders headers = new HttpHeaders();
		
            LackOfRepositoriesExcpetion unfe = (LackOfRepositoriesExcpetion) ex;

            return handleObjectNotFoundException(unfe, headers, request);
	}

	private ResponseEntity<ApiError> handleObjectNotFoundException(LackOfRepositoriesExcpetion ex, 
																HttpHeaders headers, WebRequest request) 
	{
		List<String> errors = Collections.singletonList(ex.getMessage());
		HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<ApiError>(new ApiError(errors,status),headers,status);
	}
	
	
	
}
