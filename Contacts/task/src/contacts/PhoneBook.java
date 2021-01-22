package contacts;

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

    public EditableRecord getRecord(int index) {
        return records.get(index);
    }

    public void removeRecord(int index) {
        records.remove(index);
    }

}
