package contacts.UI;

import java.time.LocalDate;

public interface View {
    // todo move prompts to controller
    void print(Object obj);
    void println(Object obj);
    String readText(String prompt);
    LocalDate readDate(String prompt);
    String readGender(String prompt);
    String readPhoneNumber(String prompt);
}
