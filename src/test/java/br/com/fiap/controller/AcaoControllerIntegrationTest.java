package br.com.fiap.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AcaoControllerIntegrationTest {

    @Autowired
	private MockMvc mvc;

    	@Test
	@DisplayName("Deve listar todas ações e retornar status 200")
	public void shouldListAllActions() throws Exception {

		mvc.perform(get("/acao")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Deve retornar uma ação pelo ID e com status 200")
	public void shouldFindActionById() throws Exception {

		mvc.perform(get("/acao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"id\":1,\"nome\":\"Andar\",\"descricao\":\"O RobÃ´ CHAPPiE deve ser capaz de andar\",\"ativo\":true}"));
	}

	@Test
    @DisplayName("Deve salvar uma ação, retornar status 201 e Location no Header")
    public void shouldSaveAction() throws Exception {

        mvc.perform(post("/acao")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Pensar\",\"descricao\":\"O Robô CHAPPiE deve ser capaz de pensar\",\"ativo\":false}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("Deve atualizar uma ação pelo id e retornar status 200")
    public void shouldUpdateAction() throws Exception {

        mvc.perform(put("/acao/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Voar\",\"descricao\":\"O Robô CHAPPiE deve ser capaz de voar\",\"ativo\":false}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve deletar uma ação pelo id e retornar status 204")
    public void shouldDeleteActionById() throws Exception {

        mvc.perform(delete("/acao/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Não deve deletar uma ação associada a uma execucao e retornar status 500")
    public void shouldNotDeleteActionByIdWithConstraints() throws Exception {

        mvc.perform(delete("/acao/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
