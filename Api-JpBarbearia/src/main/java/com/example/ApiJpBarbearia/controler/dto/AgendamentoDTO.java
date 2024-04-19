package com.example.ApiJpBarbearia.controler.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {
	
	private Integer id;
	private String cliente;
	private String telefone;
	private String servico;
	private String data;
	private String hora;
	private String observacao;
	private Double total;
	private String data_agendamento;
}
