package com.example.ApiJpBarbearia.controler.form;

import com.example.ApiJpBarbearia.enums.UserRoles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteForm {
	
	private String nome;
	private String email;
	private String telefone;
	private String password;
	private UserRoles role;
}
