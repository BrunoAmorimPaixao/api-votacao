package com.br.votacao.service;

import com.br.votacao.domain.Sessao;
import java.util.List;
import java.util.Optional;

public interface SessaoService {

    Sessao salvar(Sessao sessao);

    Optional<Sessao> findById(String id);

    void deletarSessao(Sessao sessao);

    List<Sessao> findAll();


}
