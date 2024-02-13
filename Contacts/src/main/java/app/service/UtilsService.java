package app.service;

import app.exceptions.EmailFormatException;
import app.exceptions.FullNameFormatException;
import app.exceptions.MissedParametersException;
import app.exceptions.PhoneNumberFormatException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;

@Slf4j
public class UtilsService {


    public static String[] parseString(String line) throws MissedParametersException {
        String[] parsedLine = line.split(";");
        if (parsedLine.length != 3 || parsedLine[0] == null || parsedLine[1] == null || parsedLine[2] == null
                || parsedLine[0].isBlank() || parsedLine[1].isBlank() || parsedLine[2].isBlank()) {
            throw new MissedParametersException("Incorrect number of parameters! Expected 3, found: " + parsedLine.length);
        }
        return parsedLine;
    }


    public static String checkFullName(String fullName) throws FullNameFormatException {
        if (fullName.trim().length() > 2) {
            return fullName;
        } else {
            throw new FullNameFormatException("full name incorrect format: " + fullName);
        }
    }

    public static String checkPhoneNumber(String phoneNumber) throws PhoneNumberFormatException {
        if (phoneNumber.matches("\\+79[0-9]{9}")) {
            return phoneNumber;
        } else {
            throw new PhoneNumberFormatException("phone number incorrect format: " + phoneNumber);
        }
    }

    public static String checkEmail(String email) throws EmailFormatException {
        if (EmailValidator.getInstance().isValid(email)) {
            return email;
        } else {
            throw new EmailFormatException("email incorrect format: " + email);
        }
    }

}
