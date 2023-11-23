import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class CheckIn implements Action{
    @Override
    public void showInfo() {
        System.out.println("Enter the name of the book you want to check in:");
    }

    @Override
    public void executeAction(String command) throws SQLException {
        BooksDB.checkInBook(command);

        /*int flag = 0;
        for(Map.Entry<String, Book> entry : Main.books.entrySet()){
            if(entry.getValue().getTittle().toLowerCase().equals(command)) {
                int bookNum = Integer.parseInt(entry.getValue().getQuantity());
                bookNum++;
                entry.getValue().setQuantity(String.valueOf(bookNum));
                System.out.println(entry);
                System.out.println("Book successfully checked-in.");
                flag = 1;
            }
        }
        if(flag == 0)
            System.out.println("Book not found.");*/
    }

    @Override
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
