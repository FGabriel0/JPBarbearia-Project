package com.example.ApiJpBarbearia.controler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiJpBarbearia.controler.dto.AuthResponseDTO;
import com.example.ApiJpBarbearia.controler.form.AuthForm;
import com.example.ApiJpBarbearia.controler.form.UsuarioForm;
import com.example.ApiJpBarbearia.entidy.Usuario;
import com.example.ApiJpBarbearia.enums.ResponseStatusEnum;
import com.example.ApiJpBarbearia.enums.UserRoles;
import com.example.ApiJpBarbearia.erros.ApiErros;
import com.example.ApiJpBarbearia.infra.security.TokenService;
import com.example.ApiJpBarbearia.reponse.Response;
import com.example.ApiJpBarbearia.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService service;
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public AuthResponseDTO logar(@RequestBody @Valid AuthForm form) {
		
		var userNamePassword = new UsernamePasswordAuthenticationToken(form.getLogin(), form.getPassword());
		authenticationManager.authenticate(userNamePassword); 
				
        return service.obterToken(form);
			
	}
	
	 @PostMapping("/refresh-token")
	 @ResponseStatus(HttpStatus.OK)
	    public AuthResponseDTO authRefreshToken(@RequestBody AuthResponseDTO requestRefreshDto) {
	        return service.obterRefreshToken(requestRefreshDto.getRefreshToken());
	    }
	
	
	@PostMapping("/register")
	public ResponseEntity<Response<Usuario>> register(@RequestBody @Valid UsuarioForm form) {
		Response<Usuario>  response = new Response<>();

		try {
			if(repository.findByLogin(form.getNome()) != null) {
				return ResponseEntity.badRequest().build();
			}
			
			String encryptedPassword = new BCryptPasswordEncoder().encode(form.getPassword());
			
			Usuario newUser = Usuario.builder()
					.login(form.getNome())
					.email(form.getEmail())
					.telefone(form.getTelefone())
					.password(encryptedPassword)
					.role(form.getRole())
					.data_criacao(LocalDateTime.now())
					.build();
			
			repository.save(newUser);
			
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(newUser);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);	
		}
			
	
	}

}
