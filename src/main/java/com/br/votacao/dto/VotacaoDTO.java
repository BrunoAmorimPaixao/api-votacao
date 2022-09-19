package com.br.votacao.dto;

import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Sessao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VotacaoDTO {

    private String id;
    @NotEmpty(message = "Sessao obrigatório")
    @NotNull
    private Sessao sessao;
    @NotEmpty(message = "Associado obrigatório")
    @NotNull
    private Associado associado;
    @NotEmpty(message = "Voto obrigatório")
    @NotNull
    private boolean voto;
}
