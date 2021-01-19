package contacts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        FileHandler handler = new FileHandler("log.txt");
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);

        Scanner sc = new Scanner(System.in);

        /// prompt for use input
        System.out.print("Enter the name of the person:\n> ");
        String firstName = sc.nextLine();
        System.out.print("Enter the surname of the person:\n> ");
        String lastName = sc.nextLine();
        System.out.print("Enter the number:\n> ");
        String phoneNumber = sc.nextLine();

        Contact contact = new Contact(firstName, lastName, phoneNumber);
        System.out.println("\nA record created!");

        PhoneBook book = new PhoneBook();
        if (book.addContact(contact)) {
            System.out.println("A Phone Book with a single record created!");
        }

    }
}

class Contact {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}

class PhoneBook {
    private final List<Contact> contacts = new ArrayList<>();

    public boolean addContact(Contact contact) {
        return contacts.add(contact);
    }
}