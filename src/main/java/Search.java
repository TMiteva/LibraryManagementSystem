import java.sql.SQLException;
import java.util.Scanner;

public class Search implements Action{
    @Override
    public void showInfo() {
        System.out.println("Enter the ISBN for book you want to search for.");
    }

    @Override
    public void executeAction(String command) throws SQLException {
        BooksDB.searchBook(command);
        /*int flag = 0;

        for (Map.Entry<String, Book> entry : Main.books.entrySet()) {
            System.out.println(entry);
            System.out.println(command);
            if (Objects.equals(entry.getKey(), command)) {
                System.out.println("Here is the book you searched for.");

                flag = 1;
            }
        }
        if(flag == 0) {
            System.out.println("The book you searched for doesn't exist.");
        }*/
    }

    @Override
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
