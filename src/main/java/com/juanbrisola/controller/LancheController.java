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

}
