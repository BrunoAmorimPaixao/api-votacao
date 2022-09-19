package com.br.votacao.dto;

import com.br.votacao.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessaoDTO {

    private String id;
    @NotNull(message = "Pauta obrigatório" )
    private Pauta pauta;
}
