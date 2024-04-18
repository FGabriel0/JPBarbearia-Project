package com.example.ApiJpBarbearia.entidy;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service", schema = "public")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Servicos {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "preco")
	private Double price;
	
	@Column(name = "tempo")
	private Integer time;
	
	@Column(name = "data_atualizacao")
	private LocalDateTime dataAtualizacao;
}
