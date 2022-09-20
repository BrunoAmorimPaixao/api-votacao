package com.br.votacao.service.impl;

import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Pauta;
import com.br.votacao.domain.Sessao;
import com.br.votacao.domain.Votacao;
import com.br.votacao.repository.SessaoRepository;
import com.br.votacao.repository.VotacaoRepository;
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

    public static final String SESSAO_ID = "1";
    public static final String CPF = "99999999999";
    public static final String VOTO = "sim";
    @InjectMocks
    private VotaocaoServiceImpl service;

    @Mock
    private SessaoServiceImpl sessaoService;

    @Mock
    private AssociadoServiceImpl  associadoService;

    @Mock
    private PautaServiceImpl pautaService;

    @Mock
    private VotacaoRepository repository;

    private Votacao votacao;

    private Sessao sessao;

    private Associado associado;
    private Pauta pauta;

    private Optional<Sessao> sessaoOptional;

    private  Optional<Associado> associadoOptional;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inicarDados();
    }

    @Test
    void salvarVotacao() throws Exception {
        Mockito.when(sessaoService.findById(Mockito.any())).thenReturn(((sessaoOptional)));
        Mockito.when(associadoService.findById(Mockito.any())).thenReturn(((associadoOptional)));
        Mockito.when(pautaService.salvar(Mockito.any())).thenReturn(((pauta)));
        Mockito.when(repository.save(Mockito.any())).thenReturn(((votacao)));

        Votacao votacaoTeste = service.salvar(SESSAO_ID, CPF, VOTO);

        assertNotNull(votacaoTeste);
        assertEquals(Votacao.class, votacaoTeste.getClass());

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

        sessaoOptional = Optional.of(sessao);

        associado = Associado.builder()
                .id("1")
                .cpf("99999999999")
                .build();

        associadoOptional = Optional.of(associado);

        votacao = Votacao.builder()
                .id("1")
                .sessao(sessao)
                .associado(associado)
                .voto(true)
                .build();
    }
}