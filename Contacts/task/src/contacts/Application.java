package contacts;

import java.util.List;
import java.util.Scanner;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    private Menu mainMenu;
    private Menu editMenu;
    private final PhoneBook phoneBook;
    private int selectedRecordIndex;
    private boolean isRunning;

    public Application(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.isRunning = true;
    }

    public void run() {
        buildMenu();

        while (isRunning) {
            System.out.print(mainMenu);
            String input = scanner.nextLine().toLowerCase().strip();
            mainMenu.execute(input);
        }
    }

    private void buildMenu() {
        mainMenu = new Menu().setTitle("Enter action")
                .addMenuItem("add", this::addContact)
                .addMenuItem("remove", this::removeContact)
                .addMenuItem("edit", this::editContact)
                .addMenuItem("count", this::getContactsCount)
                .addMenuItem("list", this::listContacts)
                .addMenuItem("exit", this::exit);

        editMenu = new Menu().setTitle("Select a field")
                .addMenuItem("name", this::updateName)
                .addMenuItem("surname", this::updateSurname)
                .addMenuItem("number", this::updateNumber);

    }

    private void removeContact() {
        if (phoneBook.getRecordsCount() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        readContactId();
        if (selectedRecordIndex == -1) {
            return;
        }

        if (phoneBook.removeContact(selectedRecordIndex)) {
            System.out.println("The record removed!");
        }
    }

    private void editContact() {
        if (phoneBook.getRecordsCount() == 0) {
            System.out.println("No records to edit!");
            return;
        }

        readContactId();
        if (selectedRecordIndex == -1) {
            return;
        }

        System.out.print(editMenu);
        String input = scanner.nextLine().toLowerCase().strip();
        editMenu.execute(input);
    }

    private void updateNumber() {
        System.out.print("Enter number: ");
        String newNumber = scanner.nextLine();
        if (PhoneBook.isNotValidNumber(newNumber)) {
            System.out.println("Wrong number format!");
            newNumber = "";
        }
        if (phoneBook.updatePhoneNumber(selectedRecordIndex, newNumber)) {
            System.out.println("The record updated!");
        }
    }

    private void updateSurname() {
        System.out.print("Enter surname: ");
        String newSurname = scanner.nextLine();
        if (phoneBook.updateLastName(selectedRecordIndex, newSurname)) {
            System.out.println("The record updated!");
        }
    }

    private void updateName() {
        System.out.print("Enter name: ");
        String newName = scanner.nextLine();
        if (phoneBook.updateFirstName(selectedRecordIndex, newName)) {
            System.out.println("The record updated!");
        }
    }

    private void readContactId() {
        if (phoneBook.getRecordsCount() == 0) {
            selectedRecordIndex = -1;
            return;
        }

        listContacts();
        System.out.print("Select a record: ");
        selectedRecordIndex = Integer.parseInt(scanner.nextLine()) - 1;
    }

    private void addContact() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();

        if (PhoneBook.isNotValidNumber(number)) {
            number = "";
            System.out.println("Wrong number format!");
        }

        if (phoneBook.addContact(new Contact(name, surname, number))) {
            System.out.println("The record added.");
        }
    }

    private void listContacts() {
        List<String> list = phoneBook.getContactsList();
        if (list.isEmpty()) {
            System.out.println("No records to list!");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void getContactsCount() {
        int count = phoneBook.getRecordsCount();
        System.out.println("The Phone Book has " + count + " records.");
    }

    private void exit() {
        isRunning = false;
    }
}
