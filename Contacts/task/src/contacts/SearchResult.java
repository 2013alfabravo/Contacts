package contacts;

public class SearchResult {
    private final int id;
    private final String text;
    private final int index;

    public SearchResult(int id, String text, int index) {
        this.id = id;
        this.text = this.id + ". " + text;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }
}
