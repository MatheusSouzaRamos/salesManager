package com.portifolio.pdv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.pdv.dto.ProdutoDTO;
import com.portifolio.pdv.model.Produto;
import com.portifolio.pdv.model.carrinho.ItemCarrinho;
import com.portifolio.pdv.service.CarrinhoService;
import com.portifolio.pdv.service.PedidoService;
import com.portifolio.pdv.service.ProdutoService;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin("*")
public class CarrinhoController {
    private CarrinhoService service;
    private ProdutoService produtoService;
    private PedidoService pedidoService;


    public CarrinhoController(CarrinhoService service, ProdutoService produtoService, PedidoService pedidoService){
        this.service = service;
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;
    }


    @GetMapping
    public ResponseEntity<List<ItemCarrinho>> findAll(){
        List<ItemCarrinho> itens = service.findAll();
        return ResponseEntity.ok().body(itens);
    }

    @PostMapping("/inserir/{id}/{qtd}")
    public void inserir(@PathVariable Long id, @PathVariable Long qtd){
        ProdutoDTO dto = produtoService.findById(id);
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setValor(dto.getValor());
        service.inserirItem(new ItemCarrinho(produto, qtd));
    }

    @DeleteMapping("/{id}")
    public void retirar(@PathVariable Long id){
        service.removerItem(id);
    }

    @GetMapping("/totais")
    public ResponseEntity<List<String>> getTotais(){
        List<String> totais = service.getTotais();
        return ResponseEntity.ok().body(totais);
    }

    @DeleteMapping("/limpar")
    public void limparCarrinho(){
        service.limparCarrinho();
    }


}
