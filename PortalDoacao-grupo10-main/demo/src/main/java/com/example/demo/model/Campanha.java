package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String descricao;
    private BigDecimal meta;
    private BigDecimal valorArrecadado;
    private String causa;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Campanha() {}

    public Campanha(Long id, String nome, String descricao, BigDecimal meta, BigDecimal valorArrecadado, String causa, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.meta = meta;
        this.valorArrecadado = valorArrecadado;
        this.causa = causa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getMeta() { return meta; }
    public void setMeta(BigDecimal meta) { this.meta = meta; }

    public BigDecimal getValorArrecadado() { return valorArrecadado; }
    public void setValorArrecadado(BigDecimal valorArrecadado) { this.valorArrecadado = valorArrecadado; }

    public String getCausa() { return causa; }
    public void setCausa(String causa) { this.causa = causa; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}
