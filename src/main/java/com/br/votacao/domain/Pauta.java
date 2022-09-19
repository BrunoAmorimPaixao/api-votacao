package com.br.votacao.domain;

import com.br.votacao.dto.PautaDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Document(collection = "pauta")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Pauta implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nome;

    private int totalAfavor;

    private int totalContra;

}
