package contacts;

import java.time.LocalDateTime;

public class Business extends Record {
    private String address;

    private Business(String name,
                    String address,
                    String number,
                    LocalDateTime created) {

        super(name, number, created);
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return getOrDefault(address);
    }

    public void setAddress(String address) {
        this.address = address;
        updateLastModified();
    }

    @Override
    public Boolean isPerson() {
        return false;
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
        private LocalDateTime created;

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
