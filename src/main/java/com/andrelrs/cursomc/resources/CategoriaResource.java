package com.andrelrs.cursomc.resources;

import com.andrelrs.cursomc.domain.Categoria;
import com.andrelrs.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Categoria obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    //@RequestBody serve para converter o json em objeto java
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserir(@RequestBody Categoria obj) {
        obj = service.inserir(obj);
        //Depois de salvar o Objeto ele sera redirecionado, ele vai ser redirecionado para /{id},
        // o id passada sera o que acabou de ser criado no objeto
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
