package app.config;

import app.service.ContactsLoader;
import app.service.InitContactsLoader;
import app.service.PhoneBookService;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application-init.properties")
@Profile("init")
public class InitConfig {

    @Bean
    public ContactsLoader contactsLoader(PhoneBookService phoneBookService) {
        return new InitContactsLoader(phoneBookService);
    }

}
