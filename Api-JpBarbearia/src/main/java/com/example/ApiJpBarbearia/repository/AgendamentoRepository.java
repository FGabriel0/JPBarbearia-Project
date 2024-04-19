package com.example.ApiJpBarbearia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ApiJpBarbearia.entidy.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    @Query("select distinct a from Agendamento a left join fetch a.usuario_id u left join fetch a.servicos s where a.id = :id")
    Optional<Agendamento> findByIdFetchItens(@Param("id") Integer id);

}
