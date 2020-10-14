package com.juanbrisola.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import com.juanbrisola.converter.DozerConverter;
import com.juanbrisola.data.model.LancheIngrediente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"id", "nome", "valor", "ingredientes"})
public class LancheVO {

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String nome;

    private BigDecimal valor;

    private List<IngredienteVO> ingredientes;

    @JsonIgnore
    private List<LancheIngrediente> lancheIngredienteList;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<IngredienteVO> getIngredientes() {
        if(ingredientes == null || ingredientes.isEmpty()) {
            List<IngredienteVO> ingredientes = new ArrayList<>();
            for (LancheIngrediente lancheIngrediente : getLancheIngredienteList()) {
                ingredientes.add(DozerConverter.parseObject(lancheIngrediente.getIngrediente(), IngredienteVO.class));
            }
            return ingredientes;
        }
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteVO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<LancheIngrediente> getLancheIngredienteList() {
        return lancheIngredienteList;
    }

    public void setLancheIngredienteList(List<LancheIngrediente> lancheIngredienteList) {
        this.lancheIngredienteList = lancheIngredienteList;
    }

    public BigDecimal getValor() {
        if (valor == null) {
            BigDecimal valor = BigDecimal.ZERO;
            for (LancheIngrediente lancheIngrediente : getLancheIngredienteList()) {
                valor = valor.add(lancheIngrediente.getIngrediente().getValor());
            }
            return valor;
        }
        return valor;
    }
}
