package com.example.projetorest.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Refeicao {
    private int id;
    private String descricao;
    private int calorias;
    private int qtdGramas;

    // Construtores
    public Refeicao() {
    }
    public Refeicao(int id, String descricao, Integer calorias, int quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.calorias = calorias;
        this.qtdGramas = quantidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getCalorias() { return calorias; }
    public void setCalorias(int calorias) { this.calorias = calorias; }

    public int getQtdGramas() {
        return qtdGramas;
    }

    public void setQtdGramas(int qtdGramas) {
        this.qtdGramas = qtdGramas;
    }

}
