package com.example.ApiJpBarbearia.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiJpBarbearia.Impl.ServicosServices;
import com.example.ApiJpBarbearia.controler.dto.ServicosDTO;
import com.example.ApiJpBarbearia.controler.form.ServicosForm;
import com.example.ApiJpBarbearia.entidy.Servicos;
import com.example.ApiJpBarbearia.enums.ResponseStatusEnum;
import com.example.ApiJpBarbearia.reponse.Response;

import jakarta.validation.Valid;

@RequestMapping("/servicos")
@RestController
public class ServicosController {
	
	@Autowired
	private ServicosServices service;
	
	
	
	@GetMapping
	public ResponseEntity<Response<List<Servicos>>> buscarTodosServicos(){
		Response<List<Servicos>> response = new Response<>(); 
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(service.buscarTodos());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
		@GetMapping("/{id}")
		public ResponseEntity<Response<ServicosDTO>> buscarPorId(@PathVariable Integer id){
			Response<ServicosDTO> response = new Response<>();
			try {
				ServicosDTO servicos= service.buscarPorIdDTO(id);
				if(servicos != null) {
					response.setStatus(ResponseStatusEnum.SUCCESS);
					response.setData(servicos);
					return ResponseEntity.ok(response);
				}
				else {
					response.setStatus(ResponseStatusEnum.ERROR);
					response.setMessage("Ponto de venda não encontrado.");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);	
				}

			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(ResponseStatusEnum.ERROR);
				response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}		
		}
		
		@PostMapping("/salvar")
		public ResponseEntity<Response<Servicos>> salvaServico(
				@RequestBody @Valid ServicosForm form){			
			Response<Servicos> response = new Response<>();
			Servicos newServico = service.salvar(form);
			try {
				if(newServico != null) {
					response.setStatus(ResponseStatusEnum.SUCCESS);
					response.setData(newServico);
					return ResponseEntity.ok(response);
				}
				else {
					response.setStatus(ResponseStatusEnum.ERROR);
					response.setMessage("Serviço não Cadastrado.");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);	
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(ResponseStatusEnum.ERROR);
				response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);			}
		}
		
		
		@PatchMapping("/{id}")
		public ResponseEntity<Response<Servicos>> atualizarServico(
				@PathVariable Integer id,@RequestBody Servicos servicos){
			Response<Servicos> response = new Response<>();
			try {
				Servicos servicoAtualizar = service.atualizarServico(id, servicos);
				if(servicoAtualizar != null) {
					response.setStatus(ResponseStatusEnum.SUCCESS);
					response.setData(servicoAtualizar);
					ResponseEntity.ok(response);
				}
				else {
					response.setStatus(ResponseStatusEnum.ERROR);
					response.setMessage("Serviço não Encontrado para Alteração");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);	
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(ResponseStatusEnum.ERROR);
				response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
			return null;
		}
		
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Response<Servicos>> deletarServico(@PathVariable Integer id){
			Response<Servicos> response = new Response<>();
			try {
				 service.Deletar(id);
				response.setStatus(ResponseStatusEnum.SUCCESS);
				return ResponseEntity.ok(response);
			}
			catch (EmptyResultDataAccessException e) {
		        response.setStatus(ResponseStatusEnum.ERROR);
		        response.setMessage("Serviço não encontrado para exclusão");
		        return ResponseEntity.notFound().build();
			}
		        catch (Exception e) {
		        	e.printStackTrace();
					response.setStatus(ResponseStatusEnum.ERROR);
					response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			} 
			
		}
}
		
	

