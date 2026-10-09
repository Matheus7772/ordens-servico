package br.com.ordensservico.aberturaordensservico.service;

import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
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

    public Optional <Equipamento> cadastrar(String nome, String numeroPatrimonio, Integer setorId) {
            Optional<Setor> setor = setorRepository.findById(setorId);

            if (setor.isEmpty()) {
                return Optional.empty();
            }

            Equipamento equipamento = new Equipamento();
            equipamento.setNome(nome);
            equipamento.setNumeroPatrimonio(numeroPatrimonio);
            equipamento.setSetor(setor.get());
            
            return Optional.of(equipamentoRepository.save(equipamento));
       
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

   public Equipamento atualizar(Integer id, EquipamentoRequest request) {
    Equipamento equipamento = equipamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

    Setor setor = setorRepository.findById(request.getSetorId())
            .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

    equipamento.setNome(request.getNome());
    equipamento.setNumeroPatrimonio(request.getNumeroPatrimonio());
    equipamento.setSetor(setor);

    return equipamentoRepository.save(equipamento);
}

    public void excluir(Integer id) {

        Equipamento equipamento = buscarPorId(id);

        equipamentoRepository.delete(equipamento);
    }


}