package contacts.Model;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Person extends Record {
    protected String surname;
    protected LocalDate birth;
    protected String gender;

    public Person(String name,
                   String surname,
                   LocalDate birth,
                   String gender,
                   String number,
                   LocalDateTime created) {

        super(name, number, created);
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.gender = gender;
    }

    @Override
    public List<String> getEditableFieldNames() {
        List<String> fieldNames = Arrays.stream(Person.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
        fieldNames.addAll(super.getEditableFieldNames());
        fieldNames = Collections.unmodifiableList(fieldNames);
        return fieldNames;
    }

    public String getSurname() {
        return getOrDefault(surname);
    }

    public String getGender() {
        return getOrDefault(gender);
    }

    @Override
    public String getFullName() {
        return name + " " + surname;
    }

    @Override
    public String toString() {
        return "Name: " + super.getName() + "\n" +
                "Surname: " + getSurname() + '\n' +
                "Birth date: " + getBirthDateAsString() + "\n" +
                "Gender: " + getGender() + '\n' +
                "Number: " + super.getNumber() + "\n" +
                "Time created: " + super.getTimeCreatedAsString() + "\n" +
                "Time last edit: " + super.getTimeLastModifiedAsString();
    }

    public String getBirthDateAsString() {
        return this.birth == null ? NO_DATA : this.birth.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String surname;
        private LocalDate birthDate;
        private String gender;
        private String number;
        private LocalDateTime created = LocalDateTime.now();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setBirthDate(LocalDate date) {
            this.birthDate = date;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
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

        public Person build() {
            return new Person(name, surname, birthDate, gender, number, created);
        }
    }

}
