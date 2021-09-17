package br.com.study.libraryapi.api.service;


import java.util.List;


public interface EmailService {
    void sendMails(String mensagem,List<String> mailsList) ;
}
