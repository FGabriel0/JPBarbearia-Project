package com.example.ApiJpBarbearia.controler.form;

import com.example.ApiJpBarbearia.enums.UserRoles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForm {
	
	private String nome;
	private String email;
	private String password;
	private UserRoles role;
}
