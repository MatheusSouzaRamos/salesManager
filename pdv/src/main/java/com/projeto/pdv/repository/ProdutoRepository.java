package com.projeto.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.pdv.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
