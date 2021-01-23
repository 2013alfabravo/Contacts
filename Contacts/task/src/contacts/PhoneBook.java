package contacts;

import contacts.Model.EditableRecord;
import contacts.Model.Record;
import contacts.Model.SearchResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID = 20210124L;
    private final List<EditableRecord> records = new ArrayList<>();

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
            String fullName = records.get(index).getFullName();
            String item = index + 1 + ". " + fullName;
            list.add(item);
        }

        return list;
    }

    public List<SearchResult> getSearchResults(String query) {
        List<SearchResult> results = new ArrayList<>();
        int counter = 1;
        String regex = "(?i).*" + query + ".*";

        for (int index = 0; index < records.size(); index++) {
            EditableRecord record = records.get(index);
            String fullName = record.getFullName();
            String phoneNumber = ((Record) records.get(index)).getNumber().replaceAll("[+\\-\\s()]", "");

            if (Pattern.matches(regex, fullName) || Pattern.matches(regex, phoneNumber)) {
                results.add(new SearchResult(counter++, fullName, index));
            }
        }

        return results;
    }

    public EditableRecord getRecord(int index) {
        return records.get(index);
    }

    public void removeRecord(int index) {
        records.remove(index);
    }

    public void save(String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed to write " + filename + ". Reason: " + e.getMessage());
        }
    }

    public static PhoneBook fromFile(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
            return  (PhoneBook) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to read " + filename + ". Reason: " + e.getMessage());
            return null;
        }
    }
}
