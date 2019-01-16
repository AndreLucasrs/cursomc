package com.andrelrs.cursomc.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Isso esta criar uma nova Anotação, que é a anotação @ClienteInsert que vai ser usar no ClienteNewDTO para validar dados como cpfOuCnpj ou email existente
@Constraint(validatedBy = ClienteInsertValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsert {
    String message() default "Erro de validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
