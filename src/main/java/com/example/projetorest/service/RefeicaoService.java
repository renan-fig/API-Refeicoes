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
    // todo: corrigir o método createRefeicao para gravar corretamente a refeicao no paciente
    // Criar uma nova refeição
    public void createRefeicao(Refeicao refeicao) {
        // Verifica se a refeição já tem um paciente associado
        if (refeicao.getPaciente() != null) {
            Paciente paciente = refeicao.getPaciente();

            // Inicializa a lista de refeições se estiver vazia
            if (paciente.getRefeicoes() == null) {
                paciente.setRefeicoes(new ArrayList<>());
            }

            // Adiciona a refeição à lista do paciente, se ainda não estiver
            if (!paciente.getRefeicoes().contains(refeicao)) {
                paciente.getRefeicoes().add(refeicao);
            }
            // Salva o paciente, garantindo que a refeição seja salva junto
            pacienteService.createPaciente(paciente);
        } else {
            // Caso não tenha um paciente, salva apenas a refeição
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
