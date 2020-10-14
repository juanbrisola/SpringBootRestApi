package com.juanbrisola.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import com.juanbrisola.data.model.LancheIngrediente;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonPropertyOrder({"id", "descricao", "valor"})
public class IngredienteVO {

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String descricao;

    private BigDecimal valor;

    @JsonIgnore
    private List<LancheIngrediente> lancheIngredienteList;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<LancheIngrediente> getLancheIngredienteList() {
        return lancheIngredienteList;
    }

    public void setLancheIngredienteList(List<LancheIngrediente> lancheIngredienteList) {
        this.lancheIngredienteList = lancheIngredienteList;
    }
}
