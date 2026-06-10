package com.portifolio.pdv.service.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException (String msg){
        super(msg);
    }
}
