package app.service;

public class DefaultContactsLoader implements ContactsLoader {

    private final PhoneBookService phoneBookService;

    public DefaultContactsLoader(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @Override
    public void initContacts() {
    }


}
