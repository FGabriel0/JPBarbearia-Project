package com.example.ApiJpBarbearia.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiJpBarbearia.entidy.Servicos;

@Repository
public interface ServiceRepository extends JpaRepository<Servicos, Integer> {

	

}
