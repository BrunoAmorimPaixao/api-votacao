package com.br.votacao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "votacao")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Votacao {

    @Id
    private String id;
    @DBRef
    private Sessao sessao;
    @DBRef
    private Associado associado;
    private boolean voto;
}
