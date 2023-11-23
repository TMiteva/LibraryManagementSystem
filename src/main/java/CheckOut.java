import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class CheckOut implements Action{
    @Override
    public void showInfo() {
        System.out.println("Enter the name of the book you want to check out:");
    }

    @Override
    public void executeAction(String command) throws SQLException {
        BooksDB.checkOutBook(command);

        /*int flag = 0;
        for(Map.Entry<String, Book> entry : Main.books.entrySet()){
            if(entry.getValue().getTittle().toLowerCase().equals(command)) {
                if (!Objects.equals(entry.getValue().getQuantity(), "0")) {
                    int bookNum = Integer.parseInt(entry.getValue().getQuantity());
                    bookNum--;
                    entry.getValue().setQuantity(String.valueOf(bookNum));
                    System.out.println(entry);
                    System.out.println("Book successfully checked-out.");
                    flag = 1;
                } else {
                    System.out.println("There are no available copies of this book.");
                }
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
