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
    public ResponseEntity<?> criarCampanha(@RequestBody Campanha novaCampanha) {
        try {
            Campanha campanhaSalva = campanhaRepo.save(novaCampanha);
            return ResponseEntity.status(HttpStatus.CREATED).body(campanhaSalva);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a campanha: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCampanha(@PathVariable Long id, @RequestBody Campanha campanhaAtualizada) {
        try {
            Optional<Campanha> optionalCampanha = campanhaRepo.findById(id);
            if (!optionalCampanha.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campanha não encontrada");
            }

            Campanha campanha = optionalCampanha.get();
            campanha.setNome(campanhaAtualizada.getNome());
            campanha.setDescricao(campanhaAtualizada.getDescricao());
            campanha.setMeta(campanhaAtualizada.getMeta());
            campanha.setValorArrecadado(campanhaAtualizada.getValorArrecadado());
            campanha.setCausa(campanhaAtualizada.getCausa());
            campanha.setDataInicio(campanhaAtualizada.getDataInicio());
            campanha.setDataFim(campanhaAtualizada.getDataFim());

            campanhaRepo.save(campanha);
            return ResponseEntity.ok(campanha);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a campanha");
        }
    }

    @PutMapping("/{id}/doar")
    public ResponseEntity<String> updateValorArrecadado(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            Optional<Campanha> campanhaOptional = campanhaRepo.findById(id);
            if (!campanhaOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campanha não encontrada");
            }

            Campanha campanha = campanhaOptional.get();

            if (updates.containsKey("valorArrecadado")) {
                BigDecimal novoValor = new BigDecimal(updates.get("valorArrecadado").toString());
                campanha.setValorArrecadado(novoValor);
                campanhaRepo.save(campanha);
                return ResponseEntity.ok("Valor atualizado com sucesso");
            } else {
                return ResponseEntity.badRequest().body("Campo 'valorArrecadado' não encontrado");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Formato de número inválido para valorArrecadado");
        } catch (Exception e) {
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
