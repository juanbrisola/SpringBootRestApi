package com.juanbrisola.controller;

import com.juanbrisola.data.vo.CardapioVO;
import com.juanbrisola.data.vo.PedidoVO;
import com.juanbrisola.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Api(value = "Endpoint de Pedidos", tags = "Pedidos")
@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @ApiOperation(value = "Calcula o valor de um pedido baseado nos ingredientes do Lanche")
    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public PedidoVO calculaPedido(@RequestBody PedidoVO pedidoVO) {
        return pedidoService.calculaPedido(pedidoVO);
    }

    @ApiOperation(value = "Retorna um cardápio com todos os lanches e ingredientes disponíveis")
    @GetMapping(path = "/cardapio", produces = {"application/json", "application/xml"})
    public CardapioVO retornaCardapio() {
        return pedidoService.retornaCardapio();
    }
}
