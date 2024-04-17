package com.example.ApiJpBarbearia.controler.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicosDTO {
	
	private Integer id;
	
	private String nome;
	
	private Double price;
	
	private Integer time;
	
}
