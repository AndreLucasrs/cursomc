package com.andrelrs.cursomc.services;

import com.andrelrs.cursomc.domain.Cidade;
import com.andrelrs.cursomc.domain.Cliente;
import com.andrelrs.cursomc.domain.Endereco;
import com.andrelrs.cursomc.domain.enums.Perfil;
import com.andrelrs.cursomc.domain.enums.TipoCliente;
import com.andrelrs.cursomc.dto.ClienteDTO;
import com.andrelrs.cursomc.dto.ClienteNewDTO;
import com.andrelrs.cursomc.repositories.ClienteRepository;
import com.andrelrs.cursomc.repositories.EnderecoRepository;
import com.andrelrs.cursomc.security.UserSS;
import com.andrelrs.cursomc.services.exceptions.AuthorizationException;
import com.andrelrs.cursomc.services.exceptions.DataIntegrityException;
import com.andrelrs.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private int size;

    public Cliente find(Integer id) {

        UserSS user = UserService.authenticated();

        if (isNull(user) || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {

            throw new AuthorizationException("Acesso Negado");
        }

        Optional<Cliente> obj = repo.findById(id);
        //ele irá retorna o objeto ou a excessão
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }


    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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

            throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionados");
        }

    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findByEmail(String email) {

        UserSS user = UserService.authenticated();

        if (isNull(user) || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {

            throw new AuthorizationException("Acesso Negado");
        }

        Cliente cliente = repo.findByEmail(email);

        if (isNull(cliente)) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
        }

        return cliente;
    }

    //Page é uma forma de encapsula informaçãoes e operações

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        //o findAll vai considerar o pageRequest como argumento e vai me retornar a pagina
        return repo.findAll(pageRequest);
    }
    //Instancia uma Cliente apartir do DTO

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), bCryptPasswordEncoder.encode(objDto.getSenha()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {

        UserSS user = UserService.authenticated();

        if (isNull(user)) {
            throw new AuthorizationException("Acesso negado");
        }

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = prefix + user.getId() + ".jpg";

        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
