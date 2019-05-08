package com.andrelrs.cursomc.resources;

import com.andrelrs.cursomc.domain.Categoria;
import com.andrelrs.cursomc.dto.CategoriaDTO;
import com.andrelrs.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {

        Categoria obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    //@RequestBody serve para converter o json em objeto java
    //Para validar esse obkDTO antes de passar pra frente usar o @Valid
    //@PreAuthorize serve para dizer qual perfil especifico pode acessar esse endpoint
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {

        Categoria obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        //Depois de salvar o Objeto ele sera redirecionado, ele vai ser redirecionado para /{id},
        // o id passada sera o que acabou de ser criado no objeto
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    //PUT para atualizar
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {

        Categoria obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {

        List<Categoria> list = service.findAll();
        List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    //@RequestParam quer dizer que os parametros são opcionais
    //por que colocar 24 linhas por pagina, é pq ele é multiplo de 1,2,3 e 4 então fica facil de organizar o layout de forma responsivel,
    //pq vc pode mostrar de 1 e 1, 2 e 2 ...
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
        //Como o page ja é java 8, não precisa do stream e nem do collect
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }

}
