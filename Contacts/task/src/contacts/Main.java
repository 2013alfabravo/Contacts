package contacts;

public class Main {

    public static void main(String[] args) {
        Person person = Person.builder().build();
        person.setFieldValue("name", "MODIFIED!");
        System.out.println(person.getFieldValue("name"));
//        new Application(new PhoneBook()).run();
    }
}