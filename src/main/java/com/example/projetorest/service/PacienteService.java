package com.example.projetorest.service;

import com.example.projetorest.model.Paciente;
import com.example.projetorest.model.Refeicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PacienteService {
    @Autowired
    private RefeicaoService refeicaoService;

    private List<Paciente> pacientes;
    private List<Refeicao> refeicoes = new ArrayList<>();
    List<Refeicao> refeicoesSistema;



    public PacienteService() {
        pacientes = new ArrayList<>();

        Paciente paciente1 = new Paciente(1, "Joãozin", 21, "joao_camisa10@gmail.com");
        Paciente paciente2 = new Paciente(2, "Renan", 24, "renan@gmail.com");
        Paciente paciente3 = new Paciente(3, "Matthew", 22, "m_araujo@gmail.com");
        Paciente paciente4 = new Paciente(4, "Dorea", 26, "dorea_jp@gmail.com");

        pacientes.addAll(Arrays.asList(paciente1, paciente2, paciente3, paciente4));
    }
    // Criar um novo paciente
    public void createPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    // Buscar todos os pacientes
    public List<Paciente> getAllPacientes() {
        return pacientes;
    }

    // Buscar paciente por ID
    public Paciente getPacienteById(int id) {
        for (Paciente paciente : pacientes) {
            if (id == paciente.getId()) {
                return paciente;
            }
        }
        return null;
    }

    // Atualizar um paciente
    public void updatePaciente(Paciente pacienteAtualizado) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == pacienteAtualizado.getId()) {
                pacientes.remove(paciente);
                Paciente newPaciente = new Paciente(pacienteAtualizado.getId(), pacienteAtualizado.getNome(), paciente.getIdade(), paciente.getEmail());
                pacientes.add(newPaciente);
            }
        }
    }

    // Deletar um paciente
    public void deletePaciente(int id) {
        for (Paciente paciente : pacientes) {
            if (id == paciente.getId()) {
                pacientes.remove(paciente);
            }
        }
    }

    // Adicionar refeição ao paciente
    public Paciente addRefeicaoToPaciente(int pacienteId, Refeicao refeicao) {
        Paciente paciente = getPacienteById(pacienteId);
        refeicoesSistema = refeicaoService.getAllRefeicoes();

        if (paciente != null) {
            refeicao.setId(refeicoesSistema.size() + 1);
            paciente.addRefeicao(refeicao);
            refeicoes.add(refeicao);
            refeicoesSistema.add(refeicao);

            return paciente;
        }
        return null;
    }

    // Atualiza refeição do paciente
    public Paciente updateRefeicaoFromPaciente(int pacienteId, Refeicao refeicao) {
        Paciente paciente = getPacienteById(pacienteId);

        if (paciente != null) {
            List<Refeicao> refeicoesPaciente = paciente.getRefeicoes();
            refeicoesSistema = refeicaoService.getAllRefeicoes();

            for (Refeicao refeicaoPaciente : refeicoesPaciente) {
                if (refeicao.getId() == refeicaoPaciente.getId()) {
                    refeicoesPaciente.remove(refeicaoPaciente);
                    refeicoesSistema.remove(refeicaoPaciente);
                    refeicoesPaciente.add(refeicao);
                    refeicoesSistema.add(refeicao);
                }
            }
            return paciente;
        }
        return null;
    }

    // Remover refeição do paciente
    public Paciente removeRefeicaoFromPaciente(int pacienteId, int refeicaoId) {
        Paciente paciente = getPacienteById(pacienteId);

        if (paciente != null) {
            List<Refeicao> refeicoesPaciente = paciente.getRefeicoes();
            refeicoesSistema = refeicaoService.getAllRefeicoes();

            for (int i = 0; i < refeicoesPaciente.size(); i++) {
                Refeicao refeicao = refeicoesPaciente.get(i);
                if (refeicao.getId() == refeicaoId) {
                    refeicoesPaciente.remove(i);
                    refeicoesSistema.remove(refeicao);
                    return paciente;
                }
            }
        }
        return null;
    }
}
