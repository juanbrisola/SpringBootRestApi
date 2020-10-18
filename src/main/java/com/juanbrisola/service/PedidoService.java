package com.juanbrisola.service;

import com.juanbrisola.data.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.List;


@Service
public class PedidoService {

    @Autowired
    private LancheService lancheService;

    @Autowired
    private IngredienteService ingredienteService;

    private List<LancheVO> lanchesMemorizados = new ArrayList<>();
    private List<IngredienteVO> ingredientesMemorizados = new ArrayList<>();

    public CardapioVO retornaCardapio() {
        CardapioVO cardapio = new CardapioVO();
        cardapio.setLanches(lancheService.findAll());
        cardapio.setIngredientes(ingredienteService.findAll());
        return cardapio;
    }

    public PedidoVO calculaPedido(PedidoVO pedidoVO) {
        PedidoVO pedidoCalculado = new PedidoVO();
        pedidoCalculado.setLanches(new ArrayList<>());
        pedidoCalculado.setTotalPedido(BigDecimal.ZERO);


        for (LancheVO lancheVO : pedidoVO.getLanches()) {

            LancheVO lanchePedido = procuraLanche(lancheVO.getKey());
            lanchePedido.setIdGeradoPeloFront(lancheVO.getIdGeradoPeloFront());
            if (!CollectionUtils.isEmpty(lancheVO.getAdicionais())) {

                lanchePedido.setAdicionais(lancheVO.getAdicionais());

                for (AdicionalVO adicionalVO : lancheVO.getAdicionais()) {
                    adicionalVO.setIngredienteVO(procuraIngrediente(adicionalVO.getIngredienteVO().getKey()));
                    lanchePedido.setValor(
                            lanchePedido.getValor()
                                    .add(adicionalVO
                                            .getIngredienteVO()
                                            .getValor()
                                            .multiply(BigDecimal.valueOf(adicionalVO.getQuantidade()))));
                }
            }


            lanchePedido.setPromosAplicadas(new ArrayList<>());

            if (promocaoMuitaCarne(lanchePedido).compareTo(BigDecimal.ZERO) != 0) {
                lanchePedido.setValor(lanchePedido.getValor().subtract(promocaoMuitaCarne(lanchePedido)));
                lanchePedido.getPromosAplicadas().add("MUITA_CARNE");
            }

            if (promocaoMuitoQueijo(lanchePedido).compareTo(BigDecimal.ZERO) != 0) {
                lanchePedido.setValor(lanchePedido.getValor().subtract(promocaoMuitoQueijo(lanchePedido)));
                lanchePedido.getPromosAplicadas().add("MUITO_QUEIJO");
            }

            if (promocaoLight(lanchePedido).compareTo(BigDecimal.ZERO) != 0) {
                lanchePedido.setValor(lanchePedido.getValor().subtract(promocaoLight(lanchePedido)));
                lanchePedido.getPromosAplicadas().add("LIGHT");
            }

            pedidoCalculado.getLanches().add(lanchePedido);
            pedidoCalculado.setTotalPedido(pedidoCalculado.getTotalPedido().add(lanchePedido.getValor()));
        }

        lanchesMemorizados = new ArrayList<>();
        ingredientesMemorizados = new ArrayList<>();

        return pedidoCalculado;

    }

    private BigDecimal promocaoMuitaCarne(LancheVO lancheVO) {

        int qtHamburguer = 0;
        boolean temHamburguer = false;
        BigDecimal valorHamburguer = BigDecimal.ZERO;
        BigDecimal descontoHamburguer = BigDecimal.ZERO;

        for (IngredienteVO ingredienteVO : lancheVO.getIngredientes()) {

            ingredienteVO = procuraIngrediente(ingredienteVO.getKey());

            if (ingredienteVO.getKey().equals(3L)) {
                qtHamburguer++;
                temHamburguer = true;
                valorHamburguer = ingredienteVO.getValor();
            }
        }
        if (!CollectionUtils.isEmpty(lancheVO.getAdicionais())) {

            for (AdicionalVO adicionalVO : lancheVO.getAdicionais()) {

                adicionalVO.setIngredienteVO(procuraIngrediente(adicionalVO.getIngredienteVO().getKey()));

                if (adicionalVO.getIngredienteVO().getKey().equals(3L)) {
                    qtHamburguer = qtHamburguer + adicionalVO.getQuantidade();
                    temHamburguer = true;
                    valorHamburguer = adicionalVO.getIngredienteVO().getValor();
                }
            }
        }

        if (temHamburguer) {
            int qtHamburguerDescontado = qtHamburguer / 3;
            descontoHamburguer = valorHamburguer.multiply(BigDecimal.valueOf(qtHamburguerDescontado));
        }

        return descontoHamburguer;
    }

    private BigDecimal promocaoMuitoQueijo(LancheVO lancheVO) {

        int qtQueijo = 0;
        boolean temQueijo = false;
        BigDecimal valorQueijo = BigDecimal.ZERO;
        BigDecimal descontoQueijo = BigDecimal.ZERO;

        for (IngredienteVO ingredienteVO : lancheVO.getIngredientes()) {
            if (ingredienteVO.getKey().equals(5L)) {
                qtQueijo++;
                temQueijo = true;
                valorQueijo = ingredienteVO.getValor();
            }
        }

        if (!CollectionUtils.isEmpty(lancheVO.getAdicionais())) {
            for (AdicionalVO adicionalVO : lancheVO.getAdicionais()) {
                if (adicionalVO.getIngredienteVO().getKey().equals(5L)) {
                    qtQueijo = qtQueijo + adicionalVO.getQuantidade();
                    temQueijo = true;
                    valorQueijo = adicionalVO.getIngredienteVO().getValor();
                }
            }
        }

        if (temQueijo) {
            int qtQueijoDescontado = qtQueijo / 3;
            descontoQueijo = valorQueijo.multiply(BigDecimal.valueOf(qtQueijoDescontado));
        }

        return descontoQueijo;
    }

    private BigDecimal promocaoLight(LancheVO lancheVO) {

        BigDecimal descontoLight = BigDecimal.ZERO;

        boolean temAlface = false;
        boolean temBacon = false;

        if (lancheVO.getIngredientes().stream().anyMatch(ingredienteVO ->
                ingredienteVO.getKey().equals(1L))) {
            temAlface = true;
        }

        if (lancheVO.getIngredientes().stream().anyMatch(ingredienteVO ->
                ingredienteVO.getKey().equals(2L))) {
            temBacon = true;
        }


        if (!CollectionUtils.isEmpty(lancheVO.getAdicionais())) {
            if (lancheVO.getAdicionais().stream().anyMatch(
                    adicionalVO -> adicionalVO.getIngredienteVO().getKey().equals(2L))) {
                temBacon = true;
            }
            if (lancheVO.getAdicionais().stream().anyMatch(
                    adicionalVO -> adicionalVO.getIngredienteVO().getKey().equals(1L))) {
                temAlface = true;
            }

            if (temAlface && !temBacon) {
                descontoLight = lancheVO.getValor()
                        .multiply(new BigDecimal(10))
                        .divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
            }
        }
        return descontoLight;
    }


    /* Nos métodos abaixo, foi aplicada uma técnica de Memorização para reduzir requisições ao BD */

    private LancheVO procuraLanche(Long id) {
        if (!lanchesMemorizados.isEmpty()) {
            for (LancheVO lancheMemorizado : lanchesMemorizados) {
                if (lancheMemorizado.getKey().equals(id)) {
                    return lancheMemorizado;
                }
            }
        }
        LancheVO lancheVO = lancheService.findById(id);
        lanchesMemorizados.add(lancheVO);
        return lancheVO;
    }

    private IngredienteVO procuraIngrediente(Long id) {
        if (!ingredientesMemorizados.isEmpty()) {
            for (IngredienteVO ingredienteMemorizado : ingredientesMemorizados) {
                if (ingredienteMemorizado.getKey().equals(id)) {
                    return ingredienteMemorizado;
                }
            }
        }
        IngredienteVO ingredienteVO = ingredienteService.findById(id);
        ingredientesMemorizados.add(ingredienteVO);
        return ingredienteVO;
    }

}
