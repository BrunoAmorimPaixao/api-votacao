package com.br.votacao.repository;

import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Pauta;
import com.br.votacao.dto.AssociadoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends MongoRepository<Associado, String> {

    Optional<Associado> findByCpf(String cpf);

}
