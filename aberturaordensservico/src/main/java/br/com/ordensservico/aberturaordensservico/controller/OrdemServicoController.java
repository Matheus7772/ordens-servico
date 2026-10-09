package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService){
        this.ordemServicoService = ordemServicoService;
    }

@PostMapping
    public ResponseEntity<OrdemServico> cadastrar(
        @Valid @RequestBody OrdemServicoRequest ordemServicoRequest) {

    return ordemServicoService.cadastrar(ordemServicoRequest)
            .map(ordemServico -> ResponseEntity.status(HttpStatus.CREATED).body(ordemServico))
            .orElseGet(() -> ResponseEntity.notFound().build());
}




    
    
    
}
