package com.example.ApiJpBarbearia.controler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiJpBarbearia.Impl.AgendamentoIMPL;
import com.example.ApiJpBarbearia.controler.dto.AgendamentoDTO;
import com.example.ApiJpBarbearia.controler.form.AgendamentoForm;
import com.example.ApiJpBarbearia.entidy.Agendamento;
import com.example.ApiJpBarbearia.entidy.Servicos;
import com.example.ApiJpBarbearia.entidy.Usuario;
import com.example.ApiJpBarbearia.enums.ResponseStatusEnum;
import com.example.ApiJpBarbearia.exception.RegraNegocioException;
import com.example.ApiJpBarbearia.reponse.Response;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoControler {
	
	@Autowired
	private AgendamentoIMPL service;
	
	
	@PostMapping("/salvar")
	public ResponseEntity<Response<Agendamento>> salvar(@RequestBody @Valid AgendamentoForm form){
		Response<Agendamento> response = new Response<>();
		Agendamento newServico = service.salvaAgendamento(form);
		try {
			if(newServico != null) {
				response.setStatus(ResponseStatusEnum.SUCCESS);
				response.setData(newServico);
				return ResponseEntity.ok(response);
			}
			else {
				response.setStatus(ResponseStatusEnum.ERROR);
				response.setMessage("Agendamento não Encontrado.");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);			}
	
	}
	
	@GetMapping("/{id}")
	public AgendamentoDTO buscarPorId(@PathVariable Integer id ) {
		return service.buscarPorId(id)
				.map(p -> converter(p)
					).orElseThrow(() -> new RegraNegocioException("Agendamento não encontrado"));
	}
	
	
	private AgendamentoDTO converter(Agendamento p) {
		return AgendamentoDTO.builder()
			.id(p.getId())
			.cliente(p.getUsuario_id().getLogin())
			.telefone(p.getUsuario_id().getTelefone())
			.servico(p.getServicos().getNome())
			.data(p.getData())
			.hora(p.getHora())
			.observacao(p.getObservacao())
			.total(p.getServicos().getPrice())
			.data_agendamento(p.getData_agendamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
			.build();
	}
	

}
