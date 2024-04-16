package com.example.ApiJpBarbearia.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiJpBarbearia.controler.dto.AuthResponseDTO;
import com.example.ApiJpBarbearia.controler.form.AuthForm;
import com.example.ApiJpBarbearia.controler.form.UsuarioForm;
import com.example.ApiJpBarbearia.entidy.Usuario;
import com.example.ApiJpBarbearia.infra.security.TokenService;
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
	public ResponseEntity logar(@RequestBody @Valid AuthForm form) {
		
		var userNamePassword = new UsernamePasswordAuthenticationToken(form.getLogin(), form.getPassword());
		var authetication = authenticationManager.authenticate(userNamePassword); 
		
		String accessToken = service.generateToken((Usuario)authetication.getPrincipal());
		
        return ResponseEntity.ok(new AuthResponseDTO(accessToken));
			
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<Usuario> register(@RequestBody @Valid UsuarioForm form) {

		
			if(repository.findByLogin(form.getNome()) != null) {
				return ResponseEntity.badRequest().build();
			}
			
			String encryptedPassword = new BCryptPasswordEncoder().encode(form.getPassword());
			
			Usuario newUser = Usuario.builder()
					.login(form.getNome())
					.email(form.getEmail())
					.password(encryptedPassword)
					.role(form.getRole())
					.build();
			repository.save(newUser);
			return ResponseEntity.ok().build();
	
	}

}
