package com.juanbrisola.service;

import com.juanbrisola.converter.DozerConverter;
import com.juanbrisola.data.model.Lanche;
import com.juanbrisola.data.model.LancheIngrediente;
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

    public Lanche create(Lanche lanche) {
        repository.save(lanche);
        return lanche;
    }

    public LancheVO findById(Long id) {
        Lanche lanche = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID"));
//        LancheVO lancheVO = new LancheVO();
//        lancheVO.setKey(entity.getId());
//        lancheVO.setNome(entity.getNome());
//        lancheVO.setLancheIngredienteList(entity.getLancheIngredienteList());
        return DozerConverter.parseObject(lanche, LancheVO.class);
    }

    public List<LancheVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), LancheVO.class);
    }

    public void delete(Long id) {
        Lanche lanche = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID"));
        repository.delete(lanche);
    }

}
