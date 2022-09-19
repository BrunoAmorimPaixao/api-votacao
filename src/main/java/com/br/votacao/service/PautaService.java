package com.br.votacao.service;

import com.br.votacao.domain.Pauta;
import java.util.Optional;

public interface PautaService {

    Pauta salvar(Pauta pauta);

    Optional<Pauta> findById(String id);

    Optional<Pauta> buscarPorNome(Pauta pauta);

    void deletarPauta(Pauta pauta);

}
