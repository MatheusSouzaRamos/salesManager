package com.projeto.pdv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.pdv.dto.ProdutoDTO;
import com.projeto.pdv.model.Produto;
import com.projeto.pdv.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll(){
        List<Produto> list = repository.findAll();
        List<ProdutoDTO> dto = new ArrayList<>();
        for(Produto p : list){
            dto.add(new ProdutoDTO(p));
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id){
        Optional<Produto> obj = repository.findById(id);
        Produto produto = obj.orElseThrow(() -> new EntityNotFoundException());
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto){
        Produto entity = new Produto();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setValor(entity.getValor());
        entity = repository.save(entity);
        return new ProdutoDTO(entity);
    }

}
