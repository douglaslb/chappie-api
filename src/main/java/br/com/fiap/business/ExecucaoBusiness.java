package br.com.fiap.business;


import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Component
public class ExecucaoBusiness {

    @Value("${rest.exception.business.action-status}")
    private String actionStatus;

    @Autowired
    public AcaoRepository acaoRepository;

    public ExecucaoModel applyBusiness(ExecucaoModel execucao) throws ResponseBusinessException {
        verifyActionStatus(execucao.getAcao());

        ZonedDateTime currentDate = getCurrentDate(execucao);

        formatDateToString(currentDate, execucao);

        return execucao;
    }

    protected void verifyActionStatus(AcaoModel acaoModel) throws ResponseBusinessException {

        AcaoModel acao = acaoRepository.findById(acaoModel.getId()).get();

        if (!acao.getAtivo()) {
            throw new ResponseBusinessException(actionStatus);
        }
    }

    protected ZonedDateTime getCurrentDate(ExecucaoModel execucao) throws ResponseBusinessException {

        ZonedDateTime currentDate = ZonedDateTime.now();

        return currentDate;
    }

    protected void formatDateToString(ZonedDateTime currentDate, ExecucaoModel execucao) throws ResponseBusinessException {
        String formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH                                                                                                                                                                                                                                                                                                                                                                     :mm").format(currentDate);

        execucao.setdataExecucao(formattedDate);
    }

}
