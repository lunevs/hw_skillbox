package app.config;

import app.service.ContactsLoader;
import app.service.DefaultContactsLoader;
import app.service.PhoneBookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!init")
public class DefaultConfig {

    @Bean
    public ContactsLoader contactsLoader(PhoneBookService phoneBookService) {
        return new DefaultContactsLoader(phoneBookService);
    }

}
