package app.service;

import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InitContactsLoader implements ContactsLoader {

    @Value("${custom.init-file.path}")
    private String initFilePath;

    private final PhoneBookService phoneBookService;

    public InitContactsLoader(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @Override
    public void initContacts() {
        Path pathInitFile = Path.of(initFilePath);
        try {
            List<String> initContacts = Files.readAllLines(pathInitFile);
            for (String c : initContacts) {
                String[] parsedLine = UtilsService.parseString(c);
                phoneBookService.add(parsedLine[0], parsedLine[1], parsedLine[2]);
            }
        } catch (Exception e) {
            Class<?> tmpObject = new Object() {}.getClass();
            String className = tmpObject.getName();
            String methodName = tmpObject.getEnclosingMethod().getName();
            System.out.println(className + "." + methodName + ": Can't read initial Contact data: " + e.getMessage());
        }
    }
}

