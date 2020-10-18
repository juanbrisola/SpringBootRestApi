package com.juanbrisola.controller;

import com.juanbrisola.data.vo.IngredienteVO;
import com.juanbrisola.service.IngredienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Endpoint de Ingredientes", tags = "Ingredientes")
@RestController
@RequestMapping("/api/ingrediente")
public class IngredienteController {

    @Autowired
    private IngredienteService service;

    @ApiOperation(value = "Busca todos os ingredientes")
    @GetMapping(produces = {"application/json", "application/xml"})
    public List<IngredienteVO> findAll() {
        return service.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @ApiOperation(value = "Busca um ingrediente específico pelo seu ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public IngredienteVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @ApiOperation(value = "Altera um ingrediente específico")
    @PutMapping(value = "/{id}", produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public IngredienteVO update(@PathVariable("id") Long id, @RequestBody IngredienteVO ingrediente) {
        ingrediente.setKey(id);
        return service.update(ingrediente);
    }

}
