package com.portifolio.pdv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.pdv.model.carrinho.ItemCarrinho;
import com.portifolio.pdv.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin("*")
public class CarrinhoController {
    private final CarrinhoService service;

    CarrinhoController(CarrinhoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ItemCarrinho>> findAll(){
        List<ItemCarrinho> itens = service.findAll();
        return ResponseEntity.ok().body(itens);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ItemCarrinho> adicionar(@RequestBody ItemCarrinho item){
        item = service.inserirItem(item);
        return ResponseEntity.ok().body(item);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<ItemCarrinho> remover(@PathVariable Long id){
        service.removerItem(id);
        return ResponseEntity.noContent().build();
    }
    
}
