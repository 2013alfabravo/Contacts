package contacts;

public class Contact {
    private static final String NO_NUMBER = "[no number]";

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (phoneNumber.isEmpty()) {
            this.phoneNumber = NO_NUMBER;
        } else {
            this.phoneNumber = phoneNumber;
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            this.phoneNumber = NO_NUMBER;
        } else {
            this.phoneNumber = phoneNumber;
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + phoneNumber;
    }
}
