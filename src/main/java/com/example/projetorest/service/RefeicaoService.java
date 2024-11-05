package com.example.projetorest.service;

import com.example.projetorest.model.Paciente;
import com.example.projetorest.model.Refeicao;
import com.example.projetorest.repositories.RefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefeicaoService {

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    // Criar uma nova refeição
    public void createRefeicao(Refeicao refeicao) {
        //refeicaoRepository.save(refeicao);
    }

    // Recuperar todas as refeições
    public List<Paciente> getAllRefeicoes() {
        return refeicaoRepository.findAll();
    }

    // Recuperar refeição por ID
    public Paciente getRefeicaoById(int id) {
        return refeicaoRepository.findById(id).orElse(null);
    }

    // Atualizar uma refeição
    public void updateRefeicao(Refeicao refeicaoAtualizada) {
        //refeicaoRepository.save(refeicaoAtualizada);
    }

    // Deletar uma refeição
    public void deleteRefeicao(int id) {
        refeicaoRepository.deleteById(id);
    }
}
