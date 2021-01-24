package contacts;

import contacts.Menu.Menu;

public class MenuFactory {
    private MenuFactory() {  }

    public static Menu buildMenu(MenuType type, Application context) {
        switch (type) {
            case MAIN:
                return buildMainMenu(context);
            case LIST:
                return buildListMenu(context);
            case SEARCH:
                return buildSearchMenu(context);
            case RECORD:
                return buildRecordMenu(context);
            case SELECT:
                return buildSelectMenu(context);
            default:
                return new Menu();
        }
    }

    private static Menu buildMainMenu(Application context) {
        return new Menu().setTitle("\n[menu] Enter action")
                .addMenuItem("add", context::addRecord)
                .addMenuItem("list", context::listRecords)
                .addMenuItem("search", context::search)
                .addMenuItem("count", context::getRecordsCount)
                .addMenuItem("exit", context::exit);
    }

    private static Menu buildListMenu(Application context) {
        return new Menu().setTitle("\n[list] Enter action")
                .addMenuItem("[number]", "\\d+", context::showRecord)
                .addMenuItem("back", context::doMainLoop);
    }

    private static Menu buildSearchMenu(Application context) {
        return new Menu().setTitle("\n[search] Enter action")
                .addMenuItem("[number]", "\\d+", context::showRecord)
                .addMenuItem("back", context::doMainLoop)
                .addMenuItem("again", context::search);
    }

    private static Menu buildRecordMenu(Application context) {
        return new Menu().setTitle("\n[record] Enter action")
                .addMenuItem("edit", context::editRecord)
                .addMenuItem("delete", context::removeRecord)
                .addMenuItem("menu", context::doMainLoop);
    }

    private static Menu buildSelectMenu(Application context) {
        return new Menu().setTitle("Enter the type")
                .addMenuItem("person", context::addPerson)
                .addMenuItem("organization", context::addOrganization);
    }
}
