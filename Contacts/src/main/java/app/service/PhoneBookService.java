package app.service;

import app.model.Contact;


public interface PhoneBookService {

    void add(String name, String phone, String email);

    void add(Contact contact);
    void remove(String email);
    void print();
    void dumpContracts();

    void parseAndAdd(String line);
}
