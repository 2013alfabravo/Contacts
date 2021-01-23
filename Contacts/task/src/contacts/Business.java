package contacts;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Business extends Record {
    protected String address;

    public Business(String name,
                    String address,
                    String number,
                    LocalDateTime created) {

        super(name, number, created);
        this.name = name;
        this.address = address;
    }

    @Override
    public List<String> getEditableFieldNames() {
        List<String> fieldNames = Arrays.stream(Business.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
        fieldNames.addAll(super.getEditableFieldNames());
        fieldNames = Collections.unmodifiableList(fieldNames);
        return fieldNames;
    }

    public String getAddress() {
        return getOrDefault(address);
    }

    @Override
    public String toString() {
        return "Organization name: " + super.getName() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Number: " + super.getNumber() + "\n" +
                "Time created: " + super.getTimeCreatedAsString() + "\n" +
                "Time last edit: " + super.getTimeLastModifiedAsString();
    }

    public static Business.Builder builder() {
        return new Builder();
    }

    static class Builder {
        private String name;
        private String address;
        private String number;
        private LocalDateTime created = LocalDateTime.now();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setCreated(LocalDateTime dateTime) {
            this.created = dateTime;
            return this;
        }

        public Business build() {
            return new Business(name, address, number, created);
        }
    }
}
