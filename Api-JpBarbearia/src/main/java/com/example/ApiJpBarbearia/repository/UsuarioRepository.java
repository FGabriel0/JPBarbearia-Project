package com.example.ApiJpBarbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiJpBarbearia.entidy.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
