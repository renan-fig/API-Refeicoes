package com.example.projetorest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@Table(name = "refeicao")
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String descricao;
    @Column(nullable = false)
    private int calorias;
    @Column(nullable = false)
    private int qtdGramas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @JsonBackReference
    private Paciente paciente;

    // Construtores
    @Deprecated
    public Refeicao() {
    }
    public Refeicao(String descricao, Integer calorias, int quantidade) {
        this.descricao = descricao;
        this.calorias = calorias;
        this.qtdGramas = quantidade;
    }

    public Refeicao(int id, String descricao, Integer calorias, int quantidade, Paciente paciente) {
        this.id = id;
        this.descricao = descricao;
        this.calorias = calorias;
        this.qtdGramas = quantidade;
        this.paciente = paciente;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        if (this.paciente != paciente) {
            this.paciente = paciente;
        }
    }

    public void removePaciente() {
        this.paciente = null;
    }

    @Override
    public String toString() {
        return "Refeicao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", calorias=" + calorias +
                ", qtdGramas=" + qtdGramas +
                '}';
    }
}
