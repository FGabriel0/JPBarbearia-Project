package com.example.ApiJpBarbearia.controler.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicosForm {

	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@NotEmpty(message = "{campo.preco.obrigatorio}")
	private Double price;
	
	@NotEmpty(message = "{campo.tempo.obrigatorio}")
	private Integer time;
}
