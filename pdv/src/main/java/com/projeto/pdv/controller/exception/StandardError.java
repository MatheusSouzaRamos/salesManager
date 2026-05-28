package com.projeto.pdv.controller.exception;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError {
    private Instant timeInstant;
    private Integer status;
    private String Error;
    private String message;
    private String path;
}
