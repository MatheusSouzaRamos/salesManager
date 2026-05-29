package com.projeto.pdv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.pdv.dto.PedidoDTO;
import com.projeto.pdv.dto.ProdutoDTO;
import com.projeto.pdv.model.Pedido;
import com.projeto.pdv.model.Produto;
import com.projeto.pdv.repository.PedidoRepository;
import com.projeto.pdv.service.exceptions.DatabaseIntegrityException;
import com.projeto.pdv.service.exceptions.EntityNotFoundException;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll(){
        List<Pedido> list = repository.findAll();
        List<PedidoDTO> dto = new ArrayList<>();
        for(Pedido p : list){
            dto.add(new PedidoDTO(p));
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        Optional<Pedido> obj = repository.findById(id);
        Pedido entity = obj.orElseThrow(() -> new EntityNotFoundException("Não encontrado."));
        return new PedidoDTO(entity);
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto){
        Pedido entity = new Pedido();
        entity.setCliente(dto.getCliente());
        entity.setListaProdutos(dto.getListaProdutos());
        entity = repository.save(entity);
        return new PedidoDTO(entity);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto){
        Pedido entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado."));
        entity.setCliente(dto.getCliente());
        entity.setListaProdutos(dto.getListaProdutos());
        entity = repository.save(entity);
        return new PedidoDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch(Exception e){
            throw new DatabaseIntegrityException("Integridade violada.");
        }
    }

    @Transactional
    public PedidoDTO updateAdicionarProduto(Long id, ProdutoDTO produtoDto){
        Pedido entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado."));
        Produto produto = new Produto(produtoDto);
        entity.getListaProdutos().add(produto);
        entity = repository.save(entity);
        return new PedidoDTO(entity);
    }

    @Transactional PedidoDTO updateRemoverProduto(Long id, ProdutoDTO produtoDto){
        Pedido entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado."));
        Produto produto = new Produto(produtoDto);
        entity.getListaProdutos().remove(produto);
        entity = repository.save(entity);
        return new PedidoDTO(entity);
    }
}
