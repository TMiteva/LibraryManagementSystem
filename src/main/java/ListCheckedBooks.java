import java.sql.SQLException;

public class ListCheckedBooks implements Action {
    @Override
    public void showInfo() {
        System.out.println("List all checked books by students.");
    }

    @Override
    public void executeAction(String command) throws SQLException {
        BooksDB.listCheckedBooks();
    }

    @Override
    public String readUserInput() {
        return null;
    }
}
