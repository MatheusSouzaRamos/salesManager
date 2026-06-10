package com.portifolio.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.pdv.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
