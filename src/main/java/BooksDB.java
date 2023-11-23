import javax.lang.model.UnknownEntityException;
import java.sql.*;
import java.util.*;

public class BooksDB extends Database{
    private static final HashMap<String, Book> cache = new HashMap<>();
    private static final HashMap<String, Book> checkedBooks = new HashMap<>();

    public static void listBooks() throws SQLException {

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        String query = "SELECT * FROM booksTable;";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String isbn = resultSet.getString("isbn");
            String tittle = resultSet.getString("tittle");
            String author = resultSet.getString("author");
            String quantity = resultSet.getString("quantity");

            System.out.println(isbn + " " + tittle + " " + author + " " + quantity);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    public static void listCheckedBooks() throws SQLException {

        Connection connection = getConnection();;
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        String query = "SELECT * FROM checkedBooks;";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String isbn = resultSet.getString("isbn");
            String tittle = resultSet.getString("tittle");
            String author = resultSet.getString("author");
            String quantity = resultSet.getString("quantity");

            System.out.println(id + " " + isbn + " " + tittle + " " + author + " " + quantity);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    public static void create(Book book) throws SQLException {

        Connection connection;
        PreparedStatement preparedStatement;
        connection = getConnection();

        int[] result = null;
        String query = "INSERT INTO booksTable (isbn, tittle, author, quantity) VALUES (?, ?, ?, ?);";
        String query3 = "SELECT * FROM booksTable ORDER BY isbn;";

        try{

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTittle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getQuantity());
            preparedStatement.addBatch();
            result = preparedStatement.executeBatch();
            Book bookEntry;
            if(result != null){
                ResultSet resultSet = connection.createStatement().executeQuery(query3);
                while (resultSet.next()){
                    try{
                        bookEntry = Book.createBookEntry(resultSet.getString("isbn"), resultSet.getString("tittle"), resultSet.getString("author"), resultSet.getString("quantity"));
                        cache.put(bookEntry.getIsbn(), bookEntry);
                    }catch (UnknownEntityException e){
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static Book searchBook(String command) throws SQLException {
        int flag = 0;
        Book book = null;
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        String query = "SELECT * FROM booksTable;";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String isbn = resultSet.getString("isbn");
            String tittle = resultSet.getString("tittle");
            String author = resultSet.getString("author");
            String quantity = resultSet.getString("quantity");

            if (command.equals(tittle)) {
                System.out.println(isbn + " " + tittle + " " + author + " " + quantity);
                book = Book.createBookEntry(isbn, tittle, author, quantity);
                flag = 1;
            }
        }
        if(flag == 0)
            System.out.println("The book you searched for doesn't exist.");

        return book;
    }

    public static void checkInBook(String name) throws SQLException {
        System.out.println("Enter student id.");
        Scanner scanner = new Scanner(System.in);
        String studentId = scanner.next();
        Book book = searchBook(name);

        createCheckBook(studentId, book, "in");

        System.out.println("Book successfully returned!");

    }

    public static void checkOutBook(String name) throws SQLException {

        System.out.println("Enter student id.");
        Scanner scanner = new Scanner(System.in);
        String studentId = scanner.next();
        Book book = searchBook(name);

        createCheckBook(studentId, book, "out");

        System.out.println("Book successfully checked-out!");
    }

    public  static void createCheckBook(String studentId, Book book, String s) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        int[] result = null;

        if(s.equals("in")) {
            String query3 = "DELETE FROM checkedBooks WHERE id = ?;";
            connection = BooksDB.getConnection();



            for (Map.Entry<String, Book> entry : checkedBooks.entrySet()) {

                if(entry.getKey().equals(studentId)) {
                    PreparedStatement st = connection.prepareStatement(query3);
                    st.setString(1, studentId);
                    st.executeUpdate();
                }
            }
        }else {

            String query = "INSERT INTO checkedBooks (id, isbn, tittle, author, quantity) VALUES (?, ?, ?, ?, ?);";
            String query2 = "SELECT * FROM checkedBooks;";
            int num = 0;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, studentId);
                preparedStatement.setString(2, book.getIsbn());
                preparedStatement.setString(3, book.getTittle());
                preparedStatement.setString(4, book.getAuthor());
                num = Integer.parseInt(book.getQuantity()) - 1;
                preparedStatement.setString(5, String.valueOf(num));
                preparedStatement.addBatch();
                result = preparedStatement.executeBatch();
                if (result != null) {
                    ResultSet resultSet = connection.createStatement().executeQuery(query2);
                    while (resultSet.next()) {
                        checkedBooks.put(studentId, book);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
