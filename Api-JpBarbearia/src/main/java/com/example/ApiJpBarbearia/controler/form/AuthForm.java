package com.example.ApiJpBarbearia.controler.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthForm {
	
	private String login;
	private String password;
}
