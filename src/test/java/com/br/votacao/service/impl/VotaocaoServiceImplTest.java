package com.br.votacao.service.impl;

import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Pauta;
import com.br.votacao.domain.Sessao;
import com.br.votacao.domain.Votacao;
import com.br.votacao.repository.SessaoRepository;
import com.br.votacao.repository.VotacaoRepository;
import com.br.votacao.service.execptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class VotaocaoServiceImplTest {

    public static final String ASSOCIADO_JA_VOLTOU = "Associado já voltou";
    public static final String SESSAO_ID = "1";
    public static final String CPF = "99999999999";
    public static final String VOTO = "sim";
    @InjectMocks
    private VotaocaoServiceImpl service;

    @Mock
    private SessaoServiceImpl sessaoService;

    @Mock
    private VotacaoRepository repository;

    @Mock
    private SessaoRepository sessaoRepository;

    private Votacao votacao;

    private Sessao sessao;

    private Associado associado;
    private Pauta pauta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inicarDados();
    }

    @Test
    void salvarSucesso() throws Exception {
        Mockito.when(sessaoRepository.findById(Mockito.any())).thenReturn(((Optional.of(sessao))));
        Mockito.when(repository.save(Mockito.any())).thenReturn(((votacao)));

        Votacao votacaoTeste = service.salvar(SESSAO_ID, CPF, VOTO);

        assertNotNull(votacaoTeste);
        assertEquals(Votacao.class, votacaoTeste.getClass());

    }

    @Test
    void salvarError() throws Exception {
        Mockito.when(repository.findByAssociado(Mockito.any()))
                .thenThrow(new BusinessException(ASSOCIADO_JA_VOLTOU));

        try {
            service.salvar(SESSAO_ID, CPF, VOTO);
        } catch (Exception ex) {
            assertEquals(ASSOCIADO_JA_VOLTOU, ex.getMessage());
        }
    }

    @Test
    void findAllTeste() {
        Mockito.when(repository.findAll()).thenReturn((List.of(votacao)));

        List<Votacao> listaVotacaoTeste = service.findAll();

        assertNotNull(listaVotacaoTeste);
        assertEquals(Votacao.class, listaVotacaoTeste.get(0).getClass());
    }

    public void inicarDados() {
        pauta = Pauta.builder()
                .id("1")
                .nome("teste-pauta")
                .build();

        sessao = Sessao.builder()
                .id("1")
                .pauta(pauta)
                .build();

        associado = Associado.builder()
                .id("1")
                .cpf("99999999999")
                .build();

        votacao = Votacao.builder()
                .id("1")
                .sessao(sessao)
                .associado(associado)
                .voto(true)
                .build();
    }
}