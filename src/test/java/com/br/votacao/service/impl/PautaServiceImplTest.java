package com.br.votacao.service.impl;

import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Pauta;
import com.br.votacao.repository.PautaRepository;
import com.br.votacao.service.execptions.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PautaServiceImplTest {

    public static final String PAUTA_COM_NOME_JA_CADASTRADO = "Pauta com nome já cadastrado";
    @InjectMocks
    private PautaServiceImpl service;

    @Mock
    private PautaRepository repository;

    private Pauta pauta;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        criarPauta();
    }

    @Test
    void salvar() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(pauta);

        Pauta pautaTeste = service.salvar(pauta);

        Assertions.assertNotNull(pautaTeste);
        Assertions.assertEquals(Pauta.class, pautaTeste.getClass());
    }


    @Test
    void salvarJaExistePauta() {
        //Mockito.when(repository.findByNome(Mockito.any())).thenReturn(Optional.of(pauta));
        Mockito.when(repository.findByNome(Mockito.anyString()))
                .thenThrow(new BusinessException(PAUTA_COM_NOME_JA_CADASTRADO));

        try {
             service.salvar(pauta);
        } catch (Exception ex) {
            assertEquals(PAUTA_COM_NOME_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void findByIdTest() {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(pauta));

        Optional<Pauta> pautaTeste = service.findById(pauta.getId());

        Assertions.assertEquals(Pauta.class, pautaTeste.get().getClass());

    }

    @Test
    void deletarPauta() {

        Mockito.doNothing().when(repository).delete(Mockito.any());

        service.deletarPauta(pauta);

        Mockito.verify(repository, Mockito.times(1)).delete(Mockito.any());
    }

    private void criarPauta() {
        pauta = new Pauta("333", "teste 1", 0, 0);
    }
}