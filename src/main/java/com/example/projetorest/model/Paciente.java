package com.example.projetorest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nome;
    @Column
    private Integer idade;
    @Column
    private String email;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Refeicao> listaRefeicoes;

    // Construtores
    @Deprecated
    public Paciente() { }
    public Paciente(int id, String nome, Integer idade, String email, List<Refeicao> refeicoes) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.listaRefeicoes = refeicoes;
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

    public List<Refeicao> getRefeicoes() { return listaRefeicoes; }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.listaRefeicoes = refeicoes;
    }

    public void addRefeicao(Refeicao refeicao) {
        if (!this.listaRefeicoes.contains(refeicao)) {
            this.listaRefeicoes.add(refeicao);
            refeicao.setPaciente(this);
        }
    }


    public void removeRefeicao(Refeicao refeicao) {
        this.listaRefeicoes.remove(refeicao);
        refeicao.setPaciente(null);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", email='" + email + '\'' +
                '}';
    }
}
