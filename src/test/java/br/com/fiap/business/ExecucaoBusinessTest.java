package br.com.fiap.business;

import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.ExecucaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import br.com.fiap.repository.AcaoRepository;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@SpringBootTest
public class ExecucaoBusinessTest {

    @InjectMocks
    public ExecucaoBusiness execucaoBusiness;

    @Mock
    public AcaoRepository acaoRepository;

     @Mock
    public ExecucaoRepository execucaoRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVerifyActionStatusWhenTrue() throws ResponseBusinessException {

        // GIVEN
        AcaoModel acao = new AcaoModel(1, "Ação 1", "Descrição 1", true);

        // WHEN
        Mockito.when(acaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(acao));
        execucaoBusiness.verifyActionStatus(acao);
    }

    //JUNIT5 utiliza o método assertThrows para validar erros, não existe mais o parametro expect dentro do @Test
    @Test()
    public void testVerifyActionStatusWhenFalse() {

        // GIVEN
        AcaoModel acao = new AcaoModel(1, "Ação 1", "Descrição 1", false);

        // WHEN
        Mockito.when(acaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(acao));

        //THEN
        Assertions.assertThrows(ResponseBusinessException.class, () -> {
            execucaoBusiness.verifyActionStatus(acao);
        });
    }

    @Test()
    public void testGetCurrentDate() throws ResponseBusinessException {

        ZonedDateTime actual = execucaoBusiness.getCurrentDate();

        ZonedDateTime expect = ZonedDateTime.now();

        //Aqui eu estou validando somente a data para não haver conflito de segundos e milisegundos e precisar
        //elaborar uma estratégia para mockar o ZoneDateTime.now()
        Assertions.assertEquals(
                actual.format(DateTimeFormatter.ofPattern("yyyy-mm-dd")),
                expect.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"))
        );

    }

    @Test
    public void testFormatDateToString() throws ResponseBusinessException {
        ExecucaoModel execucao = new ExecucaoModel(1, null, null);
        ZonedDateTime currentDate = ZonedDateTime.now();

        execucaoBusiness.formatDateToString(currentDate, execucao);
    }

    @Test
	public void testApplyBusiness() throws ResponseBusinessException {

		// GIVEN
        AcaoModel acao = new AcaoModel(1, "Ação 1", "Descrição 1", true);
        ExecucaoModel execucao = new ExecucaoModel(1, acao, "12/11/2020");

        ExecucaoModel expected = new ExecucaoModel(1, acao, ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")));

		// WHEN
		Mockito.when(acaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(acao));
		ExecucaoModel actual = execucaoBusiness.applyBusiness(execucao);

		// THEN
		Assertions.assertEquals(expected.toString(), actual.toString());
	}

}
