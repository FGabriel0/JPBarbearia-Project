package com.example.ApiJpBarbearia.controler.form;

import com.example.ApiJpBarbearia.enums.UserRoles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForm {
	
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@NotEmpty(message = "{campo.email.obrigatorio}")
	@Email(message = "{campo.email.invalido}")
	private String email;
	
	@NotEmpty(message = "{campo.telefone.obrigatorio}")
	private String telefone;
	
	@NotEmpty(message = "{campo.password.obrigatorio}")
	private String password;
	
	private UserRoles role;
}
