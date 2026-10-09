package br.com.ordensservico.aberturaordensservico.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;

@Service 
public class OrdemServicoService {

    private final EquipamentoRepository equipamentoRepository;
    private final OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoService(
        EquipamentoRepository equipamentoRepository,
        OrdemServicoRepository ordemServicoRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.ordemServicoRepository = ordemServicoRepository;
    }

    public Optional <OrdemServico> cadastrar(OrdemServicoRequest ordemServicoRequest) {
        
        Optional<Equipamento> equipamento = equipamentoRepository.findById(ordemServicoRequest.getEquipamentoId());

        if (equipamento.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = new OrdemServico();

        ordemServico.setDescricao(ordemServicoRequest.getDescricao());
        ordemServico.setDataAbertura(LocalDateTime.now());
        ordemServico.setEquipamento(equipamento.get());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }
 
    
}
