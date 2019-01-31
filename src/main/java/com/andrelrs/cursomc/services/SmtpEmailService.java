package com.andrelrs.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

//Quando for usar essa classe, irá precisar colocar o email e a senha nos arquivo
//application-dev e application-prod
//Para conseguimos enviar o email com o gmail, precisa:
//logar no gmail,
// ir em https://myaccount.google.com/security
// ir na parte de segurança
// ir em Acesso a app menos seguro
// e ativar
//com o passar do tempo ele irá desabilitar novamente
//então faça os passos anterios e adicionesse o proximo passo
//https://accounts.google.com/b/0/DisplayUnlockCaptcha
// e basta clicar em continuar

//referencia pdf: 05-servico-de-email
public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando...");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {

        LOG.info("Enviando email...");
        javaMailSender.send(msg);
        LOG.info("Email enviado");

    }
}