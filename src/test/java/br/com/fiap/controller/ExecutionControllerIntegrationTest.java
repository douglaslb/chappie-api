package br.com.fiap.controller;

import br.com.fiap.model.ExecucaoModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ExecutionControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve listar todas execuções e retornar status 200")
    public void shouldListAllExecutions() throws Exception {

        mvc.perform(get("/execucao")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Deve retornar uma execução pelo ID e com status 200")
    public void shouldFindExecutionById() throws Exception {

       ResultActions resultActions = mvc.perform(get("/execucao/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

       //deserializando o response para obter a data de execução para validar o json
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        ExecucaoModel response = objectMapper.readValue(contentAsString, ExecucaoModel.class);

        resultActions.andExpect(content().string("{\"id\":1,\"acao\":{\"id\":2,\"nome\":\"Pular\",\"descricao\":\"O RobÃ´ CHAPPiE deve ser capaz de pular\",\"ativo\":true},\"dataExecucao\":\""+ response.getdataExecucao() +"\"}"));

    }

    @Test
    @DisplayName("Deve salvar uma execução, retornar status 201 e Location no Header")
    public void shouldSaveExecution() throws Exception {

        mvc.perform(post("/execucao")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"acao\": { \"id\": 2 } }"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("Deve atualizar uma execução pelo id e retornar status 200")
    public void shouldUpdateExecution() throws Exception {

        mvc.perform(put("/execucao/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"acao\": { \"id\": 1 } }"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve deletar uma execucao pelo id e retornar status 204")
    public void shouldDeleteExecutionById() throws Exception {

        mvc.perform(delete("/execucao/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
