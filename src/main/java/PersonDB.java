import java.sql.*;
import java.util.HashMap;

public class PersonDB extends Database{
    private static final HashMap<String, Person> cache = new HashMap<>();
    public static void createPerson(Person person) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        int[] result = null;
        String query = "INSERT INTO personTable (id, status, name, surname) VALUES (?, ?, ?, ?);";
        String query2 = "SELECT * FROM personTable ORDER BY id;";

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, person.getId());
            preparedStatement.setString(2, person.getStatus());
            preparedStatement.setString(3, person.getName());
            preparedStatement.setString(4, person.getSurname());
            preparedStatement.addBatch();
            result = preparedStatement.executeBatch();
            Person p;
            if(result != null){
                ResultSet resultSet = connection.createStatement().executeQuery(query2);
                while (resultSet.next()){
                    try{
                        p = Person.createPerson(resultSet.getString("id"), resultSet.getString("status"), resultSet.getString("name"), resultSet.getString("surname"));
                        cache.put(p.getId(), p);
                    }catch (SQLException s){
                        s.printStackTrace();
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void listStudents() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        String stat = "student";
        String query = "SELECT * FROM personTable WHERE status = '" + stat + "';";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String status = resultSet.getString("status");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");

            System.out.println(id + " " + status + " " + name + " " + surname);
        }
    }

    public static void listLibrarians() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        String stat = "librarian";
        String query = "SELECT * FROM personTable WHERE status = '" + stat + "';";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String status = resultSet.getString("status");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");

            System.out.println(id + " " + status + " " + name + " " + surname);
        }
    }
}
