package com.portifolio.pdv.service;

import org.springframework.stereotype.Service;

import com.portifolio.pdv.repository.PedidoRepositoy;

@Service
public class PedidoService {
    private final PedidoRepositoy repository;

    PedidoService(PedidoRepositoy repository) {
        this.repository = repository;
    }

    //public 
}
