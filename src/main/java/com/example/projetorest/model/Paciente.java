package com.example.projetorest.model;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Paciente {
    private int id;
    private String nome;
    private Integer idade;
    private String email;
    private List<Refeicao> refeicoes = new ArrayList<>();

    // Construtores
    public Paciente() { }
    public Paciente(int id, String nome, Integer idade, String email) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Refeicao> getRefeicoes() { return refeicoes; }

    public void setRefeicoes(List<Refeicao> refeicoes) { this.refeicoes = refeicoes; }

    public void addRefeicao(Refeicao refeicao) {
        this.refeicoes.add(refeicao);
    }

    public void removeRefeicao(Refeicao refeicao) {
        this.refeicoes.remove(refeicao);
    }
}
