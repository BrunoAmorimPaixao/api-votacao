package com.br.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssociadoDTO {

    @Id
    private String id;
    @NotEmpty(message = "CPF obrigatório")
    @NotNull
    private String cpf;


}
