package com.juanbrisola.data.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"lanches", "totalPedido"})
public class PedidoVO {

    private List<LancheVO> lanches;

    private BigDecimal totalPedido;

    public List<LancheVO> getLanches() {
        return lanches;
    }

    public void setLanches(List<LancheVO> lanches) {
        this.lanches = lanches;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

}
