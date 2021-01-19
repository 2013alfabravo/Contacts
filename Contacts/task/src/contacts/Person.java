package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Person extends Record {
    private String name;
    private String surname;
    private String birthDate;
    private String gender;
    private boolean isPerson = true;

    private Person(String name,
                   String surname,
                   String birthDate,
                   String gender,
                   String number,
                   String created) {

        super(number, created);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + '\n' +
                "Birth date: " + birthDate + "\n" +
                "Gender: " + gender + '\n' +
                "Number: " + super.number + "\n" +
                "Time created: " + super.created + "\n" +
                "Time last edit: " + super.lastModified;
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private String name;
        private String surname;
        private String birthDate;
        private String gender;
        private String number;
        private String created;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setBirthDate(String date) {
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
            this.created = dateTime.truncatedTo(ChronoUnit.MINUTES).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return this;
        }

        public Person build() {
            return new Person(name, surname, birthDate, gender, number, created);
        }
    }

}
