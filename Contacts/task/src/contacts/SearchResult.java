package contacts;

public class SearchResult {
    private final String text;
    private final int index;

    public SearchResult(int id, String text, int index) {
        this.text = id + ". " + text;
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }
}
