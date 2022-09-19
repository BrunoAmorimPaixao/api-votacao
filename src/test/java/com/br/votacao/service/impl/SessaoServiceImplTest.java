package com.br.votacao.service.impl;

import com.br.votacao.domain.Pauta;
import com.br.votacao.domain.Sessao;
import com.br.votacao.repository.SessaoRepository;
import com.br.votacao.service.execptions.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

class SessaoServiceImplTest {

    public static final String SESSAO_NAO_EXISTE = "Sessão não existe";
    @InjectMocks
    private SessaoServiceImpl service;

    @Mock
    private SessaoRepository repository;

    private Sessao sessao;

    private Pauta pauta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        criarSessao();
    }

    @Test
    void salvar() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(sessao);

        Sessao sessaoSalvarTeste = service.salvar(sessao);

        Assertions.assertNotNull(sessaoSalvarTeste);
        Assertions.assertEquals(Sessao.class, sessaoSalvarTeste.getClass());
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(sessao));

        Optional<Sessao> sessaoTeste = service.findById(sessao.getId());

        Assertions.assertNotNull(sessaoTeste);
        Assertions.assertEquals(Sessao.class, sessaoTeste.get().getClass());
    }

    @Test
    void findByIdSessaoNaoExiste() {
        Mockito.when(repository.findById(Mockito.any()))
                .thenThrow(new BusinessException(SESSAO_NAO_EXISTE));
        try {
            service.findById(sessao.getId());
        } catch (Exception ex) {
            Assertions.assertEquals(SESSAO_NAO_EXISTE, ex.getMessage());
        }
    }

    @Test
    void deletarSessao() {
        Mockito.doNothing().when(repository).delete(Mockito.any());

        service.deletarSessao(sessao);

        Mockito.verify(repository, Mockito.times(1)).delete(Mockito.any());

    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(List.of(sessao));

        List<Sessao> listSessaoTest = service.findAll();

        Assertions.assertEquals(Sessao.class, listSessaoTest.get(0).getClass());
    }

    private void criarSessao() {

        pauta = new Pauta("1", "pauta 1", 0, 0);
        sessao = new Sessao("1", null, null, pauta, true, true);

        /*
        pauta = Pauta.builder()
                .id("1")
                .nome("teste-pauta")
                .build();
        sessao = Sessao.builder()
                .id("1")
                .pauta(pauta)
                .build();

         */

    }

}



