package com.projeto.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.pdv.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
