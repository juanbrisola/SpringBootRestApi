package com.juanbrisola.data.vo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.List;

@JsonPropertyOrder({"lanche", "adicionais", "temPromocaoLight",
        "temPromocaoMuitaCarne", "temPromocaoMuitoQueijo", "totalPedido"})
public class PedidoVO {

    private LancheVO lanche;

    private Boolean temPromocaoLight;

    private Boolean temPromocaoMuitaCarne;

    private Boolean temPromocaoMuitoQueijo;

    private List<AdicionalVO> adicionais;

    private BigDecimal totalPedido;

    public LancheVO getLanche() {
        return lanche;
    }

    public void setLanche(LancheVO lanche) {
        this.lanche = lanche;
    }

    public Boolean getTemPromocaoLight() {
        return temPromocaoLight;
    }

    public void setTemPromocaoLight(Boolean temPromocaoLight) {
        this.temPromocaoLight = temPromocaoLight;
    }

    public Boolean getTemPromocaoMuitaCarne() {
        return temPromocaoMuitaCarne;
    }

    public void setTemPromocaoMuitaCarne(Boolean temPromocaoMuitaCarne) {
        this.temPromocaoMuitaCarne = temPromocaoMuitaCarne;
    }

    public Boolean getTemPromocaoMuitoQueijo() {
        return temPromocaoMuitoQueijo;
    }

    public void setTemPromocaoMuitoQueijo(Boolean temPromocaoMuitoQueijo) {
        this.temPromocaoMuitoQueijo = temPromocaoMuitoQueijo;
    }

    public List<AdicionalVO> getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(List<AdicionalVO> adicionais) {
        this.adicionais = adicionais;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }
}
