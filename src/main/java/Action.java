import java.sql.SQLException;

public interface Action {
    void showInfo();
    void executeAction(String command) throws SQLException;
    String readUserInput();
}
