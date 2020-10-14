package com.juanbrisola.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"ingrediente", "quantidade"})
public class AdicionalVO {

    @JsonProperty("ingrediente")
    private IngredienteVO ingredienteVO;

    private Integer quantidade;

    public IngredienteVO getIngredienteVO() {
        return ingredienteVO;
    }

    public void setIngredienteVO(IngredienteVO ingredienteVO) {
        this.ingredienteVO = ingredienteVO;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
