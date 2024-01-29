package rs.ac.uns.ftn.springsecurityexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.User;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.core.env.Environment;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;
    
    
    public void sendActivationCode(User user) {

       // System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Account activation");


        String activationURL = "http://localhost:8080/api/activateAccount/"
                + user.getActivationCode();

        mail.setText("Hello, " + user.getFirstName() + "! \n " +
                     "To acctivate your account, please click the following link:\n" +
                activationURL);

        javaMailSender.send(mail);
       // System.out.println("Email poslat!");
    }
}
