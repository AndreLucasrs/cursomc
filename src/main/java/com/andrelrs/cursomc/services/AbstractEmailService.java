package com.andrelrs.cursomc.services;

import com.andrelrs.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {

        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }

    //montas as informações necessario para o envio de email
    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {

        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Código: "+obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());

        return sm;
    }

    protected String htmlFromTemplatePedido(Pedido obj){

        Context context = new Context();
        context.setVariable("pedido",obj);

        return templateEngine.process("email/confirmacaoPedido",context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido obj){

        try {
            MimeMessage mm = prepareMimeMessageFromPedido(obj);
            sendHtmlEmail(mm);
        }
        catch (MessagingException ex){
            sendOrderConfirmationEmail(obj);
        }
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setTo(obj.getCliente().getEmail());
        messageHelper.setFrom(sender);
        messageHelper.setSubject("Pedido confirmado! Código: "+obj.getId());
        messageHelper.setSentDate(new Date(System.currentTimeMillis()));
        messageHelper.setText(htmlFromTemplatePedido(obj),true);

        return mimeMessage;
    }
}
