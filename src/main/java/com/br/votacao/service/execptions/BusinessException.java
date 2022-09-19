package com.br.votacao.service.execptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String messagem) {
        super(messagem);
    }
}
