package com.example.demo.controller;

import com.example.demo.model.Campanha;
import com.example.demo.repository.CampanhaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaRepo campanhaRepo;

    @GetMapping
    public Iterable<Campanha> listarTodasCampanhas() {
        return campanhaRepo.findAll(); // Retorna todas as campanhas
    }

    @GetMapping("/{id}")
    public Campanha buscarCampanhaPorId(@PathVariable Long id) {
        return campanhaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada com o ID: " + id));
    }

    @PostMapping
    public Campanha criarCampanha(@RequestBody Campanha novaCampanha) {
        return campanhaRepo.save(novaCampanha); // Salva nova campanha
    }

    @PutMapping("/{id}")
    public Campanha atualizarCampanha(@PathVariable Long id, @RequestBody Campanha campanhaAtualizada) {
        return campanhaRepo.findById(id).map(campanha -> {
            campanha.setNome(campanhaAtualizada.getNome());
            campanha.setDescricao(campanhaAtualizada.getDescricao());
            campanha.setMeta(campanhaAtualizada.getMeta());
            campanha.setValorArrecadado(campanhaAtualizada.getValorArrecadado());
            campanha.setCausa(campanhaAtualizada.getCausa());
            campanha.setDataInicio(campanhaAtualizada.getDataInicio());
            campanha.setDataFim(campanhaAtualizada.getDataFim());
            return campanhaRepo.save(campanha); // Atualiza a campanha
        }).orElseThrow(() -> new RuntimeException("Campanha não encontrada com o ID: " + id));
    }

    @DeleteMapping("/{id}")
    public void deletarCampanha(@PathVariable Long id) {
        if (!campanhaRepo.existsById(id)) {
            throw new RuntimeException("Campanha não encontrada com o ID: " + id);
        }
        campanhaRepo.deleteById(id); // Deleta a campanha
    }
}
