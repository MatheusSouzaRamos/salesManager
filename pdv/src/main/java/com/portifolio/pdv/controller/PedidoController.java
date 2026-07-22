package com.portifolio.pdv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.pdv.dto.ClienteDTO;
import com.portifolio.pdv.dto.PedidoDTO;
import com.portifolio.pdv.model.Cliente;
import com.portifolio.pdv.model.Produto;
import com.portifolio.pdv.model.carrinho.Carrinho;
import com.portifolio.pdv.model.carrinho.ItemCarrinho;
import com.portifolio.pdv.service.ClienteService;
import com.portifolio.pdv.service.PedidoService;

@RestController
@RequestMapping("pedidos")
@CrossOrigin("*")
public class PedidoController {

    private final PedidoService service;
    private final ClienteService clienteService;

    PedidoController(PedidoService service, ClienteService clienteService) {
        this.service = service;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll(){
        List<PedidoDTO> dto = service.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/inserirPedido/{id}")
    public void inserirPedido(@PathVariable Long id, @RequestBody Carrinho carrinho){
        ClienteDTO clienteDTO = clienteService.findById(id);
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());

        List<Produto> produtos = new ArrayList<>();

        for (ItemCarrinho ic : carrinho.getAll()) {
            for(int i = 0; i < ic.getQuantidade(); i++){
                produtos.add(ic.getProduto());
            }
        }
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> gerarPdf(@PathVariable Long id){
        byte[] pdf = service.gerarPdf(id);

        
        return ResponseEntity.ok() .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pedido.pdf") .contentType(MediaType.APPLICATION_PDF) .body(pdf);
    }
}
