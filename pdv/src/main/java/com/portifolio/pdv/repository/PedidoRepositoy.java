package com.portifolio.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.pdv.model.Pedido;

@Repository
public interface PedidoRepositoy extends JpaRepository<Pedido, Long>{  
}
