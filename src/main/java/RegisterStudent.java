import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class RegisterStudent implements Action {
    @Override
    public void showInfo() {
        System.out.println("Enter the id, status, name and surname of the student.");
    }

    @Override
    public void executeAction(String command) throws SQLException {
        String[] parts = command.split(",");
        Person person = Person.createPerson(parts[0], parts[1], parts[2], parts[3]);
        PersonDB.createPerson(person);
        // Main.persons.put(person.getId(), person);
        System.out.println("Student successfully added!");
    }

    @Override
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String student = scanner.nextLine();

        if(student != null){
            String[] parts = student.split(",");
            if(parts.length == 4){
                for(Map.Entry<String, Person> entry : Main.persons.entrySet()) {
                    if (Objects.equals(entry.getKey(), parts[0])) {
                        System.out.println("The ID you entered already exists.");
                        return null;
                    }
                }
            }
        }

        return student;
    }
}
