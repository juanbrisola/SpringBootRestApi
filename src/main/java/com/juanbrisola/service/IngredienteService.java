package com.juanbrisola.service;

import com.juanbrisola.converter.DozerConverter;
import com.juanbrisola.data.model.Ingrediente;
import com.juanbrisola.data.vo.IngredienteVO;
import com.juanbrisola.exception.ResourceNotFoundException;
import com.juanbrisola.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    IngredienteRepository repository;

    public Ingrediente create(Ingrediente ingrediente) {
        repository.save(ingrediente);
        return ingrediente;
    }

    public IngredienteVO findById(Long id) {
        Ingrediente ingrediente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID"));
        return DozerConverter.parseObject(ingrediente, IngredienteVO.class);
    }

    public List<IngredienteVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), IngredienteVO.class);
    }

    public IngredienteVO update(IngredienteVO ingredienteVO) {
        Ingrediente ingrediente = repository.findById(ingredienteVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID"));

        ingrediente.setDescricao(ingredienteVO.getDescricao());
        ingrediente.setValor(ingredienteVO.getValor());
        return DozerConverter.parseObject(repository.save(ingrediente), IngredienteVO.class);
    }


    public void delete(Long id) {
        Ingrediente entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID"));
            repository.delete(entity);
    }

}
