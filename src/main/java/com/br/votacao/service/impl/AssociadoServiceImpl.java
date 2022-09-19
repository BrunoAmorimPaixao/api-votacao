package com.br.votacao.service.impl;

import com.br.votacao.domain.Associado;
import com.br.votacao.repository.AssociadoRepository;
import com.br.votacao.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssociadoServiceImpl implements AssociadoService {

    private static final Logger log = LoggerFactory.getLogger(AssociadoServiceImpl.class);

    private final AssociadoRepository repository;

    @Override
    public Associado salvar(Associado associado) {
        return repository.save(associado);
    }

    @Override
    public Optional<Associado> findByCpf(String cpf) {
        log.info("Busvando associado pelo cpf: {}", cpf);
        return Optional.ofNullable(this.repository.findByCpf(cpf));
    }

    @Override
    public Optional<Associado> findById(String id) {
        log.info("Busvando associado pelo id: {}", id);
        return this.repository.findById(id);
    }

    @Override
    public void deletarAssociado(Associado associado) {
        log.info("deletar: {}", associado.getCpf());
        repository.delete(associado);
    }

}
