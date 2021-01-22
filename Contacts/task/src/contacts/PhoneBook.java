package contacts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private static final String VALID_PHONE_NUMBER_FORMAT =
            "\\+?(\\([A-Za-z0-9]+\\)([\\s|-]?[A-Za-z0-9]{2,})?|[A-Za-z0-9]+[\\s|-]?(\\([A-Za-z0-9]{2,}\\))?)([\\s|-]?[A-Za-z0-9]{2,})*";

    private final List<EditableRecord> records = new ArrayList<>();

    public static boolean isNotValidNumber(String number) {
        return !number.matches(VALID_PHONE_NUMBER_FORMAT);
    }

    public void addRecord(EditableRecord contact) {
        records.add(contact);
    }

    public int getRecordsCount() {
        return records.size();
    }

    public String getRecordAsString(int index) {
        return records.get(index).toString();
    }

    public List<String> getRecordsList() {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < records.size(); index++) {
            String item = index + 1 + ". " + records.get(index).getFullName();
            list.add(item);
        }

        return list;
    }

    public void removeRecord(int index) {
        records.remove(index);
    }

    // TODO should replace with one generic method 'updateField'
    public void updateName(int index, String newName) {
        records.get(index).setFieldValue("name", newName);
    }

    public void updateSurname(int index, String newSurname) {
        records.get(index).setFieldValue("surname", newSurname);
    }

    public void updateNumber(int index, String newNumber) {
        records.get(index).setFieldValue("number", newNumber);
    }

    public void updateAddress(int index, String newAddress) {
        records.get(index).setFieldValue("address", newAddress);
    }

    public void updateBirthDate(int index, LocalDate newDate) {
        records.get(index).setFieldValue("birth", newDate);
    }

    public void updateGender(int index, String gender) {
        records.get(index).setFieldValue("gender", gender);
    }
}
