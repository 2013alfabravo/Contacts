package contacts;

public class Record {
    protected String created;
    protected String lastModified;
    protected String number;

    protected Record(String number, String created) {
        this.created = created;
        this.lastModified = created;
        this.number = number;
    }
}
