import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class AddBook implements Action {
    @Override
    public void showInfo() {
        System.out.println("Please enter the isbn, tittle, author and quantity for the book you want to add.");
    }

    @Override
    public void executeAction(String command) throws SQLException {
        String[] parts = command.split(",");
        //Date date = new Date();
        //DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        Book book = Book.createBookEntry(parts[0], parts[1], parts[2], parts[3]);
        BooksDB.create(book);
        //Main.books.put(book.getIsbn(), book);
        System.out.println("Book successfully added!");
    }

    @Override
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if(!userInput.equals("0")){
            String[] parts = userInput.split(",");
            if(parts.length == 5){
                for (Map.Entry<String, Book> entry : Main.books.entrySet()) {
                    if(Objects.equals(entry.getKey(), parts[0])){
                        System.out.println("The chosen ISBN already exists. Choose another one.");
                        return null;
                    }
                    Book book = entry.getValue();
                    if (Objects.equals(book.getTittle(), parts[1])) {
                        System.out.println("The tittle you entered already exists. Choose another tittle.");
                        return null;
                    }
                }
            }
        }
        return userInput;
    }
}
