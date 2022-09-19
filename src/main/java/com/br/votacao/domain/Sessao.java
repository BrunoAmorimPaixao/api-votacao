package com.br.votacao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "sessao")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Sessao {

    @Id
    private String id;
    private Date dataInicio;
    private Date dataFim;
    @DBRef
    private Pauta pauta;
    private boolean incioSessao;
    private boolean fimSessao;
}
