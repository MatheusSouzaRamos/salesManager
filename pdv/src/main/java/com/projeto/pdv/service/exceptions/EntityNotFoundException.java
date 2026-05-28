package com.projeto.pdv.service.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String msg){
        super(msg);
    }
}
