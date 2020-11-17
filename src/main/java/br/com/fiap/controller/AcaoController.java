package br.com.fiap.controller;

import br.com.fiap.model.AcaoModel;
import br.com.fiap.repository.AcaoRepository;
import br.com.fiap.repository.ExecucaoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/acao")
public class AcaoController {

    @Autowired
    public AcaoRepository acaoRepository;

    @Autowired
    public ExecucaoRepository execucaoRepository;

    @GetMapping()
	@ApiOperation(value = "Retorna uma lista de ações")
	public ResponseEntity<List<AcaoModel>> findAll(Model model) {

 		List<AcaoModel> acoes = acaoRepository.findAll();
		return ResponseEntity.ok(acoes);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna uma ação a partir do identificador")
	public ResponseEntity<AcaoModel> findById(@PathVariable("id") long id) {

		AcaoModel acao = acaoRepository.findById(id).get();
		return ResponseEntity.ok(acao);
	}

}
