package br.com.ordensservico.aberturaordensservico.repository;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {
}