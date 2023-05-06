package simplechat.example.simplechat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import simplechat.example.simplechat.Models.Message;
import simplechat.example.simplechat.Service.MessageService;

@SpringBootApplication
public class SimplechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplechatApplication.class, args);
	}
	

	@Bean
	CommandLineRunner runner(MessageService messageService){
		return args -> {
			Message mess1 = new Message("hello", "1", "quan");
			Message mess2 = new Message("hello 2", "1", "nhan");
			messageService.saveMessage(mess1);
			messageService.saveMessage(mess2);
		};
	}

}
