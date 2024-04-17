package com.example.ApiJpBarbearia.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiJpBarbearia.controler.dto.ServicosDTO;
import com.example.ApiJpBarbearia.controler.form.ServicosForm;
import com.example.ApiJpBarbearia.entidy.Servicos;
import com.example.ApiJpBarbearia.exception.RegraNegocioException;
import com.example.ApiJpBarbearia.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicosServices {
	
	@Autowired
	private ServiceRepository repository;
	
	private static final ModelMapper modelMapper = new ModelMapper();

	
	public List<Servicos> buscarTodos(){
		return repository.findAll();
	}
	
	public Optional<Servicos> buscarPorId(Integer id){
		return repository.findById(id);
	}
	
	public Servicos salvar(ServicosForm form) {
		
		Servicos servicos =  Servicos.builder()
				.nome(form.getNome())
				.price(form.getPrice())
				.time(form.getTime())
				.build();
		return repository.save(servicos);
		
	}
	
	public ServicosDTO buscarPorIdDTO (Integer id) {
		Optional<Servicos> servico = repository.findById(id);
		if(servico.isPresent()) {
			return modelMapper.map(servico,  ServicosDTO.class);
		}
		return null;
	}
	
	public Servicos atualizarServico(Integer id, Servicos atualizar) {
		Optional<Servicos> servicosNovo = repository.findById(id); 
		
		if(servicosNovo.isPresent()) {
			Servicos servicos = servicosNovo.get();
					servicos.setNome(atualizar.getNome());
					servicos.setPrice(atualizar.getPrice());
					servicos.setTime(atualizar.getTime());
					servicos.setDataAtualizacao(LocalDateTime.now());
			
			return repository.save(servicos);
		}
		else {
            throw new RegraNegocioException("Ponto de Venda não encontrado");
		}
	}
	
	
	public void Deletar(Integer id) {
		repository.deleteById(id);
	}
	
	
	
}
