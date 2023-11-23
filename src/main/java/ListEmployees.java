import java.sql.SQLException;

public class ListEmployees implements Action {
    @Override
    public void showInfo() {
        System.out.println("Here are all employees:");
    }

    @Override
    public void executeAction(String command) throws SQLException {
        PersonDB.listLibrarians();
        /*Main.persons.forEach((key, person) -> {
            if(Objects.equals(person.getStatus().toLowerCase(), "librarian"))
                System.out.println("ID: " + key + ", Name: " + person.getName() + ", Surname: " + person.getSurname() + ", Status: " + person.getStatus());
        });*/
    }

    @Override
    public String readUserInput() {
        return null;
    }
}
