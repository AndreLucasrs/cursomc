package com.andrelrs.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.andrelrs.cursomc.services.exceptions.ObjectNotFoundException;

//Classe auxiliar que ira interceptar as excessoes e ela obrigatoriamente dentro do framework ela tem que ter essa assinatura no metodo
@ControllerAdvice
public class ResourceExceptionHandler {
	
	//Isso indica que ele é um tratador de excessao desse tipo de excessao
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNoutFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError error =  new StandardError(HttpStatus.NOT_FOUND.value(), 	e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
