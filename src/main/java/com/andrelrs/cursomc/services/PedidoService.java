package com.andrelrs.cursomc.services;

import com.andrelrs.cursomc.domain.Pedido;
import com.andrelrs.cursomc.repositories.PedidoRepository;
import com.andrelrs.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        //ele irá retorna o objeto ou a excessão
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
