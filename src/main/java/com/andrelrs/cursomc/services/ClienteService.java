package com.andrelrs.cursomc.services;

import com.andrelrs.cursomc.domain.Cliente;
import com.andrelrs.cursomc.repositories.ClienteRepository;
import com.andrelrs.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        //ele irá retorna o objeto ou a excessão
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
}
