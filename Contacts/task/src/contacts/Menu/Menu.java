package contacts.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Menu {
    private String title;
    private final List<MenuItem> items = new ArrayList<>();

    private static class MenuItem {
        private final String title;
        private final String actionKey;
        private final Action action;

        private MenuItem(String title, String actionKey, Action action) {
            this.title = title;
            this.actionKey = actionKey;
            this.action = action;
        }

        private void execute() {
            action.execute();
        }
    }

    public Menu setTitle(String title) {
        this.title = title;
        return this;
    }

    public Menu addMenuItem(String title, Action action) {
        items.add(new MenuItem(title, title, action));
        return this;
    }

    public Menu addMenuItem(String title, String actionKey, Action action) {
        items.add(new MenuItem(title, actionKey, action));
        return this;
    }

    public void execute(String input) {
        Optional<MenuItem> item = items.stream()
                .filter(it -> Pattern.matches(it.actionKey, input))
                .findFirst();

        item.ifPresent(MenuItem::execute);
    }

    @Override
    public String toString() {
        return title + " " +
                items.stream()
                        .map(it -> it.title)
                        .collect(Collectors.joining(
                                ", ", "(", ")")) + ": ";
    }
}
