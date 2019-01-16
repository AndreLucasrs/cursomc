package com.andrelrs.cursomc.services.validation;

import com.andrelrs.cursomc.domain.Cliente;
import com.andrelrs.cursomc.domain.enums.TipoCliente;
import com.andrelrs.cursomc.dto.ClienteNewDTO;
import com.andrelrs.cursomc.repositories.ClienteRepository;
import com.andrelrs.cursomc.resources.exception.FieldMessage;
import com.andrelrs.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

//Classe que cria as regras da anotação @ClienteInsert
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Cliente aux = repository.findByEmail(objDto.getEmail());
        if (nonNull(aux)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

// inclua os testes aqui, inserindo erros na lista
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
