package com.andrelrs.cursomc.config;

import com.andrelrs.cursomc.services.DBService;
import com.andrelrs.cursomc.services.EmailService;
import com.andrelrs.cursomc.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

//ira pega o arquivo application-test, e ira rodar o bando de test
@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        //insere os dados no banco de teste
        dbService.instantiateTestDatabase();

        return true;
    }

    //Isso vai ficar disponivel como um componento do nosso sistema quando tiver a anotação @Bean,
    //se eu injetar EmailService em outra classe, o spring vai procurar um componente que pode ser um @Bean
    //que vai retornar um estancia desse tipo
    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
