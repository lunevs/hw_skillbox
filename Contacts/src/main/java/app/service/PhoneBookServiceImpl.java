package app.service;

import app.exceptions.EmailFormatException;
import app.exceptions.FullNameFormatException;
import app.exceptions.PhoneNumberFormatException;
import app.model.Contact;
import app.model.PhoneBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Map;

import static app.service.UtilsService.*;

@Slf4j
public class PhoneBookServiceImpl implements PhoneBookService {

    @Value("${custom.dump-file.path}")
    private String dumpFilePath;

    private final PhoneBook phoneBook;

    public PhoneBookServiceImpl(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public void add(String fullName, String phoneNumber, String emailAddress) {
        try {
            Contact addedContact = checkAndCreateContact(fullName, phoneNumber, emailAddress);
            phoneBook.getContactsMap().put(addedContact.getEmail(), addedContact);
        } catch (Exception e) {
            log.error("Can't add Contact: " + e.getMessage());
        }
    }

    @Override
    public void add(Contact contact) {
        this.add(contact.getFullName(), contact.getPhoneNumber(), contact.getEmail());
    }

    @Override
    public void remove(String emailAddress) {
        try {
            String email = checkEmail(emailAddress);
            phoneBook.getContactsMap().remove(email);
        } catch (Exception e) {
            log.error("Can't remove Contact: " + e.getMessage());
        }
    }

    @Override
    public void print() {
        for (Map.Entry<String, Contact> entry : phoneBook.getContactsMap().entrySet()) {
            System.out.println(
                    MessageFormat.format("{0} | {1} | {2}",
                            entry.getValue().getFullName(),
                            entry.getValue().getPhoneNumber(),
                            entry.getValue().getEmail()));
        }
    }

    @Override
    public void dumpContracts() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(dumpFilePath))) {
            for (Map.Entry<String, Contact> entry : phoneBook.getContactsMap().entrySet()) {
                printWriter.printf("%s;%s;%s\n",
                        entry.getValue().getFullName(),
                        entry.getValue().getPhoneNumber(),
                        entry.getValue().getEmail());
            }
        } catch (IOException e) {
            log.error("Can't write data to file: " + e.getMessage());
        }
    }

    @Override
    public void parseAndAdd(String line) {
        try {
            String[] parameters = UtilsService.parseString(line.trim());
            Contact contact = checkAndCreateContact(parameters[0], parameters[1], parameters[2]);
            add(contact);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private Contact checkAndCreateContact(String fullName, String phoneNumber, String emailAddress) throws EmailFormatException, FullNameFormatException, PhoneNumberFormatException {
        return new Contact(
                checkFullName(fullName.trim()),
                checkPhoneNumber(phoneNumber.trim()),
                checkEmail(emailAddress.trim()));
    }
}
