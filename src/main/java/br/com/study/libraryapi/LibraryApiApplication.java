package br.com.study.libraryapi;

import br.com.study.libraryapi.api.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class LibraryApiApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Autowired
	private EmailService emailService;

	@Bean
	public CommandLineRunner runner (){
		return args -> {
			List<String> emails = Arrays.asList("library-api-2bd3f4@inbox.mailtrap.io");
			emailService.sendMails("Testando serviço de emails",emails);
			System.out.println("Email enviado");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApiApplication.class, args);
	}



}
