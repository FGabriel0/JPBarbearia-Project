package com.example.ApiJpBarbearia.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ApiJpBarbearia.controler.dto.AuthResponseDTO;
import com.example.ApiJpBarbearia.controler.form.AuthForm;
import com.example.ApiJpBarbearia.entidy.Usuario;
import com.example.ApiJpBarbearia.exception.UnauthorizedException;
import com.example.ApiJpBarbearia.repository.UsuarioRepository;

@Service
public class TokenService {
	
	@Autowired
	private UsuarioRepository repository;

	 @Value("${api.security.token.secret}")
	 private String secret;
	 
	 @Value("${api.jwt.token.expiration}")
	 private Integer expiredIn;
	 
	 @Value("${api.jwt.token.refresh}")
	 private Integer refreshToken;
	 
	 public AuthResponseDTO obterToken(AuthForm form) {
		 Usuario usuario = (Usuario) repository.findByLogin(form.getLogin());
		 
		 return AuthResponseDTO.builder()
				 .nome(usuario.getLogin())
				 .email(usuario.getEmail())
				 .accessToken(generateToken(usuario,expiredIn))
				 .refreshToken(generateToken(usuario,refreshToken))
				 .expiresIn(expiredIn)
				 .build();
		 
	 }
	 
	    public String generateToken(Usuario user, Integer expiredIn){
	        try{
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            return JWT.create()
	                    .withIssuer("auth-api")
	                    .withSubject(user.getLogin())
	                    .withExpiresAt(genExpirationDate(expiredIn))
	                    .sign(algorithm);
	        } catch (JWTCreationException exception) {
	            throw new RuntimeException("Error while generating token", exception);
	        }
	    }

	    public String validateToken(String token){
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            return JWT.require(algorithm)
	                    .withIssuer("auth-api")
	                    .build()
	                    .verify(token)
	                    .getSubject();
	        } catch (JWTVerificationException exception){
	            return "";
	        }
	    }

	    private Instant genExpirationDate( Integer expiredIn){
	        return LocalDateTime.now().plusHours(expiredIn).toInstant(ZoneOffset.of("-03:00"));
	    }
	    
	    public AuthResponseDTO obterRefreshToken(String horaRefreshToken) {

	        String login = validateToken(horaRefreshToken);
	        Usuario usuario = (Usuario) repository.findByLogin(login);

	        if (usuario == null) {
	            throw new UnauthorizedException("UnauthorizedException");
	        }

	        var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

	        SecurityContextHolder.getContext().setAuthentication(autentication);

	        return AuthResponseDTO
	                .builder()
	                .accessToken(generateToken(usuario,expiredIn))
	                .refreshToken(generateToken(usuario,refreshToken))
	                .build();
	    }
	
}
