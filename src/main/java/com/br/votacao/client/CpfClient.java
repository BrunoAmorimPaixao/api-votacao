package com.br.votacao.client;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CpfClient {

    public String buscaCpfId(String cpf){
        RestTemplate template = new RestTemplate();
        String retornoCpf = template.getForObject("https://user-info.herokuapp.com/users/".concat(cpf), String.class);
        return retornoCpf ;
    }
}
