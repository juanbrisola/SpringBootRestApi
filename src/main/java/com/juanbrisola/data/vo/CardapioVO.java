package com.juanbrisola.data.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"lanches", "ingredientes"})
public class CardapioVO {

    private List<LancheVO> lanches;

    private List<IngredienteVO> ingredientes;

    public List<LancheVO> getLanches() {
        return lanches;
    }

    public void setLanches(List<LancheVO> lanches) {
        this.lanches = lanches;
    }

    public List<IngredienteVO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteVO> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
