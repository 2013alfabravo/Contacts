package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    private Menu mainMenu;
    private Menu listMenu;
    private Menu recordMenu;
    private Menu recordSelectMenu;
    private Menu searchMenu;
    private final PhoneBook phoneBook;
    private int selectedRecordIndex;
    private boolean isRunning;

    public Application(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.isRunning = true;
    }

    public void run() {
        buildMenu();
        doMainLoop();
    }

    private void doMainLoop() {
        while (isRunning) {
            System.out.print(mainMenu);
            String input = scanner.nextLine().toLowerCase().strip();
            mainMenu.execute(input);
        }
    }

    // TODO consider introducing ListView, RecordEditor and SearchEngine classes to make this one less bloated

    // FIXME add input checks for item selection in lists

    private void buildMenu() {
        mainMenu = new Menu().setTitle("\n[menu] Enter action")
                .addMenuItem("add", this::addRecord)
                .addMenuItem("list", this::listRecords)
                .addMenuItem("search", this::search)
                .addMenuItem("count", this::getContactsCount)
                .addMenuItem("exit", this::exit);

        listMenu = new Menu().setTitle("\n[list] Enter action")
                .addMenuItem("[number]", "\\d", this::showRecord)
                .addMenuItem("back", this::doMainLoop);

        searchMenu = new Menu().setTitle("\n[search] Enter action")
                .addMenuItem("[number]", "\\d", this::showRecord)
                .addMenuItem("back", this::doMainLoop)
                .addMenuItem("again", this::search);

        recordMenu = new Menu().setTitle("\n[record] Enter action")
                .addMenuItem("edit", this::editRecord)
                .addMenuItem("delete", this::removeRecord)
                .addMenuItem("menu", this::doMainLoop);

        recordSelectMenu = new Menu().setTitle("Enter the type")
                .addMenuItem("person", this::addPerson)
                .addMenuItem("organization", this::addOrganization);
    }

    private void removeRecord() {
        phoneBook.removeRecord(selectedRecordIndex);
        System.out.println("The record removed!");
    }

    private void search() {
        if (phoneBook.getRecordsCount() == 0) {
            System.out.println("No records to search!");
            return;
        }

        String query = readString("Enter search query: ");
        List<SearchResult> results = phoneBook.getSearchResults(query);
        System.out.println("Found " + results.size() + " results:");

        if (results.size() == 0) {
            return;
        }

        results.forEach(result -> System.out.println(result.getText()));

        System.out.print(searchMenu);
        String input = readString("");
        if (input.matches("\\d")) {
            selectedRecordIndex = results.get(Integer.parseInt(input) - 1).getIndex();
        }
        searchMenu.execute(input);
    }

    private void editRecord() {
        EditableRecord record = phoneBook.getRecord(selectedRecordIndex);
        String fieldName = readString("Select a field (" +
                String.join(", ", record.getEditableFieldNames()) + "): ");

        Object newValue;
        switch (fieldName) {
            case "birth":
                newValue = readDate();
                break;
            case "gender":
                newValue = readGender();
                break;
            case "number":
                newValue = readPhoneNumber();
                break;
            default:
                newValue = readString("Enter " + fieldName + ": ");
        }

        record.setFieldValue(fieldName, newValue);
        System.out.println("Saved");

        System.out.println(record);
        showRecordMenu();
    }

    private void addRecord() {
        System.out.print(recordSelectMenu);
        String input = scanner.nextLine().strip().toLowerCase();
        recordSelectMenu.execute(input);
    }

    private void addPerson() {
        String name = readString("Enter the name: ");
        String surname = readString("Enter the surname: ");
        LocalDate date = readDate();
        String gender = readGender();
        String number = readPhoneNumber();

        Person newPerson = Person.builder()
                .setName(name)
                .setSurname(surname)
                .setBirthDate(date)
                .setGender(gender)
                .setNumber(number)
                .setCreated(LocalDateTime.now())
                .build();

        phoneBook.addRecord(newPerson);
        System.out.println("The record added.");
    }

    private void addOrganization() {
        String name = readString("Enter the organization name: ");
        String address = readString("Enter the address: ");
        String number = readPhoneNumber();

        Business newBusiness = Business.builder()
                .setName(name)
                .setAddress(address)
                .setNumber(number)
                .setCreated(LocalDateTime.now())
                .build();

        phoneBook.addRecord(newBusiness);
        System.out.println("The record added.");
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().strip();
    }

    private String readGender() {
        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine().strip().toUpperCase();
        if (!gender.matches("[MF]")) {
            System.out.println("Bad gender!");
            return null;
        }
        return gender;
    }

    private String readPhoneNumber() {
        System.out.print("Enter the number: ");
        String number = scanner.nextLine().strip();
        if (PhoneBook.isNotValidNumber(number)) {
            System.out.println("Wrong number format!");
            return null;
        } else {
            return number;
        }
    }

    private LocalDate readDate() {
        System.out.print("Enter the birth date: ");
        String dateAsString = scanner.nextLine().trim();
        try {
            return LocalDate.parse(dateAsString);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            return null;
        }
    }

    private void listRecords() {
        if (phoneBook.getRecordsCount() == 0) {
            System.out.println("No records to list!");
            return;
        }

        List<String> list = phoneBook.getRecordsList();
        list.forEach(System.out::println);
        showListMenu();
    }

    private void showListMenu() {
        System.out.print(listMenu);
        String input = readString("");
        if (input.matches("\\d")) {
            selectedRecordIndex = Integer.parseInt(input) - 1;
        }

        listMenu.execute(input);
    }

    private void showRecord() {
        System.out.println(phoneBook.getRecordAsString(selectedRecordIndex));
        showRecordMenu();
    }

    private void showRecordMenu() {
        System.out.print(recordMenu);
        String input = readString("");
        recordMenu.execute(input);
    }

    private void getContactsCount() {
        int count = phoneBook.getRecordsCount();
        System.out.println("The Phone Book has " + count + " records.");
    }

    private void exit() {
        isRunning = false;
    }
}
