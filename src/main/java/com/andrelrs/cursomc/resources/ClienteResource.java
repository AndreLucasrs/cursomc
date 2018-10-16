package com.andrelrs.cursomc.resources;

import com.andrelrs.cursomc.domain.Cliente;
import com.andrelrs.cursomc.dto.ClienteDTO;
import com.andrelrs.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {

        Cliente obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    //PUT para atualizar
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {

        Cliente obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {

        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    //@RequestParam quer dizer que os parametros são opcionais
    //por que colocar 24 linhas por pagina, é pq ele é multiplo de 1,2,3 e 4 então fica facil de organizar o layout de forma responsivel,
    //pq vc pode mostrar de 1 e 1, 2 e 2 ...
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
        //Como o page ja é java 8, não precisa do stream e nem do collect
        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }
}
