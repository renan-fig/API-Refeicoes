package com.example.projetorest.service;

import com.example.projetorest.model.Paciente;
import com.example.projetorest.model.Refeicao;
import com.example.projetorest.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    // Criar um novo paciente
    public void createPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    // Buscar todos os pacientes
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    // Buscar paciente por ID
    public Paciente getPacienteById(int id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    // Atualizar um paciente
    public void updatePaciente(Paciente pacienteAtualizado) {
        pacienteRepository.save(pacienteAtualizado);
    }

    // Deletar um paciente
    public void deletePaciente(int id) {
        pacienteRepository.deleteById(id);
    }

    // Adicionar refeição ao paciente
    public void addRefeicaoToPaciente(int pacienteId, Refeicao refeicao) {
        Paciente paciente = getPacienteById(pacienteId);
        if (paciente != null) {
            paciente.getRefeicoes().add(refeicao);
            pacienteRepository.save(paciente);
        }
    }

    // Atualiza refeição do paciente
    public void updateRefeicaoFromPaciente(int pacienteId, Refeicao refeicaoAtualizada) {
        Paciente paciente = getPacienteById(pacienteId);
        if (paciente != null) {
            List<Refeicao> refeicoesPaciente = paciente.getRefeicoes();
            refeicoesPaciente.stream()
                    .filter(r -> r.getId() == refeicaoAtualizada.getId())
                    .findFirst()
                    .ifPresent(refeicoesPaciente::remove);
            refeicoesPaciente.add(refeicaoAtualizada);
            pacienteRepository.save(paciente);
        }
    }

    // Remover refeição do paciente
    public void removeRefeicaoFromPaciente(int pacienteId, int refeicaoId) {
        Paciente paciente = getPacienteById(pacienteId);
        if (paciente != null) {
            paciente.getRefeicoes().removeIf(refeicao -> refeicao.getId() == refeicaoId);
            pacienteRepository.save(paciente);
        }
    }
}
