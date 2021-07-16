package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.EmailService;
import com.kodlamaio.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailManager implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendVerifyEmail(User user, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("HRMS Email Təsdiqlənməsi");
        message.setText("Hrms Email Təsdiqləmə Linki:" +
                " http://localhost:8080/api/activationcode/active/"+code);
        message.setTo(user.getEmail());
        message.setFrom("demo@gmail.com");
        emailSender.send(message);

    }
}