package com.br.votacao.controller;

import com.br.votacao.client.CpfClient;
import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Pauta;
import com.br.votacao.dto.AssociadoDTO;
import com.br.votacao.dto.PautaDTO;
import com.br.votacao.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/associado")
@CrossOrigin(origins = "*") // requisao de qq dominio
@RequiredArgsConstructor
public class AssociadoController {


    private static final Logger log = LoggerFactory.getLogger(AssociadoController.class);

    private final AssociadoService service;

    private final ModelMapper modelMapper;

    private final CpfClient cpfClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssociadoDTO cadastrarAssociado(@Valid @RequestBody AssociadoDTO associadoDTO) {

        log.info("Cadastrando de Associado: {}", associadoDTO.toString());
        Associado associado = modelMapper.map(associadoDTO, Associado.class);
      //  if (validarCpf(associado)) {
            associado = service.salvar(associado);
       // }
        return modelMapper.map(associado, AssociadoDTO.class);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAssociado(@PathVariable String id, @RequestBody @Valid AssociadoDTO associadoDTO) {

        service.findById(id).map( a-> {
            a.setId(associadoDTO.getId());
            a.setCpf(associadoDTO.getCpf());
            return service.salvar(a);
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirAssociado(@PathVariable ("id") String id) {
        service.findById(id).map(p -> {
            service.deletarAssociado(p);
            return Void.TYPE;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associado não encontrado"));

    }

    public boolean validarCpf(Associado associado) {
        boolean validado = true;
        String cpf = cpfClient.buscaCpfId(associado.getCpf());
        if (cpf == null) {
            validado = false;
        }
        return validado;

    }


}
