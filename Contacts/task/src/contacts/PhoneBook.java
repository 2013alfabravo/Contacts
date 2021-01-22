package contacts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private static final String VALID_PHONE_NUMBER_FORMAT =
            "\\+?(\\([A-Za-z0-9]+\\)([\\s|-]?[A-Za-z0-9]{2,})?|[A-Za-z0-9]+[\\s|-]?(\\([A-Za-z0-9]{2,}\\))?)([\\s|-]?[A-Za-z0-9]{2,})*";

    private final List<Record> contacts = new ArrayList<>();

    public static boolean isNotValidNumber(String number) {
        return !number.matches(VALID_PHONE_NUMBER_FORMAT);
    }

    public void addRecord(Record contact) {
        contacts.add(contact);
    }

    public int getRecordsCount() {
        return contacts.size();
    }

    public String getRecord(int index) {
        return contacts.get(index).toString();
    }

    public List<String> getRecordsList() {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < contacts.size(); index++) {
            String item = index + 1 + ". " + contacts.get(index).getName();
            list.add(item);
        }

        return list;
    }

    public void removeRecord(int index) {
        contacts.remove(index);
    }

    public void updateName(int index, String newName) {
        Record record = contacts.get(index);
        record.setName(newName);
    }

    public void updateSurname(int index, String newSurname) {
        Person person = (Person) contacts.get(index);
        person.setSurname(newSurname);
    }

    public void updateNumber(int index, String newNumber) {
        Record record = contacts.get(index);
        record.setNumber(newNumber);
    }

    public void updateAddress(int index, String newAddress) {
        Business business = (Business) contacts.get(index);
        business.setAddress(newAddress);
    }

    public void updateBirthDate(int index, LocalDate newDate) {
        Person person = (Person) contacts.get(index);
        person.setBirth(newDate);
    }

    public void updateGender(int index, String gender) {
        Person person = (Person) contacts.get(index);
        person.setGender(gender);
    }
}
