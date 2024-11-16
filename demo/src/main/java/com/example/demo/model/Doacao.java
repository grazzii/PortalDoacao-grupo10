package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "doacao") // Garantimos o nome da tabela
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-incrementado
    private Integer id; // Alterado para Integer para refletir o tipo INT na tabela

    @Column(name = "valor_arrecadado", nullable = false) // Mapeia a coluna NUMERIC
    private BigDecimal valor;

    @Column(name = "data_doacao", nullable = false) // Mapeia a coluna DATE
    private LocalDate dataDoacao;

    @ManyToOne
    @JoinColumn(name = "campanha_id", nullable = false) // Mapeia a coluna INT com chave estrangeira
    private Campanha campanha;

    // Construtores
    public Doacao() {}

    public Doacao(BigDecimal valor, LocalDate dataDoacao, Campanha campanha) {
        this.valor = valor;
        this.dataDoacao = dataDoacao;
        this.campanha = campanha;
    }

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
}
