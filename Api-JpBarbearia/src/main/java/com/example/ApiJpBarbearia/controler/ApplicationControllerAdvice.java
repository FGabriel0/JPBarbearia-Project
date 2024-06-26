package com.example.ApiJpBarbearia.controler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ApiJpBarbearia.erros.ApiErros;
import com.example.ApiJpBarbearia.exception.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	 	@ExceptionHandler(RegraNegocioException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ApiErros handleRegraNegocioException(RegraNegocioException ex){
	        String mensagemErro = ex.getMessage();
	        return new ApiErros(mensagemErro);
	    }
	 	
	 	
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ApiErros handleMethodNotValidException( MethodArgumentNotValidException ex ){
	        List<String> errors = ex.getBindingResult().getAllErrors()
	                .stream()
	                .map(erro -> erro.getDefaultMessage())
	                .collect(Collectors.toList());
	        return new ApiErros(errors);
	    }
}
