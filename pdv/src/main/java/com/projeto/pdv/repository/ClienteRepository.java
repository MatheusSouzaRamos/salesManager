package com.projeto.pdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.pdv.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
