package contacts.UI;

import java.time.LocalDate;

public interface View {
    void print(Object obj);
    void println(Object obj);
    String readName();
    String readSurname();
    String readAddress();
    String readBusinessName();
    String readSearchQuery();
    String readText();
    LocalDate readDate();
    String readGender();
    String readPhoneNumber();
}
