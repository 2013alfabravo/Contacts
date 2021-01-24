package contacts.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleView implements View {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String VALID_PHONE_NUMBER_FORMAT =
            "\\+?(\\([A-Za-z0-9]+\\)([\\s|-]?[A-Za-z0-9]{2,})?|[A-Za-z0-9]+[\\s|-]?(\\([A-Za-z0-9]{2,}\\))?)([\\s|-]?[A-Za-z0-9]{2,})*";

    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }

    @Override
    public String readText() {
        return scanner.nextLine().strip();
    }

    @Override
    public String readGender() {
        print("Enter the gender (M, F): ");
        String gender = scanner.nextLine().strip().toUpperCase();
        if (!gender.matches("[MF]")) {
            println("Bad gender!");
            return null;
        }
        return gender;
    }

    @Override
    public String readPhoneNumber() {
        print("Enter the number: ");
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

    @Override
    public LocalDate readDate() {
        print("Enter the birth date: ");
        String dateAsString = scanner.nextLine().strip();
        try {
            return LocalDate.parse(dateAsString);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            return null;
        }
    }

    @Override
    public String readBusinessName() {
        print("Enter the organization name: ");
        return readText();
    }

    @Override
    public String readName() {
        print("Enter the name: ");
        return readText();
    }

    @Override
    public String readSurname() {
        print("Enter the surname: ");
        return readText();
    }

    @Override
    public String readAddress() {
        print("Enter the address: ");
        return readText();
    }

    @Override
    public String readSearchQuery() {
        print("Enter search query: ");
        return readText();
    }
}
