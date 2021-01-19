package contacts;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Person person = Person.builder()
                .setName("Jane")
                .setSurname("Doe")
                .setBirthDate("1970-05-24")
                .setGender("F")
                .setNumber("123-54-74")
                .setCreated(LocalDateTime.now())
                .build();

        System.out.println(person);

//        new Application(new PhoneBook()).run();
    }
}