package com.company.smallspringsecuritywebapp.unils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderListener implements ApplicationListener<OnLoginEvent> {

    private final MailSender mailSender;

    private final String emailForm;

    private final String emailTo;

    public EmailSenderListener(MailSender mailSender,
                               @Value("${EMAIL_FORM}") String emailForm,
                               @Value("${EMAIL_TO}") String emailTo) {

        this.mailSender = mailSender;
        this.emailForm = emailForm;
        this.emailTo = emailTo;
    }

    @Override
    public void onApplicationEvent(OnLoginEvent event) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailForm);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject("testing Spring Mail SMTP");
        mailMessage.setText("Hello World, Done 🎉");

        mailSender.send(mailMessage);


    }
}
