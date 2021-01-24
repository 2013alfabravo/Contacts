package contacts;

import contacts.UI.ConsoleView;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1) {
            new Application(args[0], new ConsoleView()).run();
        } else {
            new Application(new PhoneBook(), new ConsoleView()).run();
        }
    }
}