package com.example.projetorest.api.controller;

import com.example.projetorest.model.Paciente;
import com.example.projetorest.model.Refeicao;
import com.example.projetorest.service.PacienteService;
import com.example.projetorest.service.RefeicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;
    private final RefeicaoService refeicaoService;

    @Autowired
    public PacienteController(final PacienteService pacienteService, final RefeicaoService refeicaoService) {
        this.pacienteService = pacienteService;
        this.refeicaoService = refeicaoService;
    }

    // Buscar paciente por ID
    @Tag(name = "GET")
    @Operation(summary = "Get patient by ID", description = "Fetch a patient by their unique ID")
    @GetMapping(value = "/paciente/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Paciente> getPaciente(@RequestParam int id) {
        Paciente paciente = pacienteService.getPacienteById(id);

        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(paciente);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paciente);
    }

    // Buscar todos os pacientes
    @Tag(name = "GET")
    @GetMapping("/all")
    public List<Paciente> getPacientes() {
        return pacienteService.getAllPacientes();
    }

    // Criar um novo paciente
    @Tag(name = "POST")
    @PostMapping(value = "/paciente", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> createPaciente(@RequestBody Paciente paciente) {
        try {
            pacienteService.createPaciente(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Paciente criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro na criação do paciente: " + e);
        }
    }

    // Deletar um paciente
    @Tag(name = "DELETE")
    @DeleteMapping("/paciente")
    public ResponseEntity<String> deletePaciente(@RequestParam int id) {
        Paciente paciente = pacienteService.getPacienteById(id);

        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado para DELETE");
        }
        pacienteService.deletePaciente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado com sucesso");
    }

    // Atualizar um paciente
    @Tag(name = "PUT")
    @PutMapping(value = "/paciente", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> editPaciente(@RequestBody Paciente paciente) {
        try {
            pacienteService.updatePaciente(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Paciente criado/atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro no update do paciente: " + e);
        }
    }

    // Adicionar refeição ao paciente
    @Tag(name = "POST")
    @PostMapping("/paciente/{pacienteId}/refeicao")
    public ResponseEntity<String> addRefeicaoToPaciente(
            @PathVariable Integer pacienteId, @RequestBody Refeicao refeicao) {
        Paciente paciente = pacienteService.getPacienteById(pacienteId);

        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }

        pacienteService.addRefeicaoToPaciente(pacienteId, refeicao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Refeição adicionada ao paciente com sucesso");
    }

    // Atualizar refeição do paciente
    @Tag(name = "PUT")
    @PutMapping("/paciente/{pacienteId}/refeicao/{refeicaoId}")
    public ResponseEntity<String> updateRefeicaoForPaciente(
            @PathVariable Integer pacienteId, @PathVariable Integer refeicaoId, @RequestBody Refeicao refeicaoAtualizada) {

        Paciente paciente = pacienteService.getPacienteById(pacienteId);

        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }

        Refeicao refeicao = refeicaoService.getRefeicaoById(refeicaoId);

        if (refeicao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refeição não encontrada para este paciente");
        }

        pacienteService.updateRefeicaoFromPaciente(pacienteId, refeicaoAtualizada);

        return ResponseEntity.status(HttpStatus.OK).body("Refeição atualizada com sucesso");
    }

    // Remover refeição do paciente
    @Tag(name = "DELETE")
    @DeleteMapping("/paciente/{pacienteId}/refeicao/{refeicaoId}")
    public ResponseEntity<String> removeRefeicaoFromPaciente(
            @PathVariable Integer pacienteId, @PathVariable Integer refeicaoId) {
        Paciente paciente = pacienteService.getPacienteById(pacienteId);

        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }

        // Remove a refeição do paciente
        pacienteService.removeRefeicaoFromPaciente(pacienteId, refeicaoId);
        return ResponseEntity.status(HttpStatus.OK).body("Refeição removida do paciente com sucesso");
    }
}
