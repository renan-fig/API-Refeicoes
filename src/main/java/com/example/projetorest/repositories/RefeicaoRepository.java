package com.example.projetorest.repositories;

import com.example.projetorest.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefeicaoRepository extends JpaRepository<Paciente, Integer> {
}
