import java.sql.SQLException;

public class ListBooks implements Action {
    @Override
    public void showInfo() {
        System.out.println("Here are all your books:");
    }

    @Override
    public void executeAction(String command) throws SQLException {

       BooksDB.listBooks();

       /* Main.books.forEach((key, book) -> {
            System.out.println("ISBN: " + key + ", Title: " + book.getTittle() + ", Author: " + book.getAuthor() + ", Quantity: " + book.getQuantity());
        });*/

    }

    @Override
    public String readUserInput() {

        return null;
    }
}
