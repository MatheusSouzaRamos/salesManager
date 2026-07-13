package com.portifolio.pdv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.pdv.dto.PedidoDTO;
import com.portifolio.pdv.service.PedidoService;

@RestController
@RequestMapping("pedidos")
@CrossOrigin("*")
public class PedidoController {

    private final PedidoService service;

    PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll(){
        List<PedidoDTO> dto = service.findAll();
        return ResponseEntity.ok().body(dto);

    }


}
