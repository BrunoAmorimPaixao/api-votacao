package com.br.votacao.service.impl;

import com.br.votacao.domain.Associado;
import com.br.votacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;


class AssociadoServiceImplTest {

    public static final String ID = "1";
    public static final String CPF = "99999999999";
    @InjectMocks
    private AssociadoServiceImpl service;
    @Mock
    private AssociadoRepository repository;
    private Associado associado;

    @BeforeEach
    void setUp() {
        //inicar os mocks
        MockitoAnnotations.openMocks(this);
        criarAssossiado();
    }

    @Test
    void salvar() {
        Mockito.when(repository.save(Mockito.any())).thenReturn((associado));

        Associado associadoTeste = service.salvar(associado);

        Assertions.assertNotNull(associadoTeste);
        Assertions.assertEquals(Associado.class, associadoTeste.getClass());
    }

    @Test
    void findByCpfSucessoTeste() {
        Mockito.when(repository.findByCpf(Mockito.anyString())).thenReturn((associado));

        Optional<Associado> associadoCpf = service.findByCpf(associado.getCpf());

        Assertions.assertEquals(Associado.class, associadoCpf.get().getClass());
    }

    @Test
    void findByIdTeste() {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(associado));

        Optional<Associado> associado1 = service.findById(associado.getId());

        Assertions.assertEquals(Associado.class, associado1.get().getClass());
    }

    @Test
    void deletarAssociadoSucesso() {

        Mockito.doNothing().when(repository).deleteById(Mockito.any());
        service.deletarAssociado(associado);

        Mockito.verify(repository, Mockito.times(1)).delete(Mockito.any());
    }

    private void criarAssossiado() {
        associado = new Associado(ID, CPF);
    }
}