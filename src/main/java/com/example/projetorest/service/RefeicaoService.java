package com.example.projetorest.service;

import com.example.projetorest.model.Paciente;
import com.example.projetorest.model.Refeicao;
import com.example.projetorest.repositories.RefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefeicaoService {

    @Autowired
    private final RefeicaoRepository refeicaoRepository;
    @Autowired
    private PacienteService pacienteService;

    public RefeicaoService(RefeicaoRepository refeicaoRepository, PacienteService pacienteService) {
        this.refeicaoRepository = refeicaoRepository;
        this.pacienteService = pacienteService;
    }
    // Criar uma nova refeição
    public void createRefeicao(Refeicao refeicao) {
        if (refeicao.getPaciente() != null) {
            Paciente paciente = pacienteService.getPacienteById(refeicao.getPaciente().getId());

            if (paciente != null) {
                if (paciente.getRefeicoes() == null) {
                    paciente.setRefeicoes(new ArrayList<>());
                }

                if (!paciente.getRefeicoes().contains(refeicao)) {
                    paciente.getRefeicoes().add(refeicao);
                }

                pacienteService.updatePaciente(paciente);
            }
        } else {
            refeicaoRepository.save(refeicao);
        }
    }


    // Recuperar todas as refeições
    public List<Refeicao> getAllRefeicoes() {
        return refeicaoRepository.findAll();
    }

    // Recuperar refeição por ID
    public Refeicao getRefeicaoById(int id) {

        return refeicaoRepository.findById(id).orElse(null);
    }

    // Atualizar uma refeição
    public void updateRefeicao(Refeicao refeicaoAtualizada) {
        refeicaoRepository.save(refeicaoAtualizada);
    }

    // Deletar uma refeição
    public void deleteRefeicao(int id) {
        refeicaoRepository.deleteById(id);
    }
}
