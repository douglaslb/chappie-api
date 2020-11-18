package br.com.fiap.controller;


import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.ExecucaoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/execucao")
public class ExecucaoController {

    @Autowired
    public ExecucaoRepository execucaoRepository;



    @GetMapping()
	@ApiOperation(value = "Retorna uma lista de execuções de ação")
	public ResponseEntity<List<ExecucaoModel>> findAll(Model model) {

 		List<ExecucaoModel> execucoes = execucaoRepository.findAll();
		return ResponseEntity.ok(execucoes);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna uma execução a partir do identificador")
	public ResponseEntity<ExecucaoModel> findById(@PathVariable("id") long id) {

		ExecucaoModel execucao = execucaoRepository.findById(id).get();
		return ResponseEntity.ok(execucao);
	}

	@PostMapping()
	@ApiOperation(value = "Salva uma nova execução")
	public ResponseEntity save(@RequestBody @Valid ExecucaoModel execucaoModel)  {


		execucaoRepository.save(execucaoModel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(execucaoModel.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}
