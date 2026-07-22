package com.portifolio.pdv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.pdv.dto.ClienteDTO;
import com.portifolio.pdv.dto.PedidoDTO;
import com.portifolio.pdv.model.Cliente;
import com.portifolio.pdv.model.ItemPedido;
import com.portifolio.pdv.model.Pedido;
import com.portifolio.pdv.model.carrinho.ItemCarrinho;
import com.portifolio.pdv.repository.PedidoRepositoy;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
    private final PedidoRepositoy repository;
    private final ClienteService clienteService;

    PedidoService(PedidoRepositoy repository, ClienteService clienteService) {
        this.repository = repository;
        this.clienteService = clienteService;
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll(){
        List<Pedido> pedidos = repository.findAll();
        List<PedidoDTO> dto = new ArrayList<>();
        for(Pedido p : pedidos){
            dto.add(new PedidoDTO(p));
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        Pedido entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));
        PedidoDTO dto = new PedidoDTO(entity);
        return dto;
    }

    @Transactional
    public void fecharPedido(Long clienteId, List<ItemCarrinho> itensCarrinho) {
        if (itensCarrinho.isEmpty()) {
            throw new IllegalArgumentException("O carrinho está vazio.");
        }

        ClienteDTO clienteDTO = clienteService.findById(clienteId);
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefone(clienteDTO.getTelefone());
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        for (ItemCarrinho ic : itensCarrinho) {
            ItemPedido item = new ItemPedido();
            item.setProduto(ic.getProduto());
            item.setQuantidade(ic.getQuantidade());
            item.setValorUnitario(ic.getProduto().getValor());
            pedido.adicionarItem(item);
        }
        repository.save(pedido);
    }

    public byte[] gerarPdf(Long id){
        PedidoDTO dto = this.findById(id);
        return pdfService.gerarPdf(dto);
    }

}
