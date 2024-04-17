package com.example.ApiJpBarbearia.controler.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicosForm {

	private String nome;
	private Double price;
	private Integer time;
}
