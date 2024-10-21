package com.example.projetorest.service;

import com.example.projetorest.model.Refeicao;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RefeicaoService {
    private List<Refeicao> refeicoes = new ArrayList<>();

    public RefeicaoService() {
        refeicoes = new ArrayList<>();

        Refeicao refeicao1 = new Refeicao(1, "Ovo mexido", 151, 100);
        Refeicao refeicao2 = new Refeicao(2, "Frango com batata doce", 420, 450);
        Refeicao refeicao3 = new Refeicao(3, "Panqueca de espinafre", 347, 500);
        Refeicao refeicao4 = new Refeicao(4, "Macarrão a bolanhesa", 576, 340);

        refeicoes.addAll(Arrays.asList(refeicao1, refeicao2, refeicao3, refeicao4));

    }

    // Criar uma nova refeição
    public Refeicao createRefeicao(Refeicao refeicao) {
        refeicoes.add(refeicao);
        return refeicao;
    }

    // Buscar todas as refeições
    public List<Refeicao> getAllRefeicoes() {
        return refeicoes;
    }

    // Buscar refeição por ID
    public Refeicao getRefeicaoById(int id) {
        for (Refeicao refeicao : refeicoes) {
            if (id == refeicao.getId()) {
                return refeicao;
            }
        }
        return null;
    }

    // Atualizar uma refeição
    public void updateRefeicao(Refeicao refeicaoAtualizada) {
        for (Refeicao refeicao : refeicoes) {
            if (refeicao.getId() == refeicaoAtualizada.getId()) {
                refeicoes.remove(refeicao);
                Refeicao newrefeicao = new Refeicao(refeicaoAtualizada.getId(), refeicaoAtualizada.getDescricao(), refeicaoAtualizada.getCalorias(), refeicaoAtualizada.getQtdGramas());
                refeicoes.add(newrefeicao);
            }
        }
    }

    // Deletar uma refeição
    public void deleteRefeicao(int id) {
        for (Refeicao refeicao : refeicoes) {
            if (id == refeicao.getId()) {
                refeicoes.remove(refeicao);
            }
        }
    }
}
