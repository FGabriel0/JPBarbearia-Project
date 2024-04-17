package com.example.ApiJpBarbearia.controler.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthClienteForm {
	
	private String email;
	private String password;
}
