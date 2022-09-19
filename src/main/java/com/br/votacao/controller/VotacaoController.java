package com.br.votacao.controller;

import com.br.votacao.domain.Votacao;
import com.br.votacao.service.VotaocaoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/votacao")
@CrossOrigin(origins = "*") // requisao de qq dominio
@RequiredArgsConstructor
public class VotacaoController {

    private static final Logger log = LoggerFactory.getLogger(VotacaoController.class);

    private final VotaocaoService service;


    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String,String>> votacao(@RequestParam String sessaoId,
                                                      @RequestParam String cpf,
                                                      @RequestParam String voto) throws Exception {
        Votacao votacao;
        votacao = service.salvar(sessaoId, cpf,voto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(votacao.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

}
