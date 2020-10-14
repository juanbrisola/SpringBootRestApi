package com.juanbrisola.service;

import com.juanbrisola.data.vo.AdicionalVO;
import com.juanbrisola.data.vo.IngredienteVO;
import com.juanbrisola.data.vo.PedidoVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PedidoService {

    public PedidoVO calculaPedido(PedidoVO pedidoVO) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (AdicionalVO adicionalVO : pedidoVO.getAdicionais()) {
            valorTotal = valorTotal
                    .add(adicionalVO
                            .getIngredienteVO()
                            .getValor()
                            .multiply(BigDecimal.valueOf(adicionalVO.getQuantidade())));
        }
        valorTotal = valorTotal.add(pedidoVO.getLanche().getValor());


        pedidoVO.setTotalPedido(valorTotal);

        if (promocaoLight(pedidoVO).compareTo(BigDecimal.ZERO) != 0) {
            pedidoVO.setTotalPedido(pedidoVO.getTotalPedido().subtract(promocaoLight(pedidoVO)));
            pedidoVO.setTemPromocaoLight(true);
        }

        if (promocaoMuitaCarne(pedidoVO).compareTo(BigDecimal.ZERO) != 0) {
            pedidoVO.setTotalPedido(pedidoVO.getTotalPedido().subtract(promocaoMuitaCarne(pedidoVO)));
            pedidoVO.setTemPromocaoMuitaCarne(true);
        }

        if (promocaoMuitoQueijo(pedidoVO).compareTo(BigDecimal.ZERO) != 0) {
            pedidoVO.setTotalPedido(pedidoVO.getTotalPedido().subtract(promocaoMuitoQueijo(pedidoVO)));
            pedidoVO.setTemPromocaoMuitoQueijo(true);
        }


        return pedidoVO;


    }

    private BigDecimal promocaoLight(PedidoVO pedidoVO) {

        BigDecimal descontoLight = BigDecimal.ZERO;

        boolean temAlface = pedidoVO.getLanche()
                .getIngredientes().stream().anyMatch(ingredienteVO ->
                        ingredienteVO.getDescricao().equals("Alface")) ||
                pedidoVO.getAdicionais().stream().anyMatch(adicionalVO ->
                        adicionalVO.getIngredienteVO().getDescricao().equals("Alface"));

        boolean temBacon = pedidoVO.getLanche()
                .getIngredientes().stream().anyMatch(ingredienteVO ->
                        ingredienteVO.getDescricao().equals("Bacon")) ||
                pedidoVO.getAdicionais().stream().anyMatch(adicionalVO ->
                        adicionalVO.getIngredienteVO().getDescricao().equals("Bacon"));

        if (temAlface && !temBacon) {
            descontoLight = pedidoVO
                    .getTotalPedido()
                    .multiply(new BigDecimal(10))
                    .divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
        }

        return descontoLight;
    }

    private BigDecimal promocaoMuitaCarne(PedidoVO pedidoVO) {

        int qtHamburguer = 0;
        boolean temHamburguer = false;
        BigDecimal valorHamburguer = BigDecimal.ZERO;
        BigDecimal descontoHamburguer = BigDecimal.ZERO;

        for (IngredienteVO ingredienteVO : pedidoVO.getLanche().getIngredientes()) {
            if (ingredienteVO.getDescricao().equals("Hamburguer")) {
                qtHamburguer++;
                temHamburguer = true;
                valorHamburguer = ingredienteVO.getValor();
            }
        }

        for (AdicionalVO adicionalVO : pedidoVO.getAdicionais()) {
            if (adicionalVO.getIngredienteVO().getDescricao().equals("Hamburguer")) {
                qtHamburguer = qtHamburguer + adicionalVO.getQuantidade();
                temHamburguer = true;
                valorHamburguer = adicionalVO.getIngredienteVO().getValor();
            }
        }

        if (temHamburguer) {
            int qtHamburguerDescontado = qtHamburguer / 3;
            descontoHamburguer = valorHamburguer.multiply(BigDecimal.valueOf(qtHamburguerDescontado));
        }

        return descontoHamburguer;
    }

    private BigDecimal promocaoMuitoQueijo(PedidoVO pedidoVO) {

        int qtQueijo = 0;
        boolean temQueijo = false;
        BigDecimal valorQueijo = BigDecimal.ZERO;
        BigDecimal descontoQueijo = BigDecimal.ZERO;

        for (IngredienteVO ingredienteVO : pedidoVO.getLanche().getIngredientes()) {
            if (ingredienteVO.getDescricao().equals("Queijo")) {
                qtQueijo++;
                temQueijo = true;
                valorQueijo = ingredienteVO.getValor();
            }
        }

        for (AdicionalVO adicionalVO : pedidoVO.getAdicionais()) {
            if (adicionalVO.getIngredienteVO().getDescricao().equals("Queijo")) {
                qtQueijo = qtQueijo + adicionalVO.getQuantidade();
                temQueijo = true;
                valorQueijo = adicionalVO.getIngredienteVO().getValor();
            }
        }

        if (temQueijo) {
            int qtQueijoDescontado = qtQueijo / 3;
            descontoQueijo = valorQueijo.multiply(BigDecimal.valueOf(qtQueijoDescontado));
        }

        return descontoQueijo;
    }

}
