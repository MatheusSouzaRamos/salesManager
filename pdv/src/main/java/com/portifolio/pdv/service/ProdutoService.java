package com.portifolio.pdv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.portifolio.pdv.dto.ProdutoDTO;

import com.portifolio.pdv.model.Produto;
import com.portifolio.pdv.repository.ProdutoRepository;
import com.portifolio.pdv.service.exception.EntityNotFoundException;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll(){
        List<Produto> produtos = repository.findAll(Sort.by("id"));
        List<ProdutoDTO> dto = new ArrayList<>();
        for(Produto p : produtos){
            dto.add(new ProdutoDTO(p));
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id){
        Produto entity = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Nao encontrado"));
        return new ProdutoDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findByNome(String nome){
        List<Produto> produtos = repository.findByNomeContainingIgnoringCase(nome, Sort.by("id"));
        List<ProdutoDTO> dto = new ArrayList<>();
        for(Produto p : produtos){
            dto.add(new ProdutoDTO(p));
        }
        return dto;
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto){
        Produto entity = new Produto();
        
        entity.setNome(dto.getNome());
        entity.setValor(dto.getValor());
        
        entity = repository.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto){
        Produto entity = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Nao encontrado"));
        entity.setNome(dto.getNome());
        entity.setValor(dto.getValor());
        entity = repository.save(entity);
        return new ProdutoDTO(entity);
    }


}
