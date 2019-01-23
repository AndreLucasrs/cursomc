package com.andrelrs.cursomc.resources;

import com.andrelrs.cursomc.domain.Categoria;
import com.andrelrs.cursomc.domain.Pedido;
import com.andrelrs.cursomc.dto.CategoriaDTO;
import com.andrelrs.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {

        Pedido obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    //@RequestBody serve para converter o json em objeto java
    //Para validar esse obkDTO antes de passar pra frente usar o @Valid
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {

        obj = service.insert(obj);
        //Depois de salvar o Objeto ele sera redirecionado, ele vai ser redirecionado para /{id},
        // o id passada sera o que acabou de ser criado no objeto
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
