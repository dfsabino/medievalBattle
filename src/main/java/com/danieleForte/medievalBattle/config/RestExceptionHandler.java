package com.danieleForte.medievalBattle.config;

import com.danieleForte.medievalBattle.exception.ErrorMessage;
import com.danieleForte.medievalBattle.exception.InvalidInputException;
import com.danieleForte.medievalBattle.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

	public ResponseEntity< ErrorMessage > handle( Exception ex, HttpStatus statusCode,
												  String desciption ) {
		ErrorMessage message = new ErrorMessage( statusCode, LocalDateTime.now( ), ex.getMessage( ),
												 desciption );
		ex.printStackTrace( );
		return new ResponseEntity<>( message, message.getStatusCode( ) );
	}

	@ExceptionHandler( { InvalidInputException.class } )
	public ResponseEntity< ErrorMessage > invalidInputHandler( Exception ex, WebRequest request ) {
		return handle( ex, HttpStatus.BAD_REQUEST, request.getDescription( false ) );
	}

	@ExceptionHandler( ResourceNotFoundException.class )
	public ResponseEntity< ErrorMessage > resourceNotFoundHandler( Exception ex,
																   WebRequest request ) {
		return handle( ex, HttpStatus.NOT_FOUND, request.getDescription( false ) );
	}
}
