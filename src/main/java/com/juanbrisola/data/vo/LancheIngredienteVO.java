package com.juanbrisola.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

@JsonPropertyOrder({"id", "ingrediente"})
public class LancheIngredienteVO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    @JsonIgnore
    private LancheVO lancheVO;

    private IngredienteVO ingredienteVO;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public LancheVO getLancheVO() {
        return lancheVO;
    }

    public void setLancheVO(LancheVO lancheVO) {
        this.lancheVO = lancheVO;
    }

    public IngredienteVO getIngredienteVO() {
        return ingredienteVO;
    }

    public void setIngredienteVO(IngredienteVO ingredienteVO) {
        this.ingredienteVO = ingredienteVO;
    }
}
