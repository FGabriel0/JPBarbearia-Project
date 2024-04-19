package com.example.ApiJpBarbearia.entidy;

import java.time.LocalDateTime;

import com.example.ApiJpBarbearia.enums.AgendamentoStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agendamento", schema = "public")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JoinColumn(name = "usuario_id")
	@ManyToOne
	private Usuario usuario_id;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "hora")
	private String hora;
	
	@JoinColumn(name = "service_id")
	@ManyToOne
	private Servicos servicos;
	
	@Column(name = "observacao")
	private String observacao;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private AgendamentoStatusEnum agendamentoStatus;
	
	@Column(name = "total")
	private Double total;
	
	@Column(name = "data_agendamento")
	private LocalDateTime data_agendamento;
	
	
	
}
