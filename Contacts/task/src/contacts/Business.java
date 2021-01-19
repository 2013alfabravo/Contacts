package contacts;

import java.time.LocalDateTime;

public class Business extends Record {
    private String name;
    private String address;

    public Business(String name,
                    String address,
                    String number,
                    String created) {

        super(number, created);
        this.name = name;
        this.address = address;
    }
}
