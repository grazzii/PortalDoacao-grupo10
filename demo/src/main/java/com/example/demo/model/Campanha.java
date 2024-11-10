package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String descricao;
    private BigDecimal meta;
    private BigDecimal valorArrecadado = BigDecimal.ZERO; // Inicializa com 0
    private String causa;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    // Construtor sem parâmetros
    public Campanha() {}

    // Construtor com todos os parâmetros
    public Campanha(Long id, String nome, String descricao, BigDecimal meta, BigDecimal valorArrecadado, String causa, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.meta = meta;
        this.valorArrecadado = valorArrecadado != null ? valorArrecadado : BigDecimal.ZERO; // Garantir valor não nulo
        this.causa = causa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getMeta() { return meta; }
    public void setMeta(BigDecimal meta) { this.meta = meta; }

    public BigDecimal getValorArrecadado() { return valorArrecadado; }
    public void setValorArrecadado(BigDecimal valorArrecadado) {
        // Garantir que o valor nunca seja nulo
        this.valorArrecadado = valorArrecadado != null ? valorArrecadado : BigDecimal.ZERO;
    }

    public String getCausa() { return causa; }
    public void setCausa(String causa) { this.causa = causa; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    // Método para somar o valor da doação ao valor arrecadado
    public void doar(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            this.valorArrecadado = this.valorArrecadado.add(valor);
        }
    }
}
