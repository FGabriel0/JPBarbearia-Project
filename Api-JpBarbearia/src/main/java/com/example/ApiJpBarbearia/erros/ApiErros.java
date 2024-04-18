package com.example.ApiJpBarbearia.erros;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErros {
	
	 @Getter
	  	private List<String> errors;

	    public ApiErros(List<String> errors) {
	        this.errors = errors;
	    }

	    public ApiErros(String mensagemErro){
	        this.errors = Arrays.asList(mensagemErro);
	    }
	
}
