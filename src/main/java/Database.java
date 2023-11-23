import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    static final String url = "jdbc:postgresql://localhost:5432/book";
    static final String user = "postgres";
    static final String password = "admin";
    private static Connection connection = null;
    private static boolean hasRun = false;

    private static class DBHelper{
        private static final Database CONNECTION = new Database();
    }

    public static Connection getConnection() throws SQLException {
        return DBHelper.CONNECTION.connect();
    }

    public static void destroyConnection() {
        DBHelper.CONNECTION.disconnect();
    }
    Database(){}
    protected static Connection connect() throws SQLException {

        try {

            if(!hasRun){
                hasRun = true;
                connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();

                //String sql = "CREATE DATABASE book";
                //statement.execute(sql);
                //System.out.println("Database book successfully created!");

                //String query2 = "CREATE TABLE booksTable (isbn VARCHAR(255), tittle VARCHAR(255), author VARCHAR(255), quantity VARCHAR(255), PRIMARY KEY ( isbn ))";
                //statement.executeQuery(query2);
                //System.out.println("Table successfully created!");

                //String query3 = "CREATE TABLE personTable (id VARCHAR(255), status VARCHAR(255), name VARCHAR(255), surname VARCHAR(255), PRIMARY KEY ( id ))";
                //statement.executeQuery(query3);
                //System.out.println("Table successfully created!");

                //String query4 = "CREATE TABLE checkedBooks (id VARCHAR(255), isbn VARCHAR(255), tittle VARCHAR(255), author VARCHAR(255), quantity VARCHAR(255), PRIMARY KEY ( id ))";
                //statement.executeQuery(query4);
                //System.out.println("Table successfully created!");
            }else{
                connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void disconnect(){
        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
