package com.example.ApiJpBarbearia.controler.form;

import com.example.ApiJpBarbearia.entidy.Servicos;
import com.example.ApiJpBarbearia.entidy.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoForm {

	private Integer usuario_id;
	private String data;
	private String hora;
	private Integer servico_id;
	private String observacao;
}
