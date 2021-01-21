package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person extends Record {
    private String surname;
    private LocalDate birthDate;
    private String gender;

    private Person(String name,
                   String surname,
                   LocalDate birthDate,
                   String gender,
                   String number,
                   LocalDateTime created) {

        super(name, number, created);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getSurname() {
        return getOrDefault(surname);
    }

    public void setSurname(String surname) {
        this.surname = surname;
        updateLastModified();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        updateLastModified();
    }

    public String getGender() {
        return getOrDefault(gender);
    }

    public void setGender(String gender) {
        this.gender = gender;
        updateLastModified();
    }

    @Override
    public String getName() {
        return name + " " + surname;
    }

    @Override
    public Boolean isPerson() {
        return true;
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
        return this.birthDate == null ? "[no data]" : this.birthDate.toString();
    }

    public static Person.Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String surname;
        private LocalDate birthDate;
        private String gender;
        private String number;
        private LocalDateTime created;

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
