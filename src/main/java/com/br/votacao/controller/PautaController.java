package com.br.votacao.controller;

import com.br.votacao.domain.Pauta;
import com.br.votacao.dto.PautaDTO;
import com.br.votacao.service.PautaService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pauta")
@CrossOrigin(origins = "*") // requisao de qq dominio
@RequiredArgsConstructor
public class PautaController {

    private static final Logger log = LoggerFactory.getLogger(PautaController.class);

    private final PautaService service;

    private final ModelMapper modelMapper;

    Map<String,String> msg = new HashMap<String, String>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PautaDTO> cadastrarPauta(@Valid @RequestBody PautaDTO pautaDTO) {

        log.info("Cadastrando a pauta: {}", pautaDTO.toString());
        Pauta pauta = modelMapper.map(pautaDTO, Pauta.class);
        pauta = service.salvar(pauta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pauta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPauta(@PathVariable String id, @RequestBody @Valid PautaDTO pautaDTO) {

        service.findById(pautaDTO.getId()).map( p-> {
            p.setId(pautaDTO.getId());
            p.setNome(pautaDTO.getNome());
            return service.salvar(p);
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletar pauta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPauta(@PathVariable ("id") String id) {
        service.findById(id).map(p -> {
            service.deletarPauta(p);
            return Void.TYPE;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada"));

    }

    @GetMapping("{id}")
    @ApiOperation(value = "Resultado Pauta")
    public ResponseEntity<Map<String,String>> resultadoPauta (@PathVariable String id) {
        Pauta pauta = service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Pauta não encontrada"));
        if (pauta.getTotalAfavor()> pauta.getTotalContra()) {
            msg.put("Pauta que teve mais votos foi:",  pauta.getNome() + " com " + pauta.getTotalAfavor() + " voto(s).");
        } else {
            msg.put("Pauta que teve mais votos foi:",  pauta.getNome() + " com " + pauta.getTotalContra() + " voto(s).");
        }

        return ResponseEntity.ok().body(msg);
    }

}
