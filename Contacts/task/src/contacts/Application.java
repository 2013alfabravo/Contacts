package contacts;

import contacts.Menu.Menu;
import contacts.Model.Business;
import contacts.Model.EditableRecord;
import contacts.Model.Person;
import contacts.Model.SearchResult;
import contacts.UI.View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Application {
    private static final String DEFAULT_FILENAME = "book.sav";
    private final View view;
    private Menu mainMenu;
    private Menu listMenu;
    private Menu recordMenu;
    private Menu recordSelectMenu;
    private Menu searchMenu;
    private String dbFilename;
    private final PhoneBook phoneBook;
    private int selectedRecordIndex;
    private boolean isRunning;

    public Application(PhoneBook phoneBook, View view) {
        this.phoneBook = phoneBook;
        this.view = view;
        dbFilename = null;
        isRunning = true;
    }

    public Application(String filename, View view) {
        dbFilename = filename;
        this.view = view;
        this.phoneBook = loadPhoneBook(filename);
        isRunning = true;
    }

    public void run() {
        buildMenu();
        doMainLoop();
    }

    void doMainLoop() {
        while (isRunning) {
            view.print(mainMenu);
            String input = view.readText().toLowerCase();
            mainMenu.execute(input);
        }
    }

    // FIXME add input checks for item selection in lists

    private PhoneBook loadPhoneBook(String filename) {
        PhoneBook phoneBook = PhoneBook.fromFile(filename);
        if (phoneBook == null) {
            dbFilename = DEFAULT_FILENAME;
            view.println("An empty phone book " + DEFAULT_FILENAME + " created");
            return new PhoneBook();
        } else {
            view.println("Open " + filename);
            return phoneBook;
        }
    }

    private void buildMenu() {
        mainMenu = MenuFactory.buildMenu(MenuType.MAIN, this);
        listMenu = MenuFactory.buildMenu(MenuType.LIST, this);
        searchMenu = MenuFactory.buildMenu(MenuType.SEARCH, this);
        recordMenu = MenuFactory.buildMenu(MenuType.RECORD, this);
        recordSelectMenu = MenuFactory.buildMenu(MenuType.SELECT, this);
    }

    void removeRecord() {
        phoneBook.removeRecord(selectedRecordIndex);
        view.println("The record removed!");
    }

    void search() {
        if (phoneBook.getRecordsCount() == 0) {
            view.println("No records to search!");
            return;
        }

        String query = view.readSearchQuery();
        List<SearchResult> results = phoneBook.getSearchResults(query);
        view.println("Found " + results.size() + " results:");

        if (results.size() == 0) {
            return;
        }

        results.forEach(result -> view.println(result.getText()));

        view.println(searchMenu);
        String input = view.readText();
        if (input.matches("\\d")) {
            selectedRecordIndex = results.get(Integer.parseInt(input) - 1).getIndex();
        }
        searchMenu.execute(input);
    }

    void editRecord() {
        EditableRecord record = phoneBook.getRecord(selectedRecordIndex);
        view.print("Select a field (" + String.join(", ", record.getEditableFieldNames()) + "): ");
        String fieldName = view.readText();

        Object newValue;
        switch (fieldName.toLowerCase()) {
            case "birth":
                newValue = view.readDate();
                break;
            case "gender":
                newValue = view.readGender();
                break;
            case "number":
                newValue = view.readPhoneNumber();
                break;
            default:
                view.print("Enter " + fieldName + ": ");
                newValue = view.readText();
        }

        record.setFieldValue(fieldName, newValue);
        view.println("Saved");

        view.println(record);
        showRecordMenu();
    }

    void addRecord() {
        view.print(recordSelectMenu);
        String input = view.readText().toLowerCase();
        recordSelectMenu.execute(input);
    }

    void addPerson() {
        String name = view.readName();
        String surname = view.readSurname();
        LocalDate date = view.readDate();
        String gender = view.readGender();
        String number = view.readPhoneNumber();

        Person newPerson = Person.builder()
                .setName(name)
                .setSurname(surname)
                .setBirthDate(date)
                .setGender(gender)
                .setNumber(number)
                .setCreated(LocalDateTime.now())
                .build();

        phoneBook.addRecord(newPerson);
        view.println("The record added.");
    }

    void addOrganization() {
        String name = view.readBusinessName();
        String address = view.readAddress();
        String number = view.readPhoneNumber();

        Business newBusiness = Business.builder()
                .setName(name)
                .setAddress(address)
                .setNumber(number)
                .setCreated(LocalDateTime.now())
                .build();

        phoneBook.addRecord(newBusiness);
        view.println("The record added.");
    }

    void listRecords() {
        if (phoneBook.getRecordsCount() == 0) {
            view.println("No records to list!");
            return;
        }

        List<String> list = phoneBook.getRecordsList();
        list.forEach(view::println);
        showListMenu();
    }

    private void showListMenu() {
        view.print(listMenu);
        String input = view.readText();
        if (input.matches("\\d")) {
            selectedRecordIndex = Integer.parseInt(input) - 1;
        }

        listMenu.execute(input);
    }

    void showRecord() {
        view.println(phoneBook.getRecordAsString(selectedRecordIndex));
        showRecordMenu();
    }

    private void showRecordMenu() {
        view.print(recordMenu);
        String input = view.readText();
        recordMenu.execute(input);
    }

    void getRecordsCount() {
        int count = phoneBook.getRecordsCount();
        view.println("The Phone Book has " + count + " records.");
    }

    void exit() {
        if (dbFilename != null) {
            phoneBook.save(dbFilename);
        }
        isRunning = false;
    }
}
