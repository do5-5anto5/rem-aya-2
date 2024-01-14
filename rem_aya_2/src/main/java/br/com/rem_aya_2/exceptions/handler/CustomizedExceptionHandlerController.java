package br.com.rem_aya_2.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.rem_aya_2.exceptions.ExceptionResponse;
import br.com.rem_aya_2.exceptions.ResourceNotFoundException;


@Controller
@ControllerAdvice
public class CustomizedExceptionHandlerController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(
			Exception exception, WebRequest webRequest){
		
		var exceptionResponse = new ExceptionResponse(
				new Date(),
				exception.getMessage(),
				webRequest.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(
			Exception exception, WebRequest webRequest){
		
		var exceptionResponse = new ExceptionResponse(
				new Date(),
				exception.getMessage(),
				webRequest.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
