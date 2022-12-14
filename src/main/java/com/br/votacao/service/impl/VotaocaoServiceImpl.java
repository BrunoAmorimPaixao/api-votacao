package com.br.votacao.service.impl;

import com.br.votacao.client.CpfClient;
import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Sessao;
import com.br.votacao.domain.Votacao;
import com.br.votacao.repository.VotacaoRepository;
import com.br.votacao.service.AssociadoService;
import com.br.votacao.service.PautaService;
import com.br.votacao.service.SessaoService;
import com.br.votacao.service.VotaocaoService;
import com.br.votacao.service.execptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotaocaoServiceImpl implements VotaocaoService {

    private static final Logger log = LoggerFactory.getLogger(VotaocaoServiceImpl.class);
    public static final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
    public static final String CPF_INVALIDO = "CPF invalido";
    public static final String ASSOCIADO_JA_VOLTOU = "Associado já voltou";

    private final AssociadoService associadoService;

    private final VotacaoRepository repository;

    private final SessaoService sessaoService;

    private final PautaService pautaService;

    private final CpfClient cpfClient;

    private Votacao votacao;

    private Optional<Associado> associado;

    private Optional<Sessao> sessao;

    @Override
    public Optional<Votacao> buscarPorAssociado(Associado associado) {
        log.info("Buscando pelo CPF: {}", associado);
        Votacao votacaoAssociadoSelecionado = this.repository.findByAssociado(associado);
        if(votacaoAssociadoSelecionado != null && votacaoAssociadoSelecionado.isVoto()) {
            throw new BusinessException(ASSOCIADO_JA_VOLTOU);
        }
        return Optional.ofNullable(votacaoAssociadoSelecionado);

    }

    public Votacao salvar(String sessaoId, String cpf, String voto) throws Exception {
        //log.info("Salvando votacao: {}", votacao.toString());

        votacao = new Votacao();
        sessao = sessaoService.findById(sessaoId);

        //incrementar o id de votacao
        int idVotacao = findAll().size() + 1;
        votacao.setId(Integer.toString(idVotacao));
        votacao.setSessao(sessao.get());
        boolean isVoto = voto.equals("sim") ? true : false;
        votacao.setVoto(isVoto);
        //TODO: o servico nao esta funcionando
        //validarCpfClient(cpf);
        validarCpf(cpf);
        buscarPorAssociado(votacao.getAssociado());
        validarSessao(votacao.getSessao().getId());

        repository.save(votacao);
        pautaService.salvar(votacao.getSessao().getPauta());

        return votacao;
    }

    @Override
    public List<Votacao> findAll() {
        return repository.findAll();
    }

    public void validarSessao(String id) {
        sessaoService.findById(id);
    }

    public void validarCpfClient(String cpf){
        if (cpfClient.buscaCpfId(cpf).equals(UNABLE_TO_VOTE)){
            throw new BusinessException(CPF_INVALIDO);
        }
    }

    public void validarCpf(String cpf) {
        associado = associadoService.findByCpf(cpf);
    }
}
