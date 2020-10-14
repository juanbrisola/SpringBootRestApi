package com.juanbrisola.controller;

import com.juanbrisola.data.model.Lanche;
import com.juanbrisola.data.vo.LancheVO;
import com.juanbrisola.service.LancheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Endpoint de Lanches", tags = "Lanches")
@RestController
@RequestMapping("/api/lanche")
public class LancheController {

    @Autowired
    private LancheService service;

    @ApiOperation(value = "Cria um novo lanche")
    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public Lanche create(@RequestBody Lanche lanche) {
        return service.create(lanche);
    }

    @ApiOperation(value = "Busca todos os lanches")
    @GetMapping(produces = {"application/json", "application/xml"})
    public List<LancheVO> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "Busca um lanche específico pelo seu ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public LancheVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @ApiOperation(value = "Exclui um lanche específíco pelo seu ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
