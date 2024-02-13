package app.config;

import app.model.PhoneBook;
import app.service.PhoneBookService;
import app.service.PhoneBookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("app")
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public PhoneBookService phoneBookService(PhoneBook phoneBook) {
        return new PhoneBookServiceImpl(phoneBook);
    }
}
