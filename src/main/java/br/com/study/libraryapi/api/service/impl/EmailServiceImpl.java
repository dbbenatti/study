package br.com.study.libraryapi.api.service.impl;

import br.com.study.libraryapi.api.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${application.mail.defaut.remetent}")
    private String remetent;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMails(String mensagem, List<String> mailsList) {
        String[] mails = mailsList.toArray(new String[mailsList.size()]);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(remetent);
        mailMessage.setSubject("Livro com Emprestimo Atraso");
        mailMessage.setText(mensagem);
        mailMessage.setTo(mails);

        javaMailSender.send(mailMessage);
    }
}
