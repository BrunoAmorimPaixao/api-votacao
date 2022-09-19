package com.br.votacao.repository;

import com.br.votacao.domain.Sessao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends MongoRepository<Sessao, String> {


}
