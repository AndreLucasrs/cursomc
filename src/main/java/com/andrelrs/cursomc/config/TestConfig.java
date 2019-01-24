package com.andrelrs.cursomc.config;

import com.andrelrs.cursomc.services.DBService;
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
}
