package com.andrelrs.cursomc.services;

import com.andrelrs.cursomc.domain.Categoria;
import com.andrelrs.cursomc.repositories.CategoriaRepository;
import com.andrelrs.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        //ele irá retorna o objeto ou a excessão
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
		obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repo.save(obj);
    }
}
