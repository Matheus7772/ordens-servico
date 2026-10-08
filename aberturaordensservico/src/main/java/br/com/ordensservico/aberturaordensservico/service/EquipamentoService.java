package br.com.ordensservico.aberturaordensservico.service;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

    public EquipamentoService(
            EquipamentoRepository equipamentoRepository,
            SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Equipamento cadastrar(Equipamento equipamento) {

        if (equipamento.getNome() == null
               ) {
            throw new RuntimeException("O setor deve ser informado.");
        }

        Optional<Setor> setor = setorRepository.findById(
                equipamento.getSetor().getId()
        );

        if (setor.isEmpty()) {
            throw new RuntimeException("Setor não encontrado.");
        }

        equipamento.setSetor(setor.get());

        return equipamentoRepository.save(equipamento);
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Equipamento buscarPorId(Integer id) {

        Optional<Equipamento> equipamento =
                equipamentoRepository.findById(id);

        if (equipamento.isEmpty()) {
            throw new RuntimeException("Equipamento não encontrado.");
        }

        return equipamento.get();
    }

    public Equipamento atualizar(Integer id, Equipamento equipamento) {

        Equipamento equipamentoExistente = buscarPorId(id);

        equipamentoExistente.setNome(equipamento.getNome());
        equipamentoExistente.setNumeroPatrimonio(equipamento.getNumeroPatrimonio());

        if (equipamento.getNome() == null ||
                equipamento.getId() == null) {
            throw new RuntimeException("O setor deve ser informado.");
        }

        Optional<Setor> setor = setorRepository.findById(
                equipamento.getSetor().getId()
        );

        if (setor.isEmpty()) {
            throw new RuntimeException("Setor não encontrado.");
        }

        equipamentoExistente.setSetor(setor.get());

        return equipamentoRepository.save(equipamentoExistente);
    }

    public void excluir(Integer id) {

        Equipamento equipamento = buscarPorId(id);

        equipamentoRepository.delete(equipamento);
    }
}