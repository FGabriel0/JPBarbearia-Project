package com.example.ApiJpBarbearia.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiJpBarbearia.controler.form.AgendamentoForm;
import com.example.ApiJpBarbearia.entidy.Agendamento;
import com.example.ApiJpBarbearia.entidy.Servicos;
import com.example.ApiJpBarbearia.entidy.Usuario;
import com.example.ApiJpBarbearia.enums.AgendamentoStatusEnum;
import com.example.ApiJpBarbearia.exception.RegraNegocioException;
import com.example.ApiJpBarbearia.repository.AgendamentoRepository;
import com.example.ApiJpBarbearia.repository.ServiceRepository;
import com.example.ApiJpBarbearia.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class AgendamentoIMPL {
	
	@Autowired
	private AgendamentoRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ServiceRepository servicosRepository;
	
	@Transactional
	public Agendamento salvaAgendamento(AgendamentoForm form) {
		
		Integer idUsuario = form.getUsuario_id();
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new RegraNegocioException("Usuario não encontrado"));
		
		Integer idServico = form.getServico_id();
		Servicos servicos = servicosRepository.findById(idServico)
				.orElseThrow(() -> new RegraNegocioException("Serviço não encontrado"));
		
		Agendamento agendamento = Agendamento.builder()
				.usuario_id(usuario)
				.telefone(usuario.getTelefone())
				.servicos(servicos)
				.data(form.getData())
				.hora(form.getHora())
				.observacao(form.getObservacao())
				.total(servicos.getPrice())
				.agendamentoStatus(AgendamentoStatusEnum.AGENDADO)
				.data_agendamento(LocalDateTime.now())
				.build();
		
		repository.save(agendamento);
		return agendamento;
					
	}
	
	public List<Agendamento> listaTodos(){
		return repository.findAll();
	}
	
	
	public Optional<Agendamento> buscarPorId(Integer id){
		return repository.findByIdFetchItens(id);
	}
}
