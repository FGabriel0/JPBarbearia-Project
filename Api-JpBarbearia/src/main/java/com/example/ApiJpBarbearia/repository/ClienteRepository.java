package com.example.ApiJpBarbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.ApiJpBarbearia.entidy.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	UserDetails findByEmail(String email);
}
