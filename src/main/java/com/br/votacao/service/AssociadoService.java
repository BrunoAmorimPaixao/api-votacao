package com.br.votacao.service;

import com.br.votacao.domain.Associado;
import java.util.Optional;

public interface AssociadoService {

    Associado salvar(Associado associado);

    Optional<Associado> findByCpf(String id);

    Optional<Associado> findById(String id);

    void deletarAssociado(Associado associado);


}
