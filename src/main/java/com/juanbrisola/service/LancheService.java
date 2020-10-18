package com.juanbrisola.service;

import com.juanbrisola.converter.DozerConverter;
import com.juanbrisola.data.model.Lanche;
import com.juanbrisola.data.vo.LancheVO;
import com.juanbrisola.exception.ResourceNotFoundException;
import com.juanbrisola.repository.LancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancheService {

    @Autowired
    LancheRepository repository;

    public LancheVO findById(Long id) {
        Lanche lanche = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro de lanche encontrado para o ID informado."));
        return DozerConverter.parseObject(lanche, LancheVO.class);
    }

    public List<LancheVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), LancheVO.class);
    }

}
