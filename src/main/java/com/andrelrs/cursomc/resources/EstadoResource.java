package com.andrelrs.cursomc.resources;

import com.andrelrs.cursomc.domain.Cidade;
import com.andrelrs.cursomc.domain.Estado;
import com.andrelrs.cursomc.dto.CidadeDTO;
import com.andrelrs.cursomc.dto.EstadoDTO;
import com.andrelrs.cursomc.services.CidadeService;
import com.andrelrs.cursomc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll() {

        List<Estado> lista = service.findAll();

        List<EstadoDTO> listDto = lista.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {

        List<Cidade> lista = cidadeService.findByEstado(estadoId);

        List<CidadeDTO> listaDto = lista.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDto);
    }

}
