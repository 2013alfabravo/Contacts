package contacts;

import contacts.UI.ConsoleView;

public class Main {

    public static void main(String[] args) {
        // TODO add file loading from args and file saving on exit

        new Application(new PhoneBook(), new ConsoleView()).run();
    }
}