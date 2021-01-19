package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private static final String VALID_PHONE_NUMBER_FORMAT =
            "\\+?(\\([A-Za-z0-9]+\\)([\\s|-]?[A-Za-z0-9]{2,})?|[A-Za-z0-9]+[\\s|-]?(\\([A-Za-z0-9]{2,}\\))?)([\\s|-]?[A-Za-z0-9]{2,})*";
    private final List<Contact> contacts = new ArrayList<>();

    public static boolean isNotValidNumber(String number) {
        return !number.matches(VALID_PHONE_NUMBER_FORMAT);
    }

    public boolean addContact(Contact contact) {
        contacts.add(contact);
        return true;
    }

    public int getRecordsCount() {
        return contacts.size();
    }

    public List<String> getContactsList() {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < contacts.size(); index++) {
            String item = index + 1 + "." + " " + contacts.get(index).toString();
            list.add(item);
        }

        return list;
    }

    public boolean removeContact(int index) {
        contacts.remove(index);
        return true;
    }

    public boolean updateFirstName(int index, String newName) {
        contacts.get(index).setFirstName(newName);
        return true;
    }

    public boolean updateLastName(int index, String newSurname) {
        contacts.get(index).setLastName(newSurname);
        return true;
    }

    public boolean updatePhoneNumber(int index, String newNumber) {
        contacts.get(index).setPhoneNumber(newNumber);
        return true;
    }
}
