package app.service;

import app.exceptions.EmailFormatException;
import app.exceptions.FullNameFormatException;
import app.exceptions.MissedParametersException;
import app.exceptions.PhoneNumberFormatException;
import app.model.Contact;
import app.model.PhoneBook;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Map;


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
            Class<?> tmpObject = new Object() {}.getClass();
            String className = tmpObject.getName();
            String methodName = tmpObject.getEnclosingMethod().getName();
            System.out.println(className + "." + methodName + ": Can't add Contact: " + e.getMessage());
        }
    }

    @Override
    public void add(Contact contact) {
        this.add(contact.getFullName(), contact.getPhoneNumber(), contact.getEmail());
    }

    @Override
    public void remove(String emailAddress) {
        try {
            String email = UtilsService.checkEmail(emailAddress);
            phoneBook.getContactsMap().remove(email);
        } catch (Exception e) {
            Class<?> tmpObject = new Object() {}.getClass();
            String className = tmpObject.getName();
            String methodName = tmpObject.getEnclosingMethod().getName();
            System.out.println(className + "." + methodName + ": Can't remove Contact: " + e.getMessage());
        }
    }

    @Override
    public void print() {
        if (!phoneBook.getContactsMap().isEmpty()) {
            for (Map.Entry<String, Contact> entry : phoneBook.getContactsMap().entrySet()) {
                System.out.println(
                        MessageFormat.format("{0} | {1} | {2}",
                                entry.getValue().getFullName(),
                                entry.getValue().getPhoneNumber(),
                                entry.getValue().getEmail()));
            }
        } else  {
            System.out.println("Contacts list is empty");
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
            Class<?> tmpObject = new Object() {}.getClass();
            String className = tmpObject.getName();
            String methodName = tmpObject.getEnclosingMethod().getName();
            System.out.println(className + "." + methodName + ": Can't write data to file: " + e.getMessage());
        }
    }

    @Override
    public void parseAndAdd(String line) {
        try {
            String[] parameters = UtilsService.parseString(line.trim());
            if (parameters.length == 3) {
                Contact contact = checkAndCreateContact(parameters[0], parameters[1], parameters[2]);
                add(contact);
            } else {
                throw new MissedParametersException("Incorrect number of parameters! Expected 3, found: " + parameters.length);
            }
        } catch (Exception e) {
            Class<?> tmpObject = new Object() {}.getClass();
            String className = tmpObject.getName();
            String methodName = tmpObject.getEnclosingMethod().getName();
            System.out.println(className + "." + methodName + ": Can't parse data: " + e.getMessage());
        }
    }

    private Contact checkAndCreateContact(String fullName, String phoneNumber, String emailAddress) throws EmailFormatException, FullNameFormatException, PhoneNumberFormatException {
        return new Contact(
                UtilsService.checkFullName(fullName.trim()),
                UtilsService.checkPhoneNumber(phoneNumber.trim()),
                UtilsService.checkEmail(emailAddress.trim()));
    }
}
