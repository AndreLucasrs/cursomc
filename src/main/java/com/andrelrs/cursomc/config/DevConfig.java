package com.andrelrs.cursomc.config;

import com.andrelrs.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

//ira pega o arquivo application-dev, e ira rodar o bando de desenvolvimento
@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    //Isso irá pegar essa linha e irá olhar o que ela esta, se é create ou none,
    //pq se for none não precisaremos toda hora instancia o dbService.instantiateTestDatabase();
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if(!"create".equals(strategy)){
            return false;
        }
        //insere os dados no banco de dev
        dbService.instantiateTestDatabase();

        return true;
    }
}
