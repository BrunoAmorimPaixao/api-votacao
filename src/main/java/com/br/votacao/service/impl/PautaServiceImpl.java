package com.br.votacao.service.impl;

import com.br.votacao.domain.Pauta;
import com.br.votacao.repository.PautaRepository;
import com.br.votacao.service.PautaService;
import com.br.votacao.service.execptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private static final Logger log = LoggerFactory.getLogger(PautaServiceImpl.class);

    private final PautaRepository repository;

    @Override
    public Pauta salvar(Pauta pauta) {
        log.info("Salvando a pauta: {}", pauta.getNome());
        buscarPorNome(pauta);
        return repository.save(pauta);
    }

    @Override
    public Optional<Pauta> findById(String id) {
        log.info("Busvando a pauta pelo nome: {}", id);
        return this.repository.findById(id);
    }

    @Override
    public Optional<Pauta> buscarPorNome(Pauta p) {
        log.info("Busvando a pauta pelo nome: {}", p.getNome());
        Optional<Pauta>  pauta = this.repository.findByNome(p.getNome());
        if (pauta.isPresent() && !pauta.get().getId().equals(p.getId())) {
            throw new BusinessException("Pauta com nome já cadastrado");
        }

        return pauta;
    }

    public void deletarPauta(Pauta pauta) {
        log.info("deletar: {}", pauta.getNome());
        repository.delete(pauta);
    }
}
