package br.com.ordensservico.aberturaordensservico.controller;

import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.service.EquipamentoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody EquipamentoRequest equipamento) {

        Integer setorId = equipamento.getSetorId();
        
       if (setorId == null) {
            return ResponseEntity.badRequest().body("O setor deve ser informado.");
        }

        Optional<Equipamento> equipamentoOptional = equipamentoService.cadastrar(null, null, setorId);
             if (equipamentoOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("Setor não encontrado.");
                
             }
             return ResponseEntity.status(HttpStatus.CREATED)
             .body(equipamentoOptional.get());
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {
        return ResponseEntity.ok(equipamentoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(
                    equipamentoService.buscarPorId(id)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

 @PutMapping("/{id}")
public ResponseEntity<Equipamento> atualizar(
        @PathVariable Integer id,
        @RequestBody EquipamentoRequest request) {

    Equipamento equipamento = equipamentoService.atualizar(id, request);

    return ResponseEntity.ok(equipamento);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        try {
            equipamentoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}