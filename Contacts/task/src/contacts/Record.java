package contacts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Record {
    protected static final String NO_DATA = "[no data]";

    protected String name;
    protected LocalDateTime created;
    protected LocalDateTime lastModified;
    protected String number;

    protected Record(String name, String number, LocalDateTime created) {
        this.name = name;
        this.created = created;
        this.lastModified = created;
        this.number = number;
    }

    public Boolean isPerson() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
        updateLastModified();
    }

    public String getName() {
        return getOrDefault(name);
    }

    public String getNumber() {
        return getOrDefault(number);
    }

    public void setNumber(String number) {
        this.number = number;
        updateLastModified();
    }

    protected void updateLastModified() {
        lastModified = LocalDateTime.now();
    }

    public String getTimeCreatedAsString() {
        return created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm"));
    }

    public String getTimeLastModifiedAsString() {
        return lastModified.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm"));
    }

    protected String getOrDefault(String field) {
        return field == null || field.isBlank() ? NO_DATA : field;
    }
}
