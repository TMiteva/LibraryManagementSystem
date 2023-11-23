import java.sql.SQLException;

public class ListStudents implements Action {
    @Override
    public void showInfo() {
        System.out.println("Here are all students:");
    }

    @Override
    public void executeAction(String command) throws SQLException {
        PersonDB.listStudents();
        /*Main.persons.forEach((key, person) -> {
            if(Objects.equals(person.getStatus().toLowerCase(), "student")){
                System.out.println("ID: " + key + ", Name: " + person.getName() + ", Surname: " + person.getSurname() + ", Status: " + person.getStatus());
            }
        });*/
    }

    @Override
    public String readUserInput() {

        return null;
    }
}
