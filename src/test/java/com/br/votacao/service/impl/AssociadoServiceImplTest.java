package com.br.votacao.service.impl;

import com.br.votacao.domain.Associado;
import com.br.votacao.repository.AssociadoRepository;
import com.br.votacao.service.execptions.BusinessException;
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
    public static final String NAO_EXISTE_ESSE_ASSOCIADO_CADASTRADO = "Nao existe esse associado cadastrado";
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
        Mockito.when(repository.findByCpf(Mockito.anyString())).thenReturn((Optional.of(associado)));

        Optional<Associado> associadoCpf = service.findByCpf(associado.getCpf());

        Assertions.assertEquals(Associado.class, associadoCpf.get().getClass());
    }

    @Test
    void findByCpfNaoCadastrado() {
        Mockito.when(repository.findByCpf(Mockito.anyString())).thenThrow(new BusinessException((NAO_EXISTE_ESSE_ASSOCIADO_CADASTRADO)));
        try{
            service.findByCpf(associado.getCpf());
        } catch (Exception ex) {
            Assertions.assertEquals(NAO_EXISTE_ESSE_ASSOCIADO_CADASTRADO, ex.getMessage());
        }
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