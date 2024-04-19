package com.example.ApiJpBarbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.ApiJpBarbearia.entidy.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	UserDetails findByLogin(String login);
	
}
