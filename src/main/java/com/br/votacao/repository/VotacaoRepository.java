package com.br.votacao.repository;

import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Votacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotacaoRepository extends MongoRepository<Votacao, String> {

    Votacao findByAssociado(Associado associado);



}
