package com.andrelrs.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

//Quando for usar essa classe, irá precisar colocar o email e a senha nos arquivo
//application-dev e application-prod
public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando...");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }
}