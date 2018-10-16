package com.andrelrs.cursomc.services;

import com.andrelrs.cursomc.domain.Cliente;
import com.andrelrs.cursomc.dto.ClienteDTO;
import com.andrelrs.cursomc.repositories.ClienteRepository;
import com.andrelrs.cursomc.services.exceptions.DataIntegrityException;
import com.andrelrs.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Cliente update(Cliente obj) {

        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {

            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {

            throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
        }

    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    //Page é uma forma de encapsula informaçãoes e operações

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        //o findAll vai considerar o pageRequest como argumento e vai me retornar a pagina
        return repo.findAll(pageRequest);
    }
    //Instancia uma Cliente apartir do DTO

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
