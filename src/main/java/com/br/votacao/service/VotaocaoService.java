package com.br.votacao.service;

import com.br.votacao.domain.Associado;import com.br.votacao.domain.Votacao;

import java.util.List;
import java.util.Optional;

public interface VotaocaoService {

    Votacao salvar(String sessaoId, String cpf, String voto) throws Exception;

    Optional<Votacao> buscarPorAssociado(Associado associado);

    List<Votacao> findAll();
}

