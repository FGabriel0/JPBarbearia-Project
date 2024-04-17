package com.example.ApiJpBarbearia.controler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiJpBarbearia.Impl.ClienteService;
import com.example.ApiJpBarbearia.controler.dto.AuthResponseDTO;
import com.example.ApiJpBarbearia.controler.form.AuthClienteForm;
import com.example.ApiJpBarbearia.controler.form.ClienteForm;
import com.example.ApiJpBarbearia.entidy.Cliente;
import com.example.ApiJpBarbearia.enums.UserRoles;
import com.example.ApiJpBarbearia.infra.security.TokenService;
import com.example.ApiJpBarbearia.repository.ClienteRepository;

import jakarta.validation.Valid;

@RequestMapping("/cliente")
@RestController
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TokenService service;
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity logar(@RequestBody @Valid AuthClienteForm form) {
		
		var userNamePassword = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
		var authetication = authenticationManager.authenticate(userNamePassword); 
		
		String accessToken = service.generateTokenCliente((Cliente)authetication.getPrincipal());
		
        return ResponseEntity.ok(new AuthResponseDTO(accessToken));
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<Cliente> register(@RequestBody @Valid ClienteForm form) {

			if(repository.findByEmail(form.getEmail()) != null) {
				return ResponseEntity.badRequest().build();
			}
			
			String encryptedPassword = new BCryptPasswordEncoder().encode(form.getPassword());
			form.setRole(UserRoles.USER);
			Cliente newUser = Cliente.builder()
					.nome(form.getNome())
					.email(form.getEmail())
					.telefone(form.getTelefone())
					.password(encryptedPassword)
					.role(form.getRole())
					.data_criacao(LocalDateTime.now())
					.build();
			repository.save(newUser);
			return ResponseEntity.ok().build();
	
	}

}

