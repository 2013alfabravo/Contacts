package contacts.Model;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class Record implements EditableRecord {
    protected static final String NO_DATA = "[no data]";

    protected String name;
    protected String number;
    protected LocalDateTime created;
    protected LocalDateTime lastModified;

    protected Record(String name, String number, LocalDateTime created) {
        this.name = name;
        this.created = created == null ? LocalDateTime.now() : created;
        this.lastModified = this.created;
        this.number = number;
    }

    public List<String> getEditableFieldNames() {
        return List.of("name", "number");
    }

    @Override
    public <T> void setFieldValue(String fieldName, T value) {
        Field requiredField = getFieldByName(fieldName);

        try {
            requiredField.setAccessible(true);
            requiredField.set(this, value);
            updateLastModified();
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Illegal access to field " + fieldName + ", Reason: " + e.getMessage());
        }
    }

    @Override
    public Object getFieldValue(String fieldName) {
        Field requiredField = getFieldByName(fieldName);

        try {
            return requiredField.get(this);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Illegal access to field " + fieldName + ", Reason: " + e.getMessage());
        }
    }

    private Field getFieldByName(String fieldName) {
        Class<?> currentClass = this.getClass();
        Optional<Field> requiredField = Optional.empty();

        while (currentClass != Object.class) {
            requiredField = Arrays.stream(currentClass.getDeclaredFields())
                    .filter(field -> field.getName().equals(fieldName))
                    .findFirst();

            if (requiredField.isPresent()) {
                break;
            } else {
                currentClass = currentClass.getSuperclass();
            }
        }

        if (requiredField.isEmpty()) {
            throw new IllegalArgumentException("No such field: " + fieldName);
        }

        return requiredField.get();
    }

    public String getName() {
        return getOrDefault(name);
    }

    @Override
    public String getFullName() {
        return getName();
    }

    public String getNumber() {
        return getOrDefault(number);
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
