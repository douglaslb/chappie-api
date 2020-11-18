package br.com.fiap.business;


import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExecucaoBusiness {

    @Value("${rest.exception.business.action-status}")
    private String actionStatus;

    @Autowired
    public AcaoRepository acaoRepository;

    public ExecucaoModel applyBusiness(ExecucaoModel execucao) throws ResponseBusinessException {
        verifyActionStatus(execucao.getAcao());

        return execucao;
    }

    protected void verifyActionStatus(AcaoModel acaoModel) throws ResponseBusinessException {

        AcaoModel acao = acaoRepository.findById(acaoModel.getId()).get();

        if(!acao.getAtivo()) {
            throw new ResponseBusinessException(actionStatus);
        }
    }


}
