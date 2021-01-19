package contacts;

import java.time.LocalDateTime;

public class Record {
    private LocalDateTime created;
    private LocalDateTime lastModified;
    private String phoneNumber;

    public Record(String phoneNumber, LocalDateTime created) {
        this.created = created;
        this.lastModified = created;
        this.phoneNumber = phoneNumber;
    }
}
