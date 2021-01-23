package contacts.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleView implements View {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String VALID_PHONE_NUMBER_FORMAT =
            "\\+?(\\([A-Za-z0-9]+\\)([\\s|-]?[A-Za-z0-9]{2,})?|[A-Za-z0-9]+[\\s|-]?(\\([A-Za-z0-9]{2,}\\))?)([\\s|-]?[A-Za-z0-9]{2,})*";

    public void println(Object obj) {
        System.out.println(obj);
    }

    public void print(Object obj) {
        System.out.print(obj);
    }

    public String readText(String prompt) {
        print(prompt);
        return scanner.nextLine().strip();
    }

    public String readGender(String prompt) {
        print(prompt);
        String gender = scanner.nextLine().strip().toUpperCase();
        if (!gender.matches("[MF]")) {
            println("Bad gender!");
            return null;
        }
        return gender;
    }

    public String readPhoneNumber(String prompt) {
        print(prompt);
        String number = scanner.nextLine().strip();
        if (isNotValidPhoneNumber(number)) {
            println("Wrong number format!");
            return null;
        } else {
            return number;
        }
    }

    private boolean isNotValidPhoneNumber(String number) {
        return !number.matches(VALID_PHONE_NUMBER_FORMAT);
    }

    public LocalDate readDate(String prompt) {
        System.out.print(prompt);
        String dateAsString = scanner.nextLine().trim();
        try {
            return LocalDate.parse(dateAsString);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            return null;
        }
    }

}
