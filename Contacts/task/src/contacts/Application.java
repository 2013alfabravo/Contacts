package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    private Menu mainMenu;
    private Menu personEditMenu;
    private Menu businessEditMenu;
    private Menu recordSelectMenu;
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
                .addMenuItem("info", this::getInfo)
                .addMenuItem("exit", this::exit);

        personEditMenu = new Menu().setTitle("Select a field")
                .addMenuItem("name", this::updateName)
                .addMenuItem("surname", this::updateSurname)
                .addMenuItem("birth", this::updateBirthDate)
                .addMenuItem("gender", this::updateGender)
                .addMenuItem("number", this::updateNumber);

        businessEditMenu = new Menu().setTitle("Select a field")
                .addMenuItem("name", this::updateName)
                .addMenuItem("address", this::updateAddress)
                .addMenuItem("number", this::updateNumber);

        recordSelectMenu = new Menu().setTitle("Enter the type")
                .addMenuItem("person", this::addPerson)
                .addMenuItem("organization", this::addOrganization);

    }

    private void removeContact() {
        if (phoneBook.getRecordsCount() == 0) {
            System.out.println("No records to remove!\n");
            return;
        }

        readContactId();
        if (selectedRecordIndex == -1) {
            return;
        }

        phoneBook.removeRecord(selectedRecordIndex);
        System.out.println("The record removed!\n");
    }

    private void editContact() {
        if (phoneBook.getRecordsCount() == 0) {
            System.out.println("No records to edit!\n");
            return;
        }

        readContactId();
        if (selectedRecordIndex == -1) {
            return;
        }

        Menu activeMenu = personEditMenu; // fixme got rid of isPerson() and needs fixing the logic
        System.out.print(activeMenu);
        String input = scanner.nextLine().toLowerCase().strip();
        activeMenu.execute(input);
    }

    private void updateNumber() {
        String newNumber = readPhoneNumber("Enter number: ");
        phoneBook.updateNumber(selectedRecordIndex, newNumber);
        System.out.println("The record updated!\n");
    }

    private void updateSurname() {
        String newSurname = readString("Enter surname: ");
        phoneBook.updateSurname(selectedRecordIndex, newSurname);
        System.out.println("The record updated!\n");
    }

    private void updateBirthDate() {
        LocalDate newDate = readDate("Enter birthdate: ");
        phoneBook.updateBirthDate(selectedRecordIndex, newDate);
        System.out.println("The record updated!\n");
    }

    private void updateGender() {
        String newGender = readGender("Enter gender (M, F): ");
        phoneBook.updateGender(selectedRecordIndex, newGender);
        System.out.println("The record updated!\n");
    }

    private void updateName() {
        String newName = readString("Enter name: ");
        phoneBook.updateName(selectedRecordIndex, newName);
        System.out.println("The record updated!\n");
    }

    private void updateAddress() {
        String newAddress = readString("Enter address: ");
        phoneBook.updateAddress(selectedRecordIndex, newAddress);
        System.out.println("The record updated!\n");
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
        System.out.print(recordSelectMenu);
        String input = scanner.nextLine().strip().toLowerCase();
        recordSelectMenu.execute(input);
    }

    private void addPerson() {
        String name = readString("Enter the name: ");
        String surname = readString("Enter the surname: ");
        LocalDate date = readDate("Enter the birth date: ");
        String gender = readGender("Enter the gender (M, F): ");
        String number = readPhoneNumber("Enter the number: ");

        Person newPerson = Person.builder()
                .setName(name)
                .setSurname(surname)
                .setBirthDate(date)
                .setGender(gender)
                .setNumber(number)
                .setCreated(LocalDateTime.now())
                .build();

        phoneBook.addRecord(newPerson);
        System.out.println("The record added.\n");
    }

    private void addOrganization() {
        String name = readString("Enter the organization name: ");
        String address = readString("Enter the address: ");
        String number = readPhoneNumber("Enter the number: ");

        Business newBusiness = Business.builder()
                .setName(name)
                .setAddress(address)
                .setNumber(number)
                .setCreated(LocalDateTime.now())
                .build();

        phoneBook.addRecord(newBusiness);
        System.out.println("The record added.\n");
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().strip();
    }

    private String readGender(String prompt) {
        System.out.print(prompt);
        String gender = scanner.nextLine().strip().toUpperCase();
        if (!gender.matches("[MF]")) {
            System.out.println("Bad gender!");
            return null;
        }
        return gender;
    }

    private String readPhoneNumber(String prompt) {
        System.out.print(prompt);
        String number = scanner.nextLine().strip();
        if (PhoneBook.isNotValidNumber(number)) {
            System.out.println("Wrong number format!");
            return null;
        } else {
            return number;
        }
    }

    private LocalDate readDate(String prompt) {
        System.out.print(prompt);
        String dateAsString = scanner.nextLine().trim();
        try {
            return LocalDate.parse(dateAsString);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            return null;
        }
    }

    private void getInfo() {
        if (phoneBook.getRecordsCount() == 0) {
            System.out.println("No records to list!\n");
            return;
        }

        readContactId();
        System.out.println(phoneBook.getRecord(selectedRecordIndex) + "\n");
    }

    private void listContacts() {
        List<String> list = phoneBook.getRecordsList();
        list.forEach(System.out::println);
    }

    private void getContactsCount() {
        int count = phoneBook.getRecordsCount();
        System.out.println("The Phone Book has " + count + " records.\n");
    }

    private void exit() {
        isRunning = false;
    }
}
