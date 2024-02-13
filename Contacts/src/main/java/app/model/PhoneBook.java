package app.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class PhoneBook {

    private final Map<String, Contact> contactsMap;

    public PhoneBook() {
        this.contactsMap = new HashMap<>();
    }
}
