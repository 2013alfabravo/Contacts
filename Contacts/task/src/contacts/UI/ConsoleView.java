package contacts.UI;

import contacts.PhoneBook;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleView {
    private static final Scanner scanner = new Scanner(System.in);

    public void println(Object obj) {
        System.out.println(obj);
    }

    public void print(Object obj) {
        System.out.print(obj);
    }

    public String readString(String prompt) {
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
        if (PhoneBook.isNotValidNumber(number)) {
            println("Wrong number format!");
            return null;
        } else {
            return number;
        }
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
