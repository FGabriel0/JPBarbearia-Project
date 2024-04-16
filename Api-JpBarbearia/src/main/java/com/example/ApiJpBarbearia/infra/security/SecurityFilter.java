package com.example.ApiJpBarbearia.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ApiJpBarbearia.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private UsuarioRepository repository;
	
	
	@Autowired
	private TokenService tokenService;
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recoverToken(request);
		if(token != null) {
			
			String login = tokenService.validateToken(token);
			
			UserDetails user = repository.findByLogin(login);
					
            var authentication = new UsernamePasswordAuthenticationToken(user,
            			null, user.getAuthorities());
            
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")) {
			return null;
		}
		
		return authHeader.substring(7, authHeader.length());
	}

}
