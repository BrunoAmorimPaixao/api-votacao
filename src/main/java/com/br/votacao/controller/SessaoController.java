package com.br.votacao.controller;

import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Pauta;
import com.br.votacao.domain.Sessao;
import com.br.votacao.dto.AssociadoDTO;
import com.br.votacao.dto.SessaoDTO;
import com.br.votacao.service.AssociadoService;
import com.br.votacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/sessao")
@CrossOrigin(origins = "*") // requisao de qq dominio
@RequiredArgsConstructor
public class SessaoController {

    private static final Logger log = LoggerFactory.getLogger(SessaoController.class);

    private final SessaoService service;

    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessaoDTO cadastrarSessao(@Valid @RequestBody SessaoDTO sessaoDTO) {

        log.info("Cadastrando de Sessao: {}", sessaoDTO.toString());
        Sessao sessao = modelMapper.map(sessaoDTO, Sessao.class);
        sessao.setDataInicio(new Date());
        sessao = service.salvar(sessao);
        return modelMapper.map(sessao, SessaoDTO.class);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirSessao(@PathVariable ("id") String id) {
        service.findById(id).map(p -> {
            service.deletarSessao(p);
            return Void.TYPE;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada"));

    }

    @GetMapping("{id}")
    public Sessao resultadoSessao (@PathVariable String id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Sessao não encontrada"));

    }


}
