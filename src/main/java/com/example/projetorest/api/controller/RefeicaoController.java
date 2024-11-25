package com.example.projetorest.api.controller;

import com.example.projetorest.model.Refeicao;
import com.example.projetorest.service.RefeicaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refeicoes")
public class RefeicaoController {
    // todo: melhorar documentação da api
    private final RefeicaoService refeicaoService;

    @Autowired
    public RefeicaoController(final RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    // Buscar refeicao por ID
    @Tag(name = "GET", description = "GET a information in the system")
    @GetMapping(value = "/refeicao/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Refeicao> getRefeicao(@RequestParam int id) {
        Refeicao refeicao = refeicaoService.getRefeicaoById(id);

        if (refeicao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(refeicao);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(refeicao);
    }

    // Buscar todas as refeicoes
    @Tag(name = "GET")
    @GetMapping("/all")
    public List<Refeicao> getRefeicoes() {
        return refeicaoService.getAllRefeicoes();
    }

    // Criar uma nova refeicao
    @Tag(name = "POST", description = "POST a register in the system")
    @PostMapping(value = "/refeicao", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> createRefeicao(@RequestBody Refeicao refeicao) {
        try {
            refeicaoService.createRefeicao(refeicao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Refeição criada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro na criação da refeição: " + e);
        }
    }

    // Deletar uma refeicao
    @Tag(name = "DELETE", description = "DELETE a register in the system")
    @DeleteMapping("/refeicao")
    public ResponseEntity<String> deleteRefeicao(@RequestParam int id) {
        Refeicao refeicao = refeicaoService.getRefeicaoById(id);

        if (refeicao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refeição não encontrada para DELETE");
        }
        refeicaoService.deleteRefeicao(id);
        return ResponseEntity.status(HttpStatus.OK).body("Refeição deletada com sucesso");
    }

    // Atualizar uma refeicao
    @Tag(name = "PUT")
    @PutMapping(value = "/refeicao", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> editRefeicao(@RequestBody Refeicao refeicao) {
        try {
            refeicaoService.updateRefeicao(refeicao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Refeição criada/atualizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro no update da refeição: " + e);
        }
    }
}
