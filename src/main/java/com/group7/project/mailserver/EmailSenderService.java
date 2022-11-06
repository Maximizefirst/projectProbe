package com.group7.project.mailserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(body);
        message.setFrom("Group7");
        message.setTo(toEmail);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("MAIL SENT SUCCESSFULLY ...");
    }
}
