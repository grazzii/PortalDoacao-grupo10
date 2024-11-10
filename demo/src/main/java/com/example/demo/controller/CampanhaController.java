package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Campanha;
import com.example.demo.repository.CampanhaRepo;

@RestController
@RequestMapping("/api/campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaRepo campanhaRepo;

    @GetMapping
    public Iterable<Campanha> listarTodasCampanhas() {
        return campanhaRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campanha> buscarCampanhaPorId(@PathVariable Long id) {
        return campanhaRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public ResponseEntity<Campanha> criarCampanha(@RequestBody Campanha novaCampanha) {
        Campanha campanhaSalva = campanhaRepo.save(novaCampanha);
        return ResponseEntity.status(HttpStatus.CREATED).body(campanhaSalva);
    }

    @PutMapping("/{id}")
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<String> updateValorArrecadado(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        System.out.println("Requisição PUT recebida para atualizar a campanha com ID: " + id);
        System.out.println("Dados recebidos no body: " + updates);
    
        try {
            Optional<Campanha> campanhaOptional = campanhaRepo.findById(id);
            if (!campanhaOptional.isPresent()) {
                System.out.println("Campanha não encontrada para o ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campanha não encontrada");
            }
    
            Campanha campanha = campanhaOptional.get();
            System.out.println("Campanha encontrada: " + campanha);
    
            if (updates.containsKey("valorArrecadado")) {
                BigDecimal novoValor = new BigDecimal(updates.get("valorArrecadado").toString());
                System.out.println("Novo valor de arrecadação recebido: " + novoValor);
    
                campanha.setValorArrecadado(novoValor);
                campanhaRepo.save(campanha);
    
                System.out.println("Valor atualizado com sucesso para: " + novoValor);
                return ResponseEntity.ok("Valor atualizado com sucesso");
            } else {
                System.out.println("Campo 'valorArrecadado' não encontrado no body.");
                return ResponseEntity.badRequest().body("Campo 'valorArrecadado' não encontrado");
            }
    
        } catch (NumberFormatException e) {
            System.out.println("Erro de formatação numérica no valor de arrecadação.");
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Formato de número inválido para valorArrecadado");
        } catch (Exception e) {
            System.out.println("Erro interno ao atualizar a campanha:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a campanha");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampanha(@PathVariable Long id) {
        if (!campanhaRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        campanhaRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
