package com.br.votacao.dto;


import com.br.votacao.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PautaDTO {

    private String id;

    @NotEmpty(message = "Nome obrigatório")
    @NotNull
    private String nome;

    public PautaDTO(Pauta pauta) {
        this.nome = pauta.getNome();
    }
}
