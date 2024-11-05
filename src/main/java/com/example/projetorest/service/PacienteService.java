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
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

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
//    public void addRefeicaoToPaciente(int pacienteId, Refeicao refeicao) {
//        Paciente paciente = getPacienteById(pacienteId);
//        if (paciente != null) {
//            // Persiste a refeição antes de associá-la ao paciente
//            refeicao.setPaciente(paciente); // associa o paciente à refeição
//            refeicaoService.createRefeicao(refeicao); // salva a refeição
//
//            paciente.getRefeicoes().add(refeicao); // adiciona a refeição persistida à lista do paciente
//            pacienteRepository.save(paciente); // salva o paciente com a refeição já associada
//        }
//    }

    // Atualiza refeição do paciente
    public void updateRefeicaoFromPaciente(int pacienteId, Refeicao refeicaoAtualizada) {
        Paciente paciente = getPacienteById(pacienteId);
        if (paciente != null) {
            List<Refeicao> refeicoesPaciente = paciente.getRefeicoes();
            for (int i = 0; i < refeicoesPaciente.size(); i++) {
                Refeicao refeicao = refeicoesPaciente.get(i);
                if (refeicao.getId() == refeicaoAtualizada.getId()) {
                    refeicoesPaciente.remove(i);
                    break;
                }
            }
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
