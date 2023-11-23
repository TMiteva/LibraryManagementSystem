import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main extends Database{

    public static Scanner scanner = new Scanner(System.in);
    public static Map<String, Person> persons = new LinkedHashMap<>();
    public static Map<String, Book> books = new LinkedHashMap<>();


    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to my library!");
        mainMenu();
        mainLoop();
        scanner.close();
    }



    public static void mainLoop() throws SQLException {
        Action action;
        String userInput = "-1";
        while(!userInput.equals("11")){
            userInput=scanner.nextLine();
            switch (userInput){
                case "1" -> {
                    action = new AddBook();
                    action.showInfo();
                    String command = action.readUserInput();
                    action.executeAction(command);
                }
                case "2" -> {
                    if(books != null) {
                        action = new Search();
                        action.showInfo();
                        String command = action.readUserInput();
                        action.executeAction(command);
                    } else {
                        System.out.println("There aren't any books saved.");
                    }
                }
                case "3" -> {
                    if (books != null) {
                        action = new ListBooks();
                        action.showInfo();
                        //String command = action.readUserInput();
                        action.executeAction("list");
                    }else {
                        System.out.println("Your list is empty.");
                    }
                }
                case "4" -> {
                    action = new RegisterStudent();
                    action.showInfo();
                    String command = action.readUserInput();
                    action.executeAction(command);
                }
                case "5" -> {
                    if(persons != null){
                        action = new ListStudents();
                        action.showInfo();
                        //String command = action.readUserInput();
                        action.executeAction("list");
                    }else {
                        System.out.println("Your list is empty!");
                    }
                }
                case "6" -> {
                    action = new RegisterEmployee();
                    action.showInfo();
                    String command = action.readUserInput();
                    action.executeAction(command);
                }
                case "7" -> {
                    if(persons != null){
                        action = new ListEmployees();
                        action.showInfo();
                        //String command = action.readUserInput();
                        action.executeAction("list");
                    }else {
                        System.out.println("Your list is empty!");
                    }
                }
                case "8" -> {
                    action = new CheckIn();
                    action.showInfo();
                    String command = action.readUserInput();
                    action.executeAction(command);
                }
                case "9" -> {
                    action = new CheckOut();
                    action.showInfo();
                    String command = action.readUserInput();
                    action.executeAction(command);
                }
                case "10" -> {
                    action = new ListCheckedBooks();
                    action.showInfo();
                    //String command = action.readUserInput();
                    action.executeAction("list");
                }
                case "11" -> {
                    exitMessage();
                }
                default -> {
                    System.out.println();
                    System.out.println("Select one of the available commands");
                    System.out.println();
                    mainMenu();
                }
            }
        }
    }

    public static void mainMenu(){
        System.out.println("1: Add a new book");
        System.out.println("2: Search for a book");
        System.out.println("3: Show all books");
        System.out.println("4: Register student");
        System.out.println("5: Show all registered students");
        System.out.println("6: Register new employee");
        System.out.println("7: List all employees");
        System.out.println("8: Check-in a book");
        System.out.println("9: Check-out a book");
        System.out.println("10: List of all checked-out books:");
        System.out.println("11: Exit application");
    }

    public static void exitMessage(){
        System.out.println("Closing the program. Until next time.");
    }
}