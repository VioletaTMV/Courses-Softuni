import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

enum Utils {

    ;

     static Connection connectionToMySQLServer(Scanner scanner) throws SQLException {

        System.out.print("Enter username (default root): ");
        String userName = scanner.nextLine();
        userName = userName.equals("") ? Constants.USER_VALUE: userName;

        System.out.print("Enter password: ");
        String pass = scanner.nextLine().trim();
        pass = pass.equals("") ? Constants.USER_PASSWORD_VALUE : pass;

        Properties properties = new Properties();
        properties.setProperty(Constants.USER_KEY, userName);
        properties.setProperty(Constants.USER_PASSWORD_KEY, pass);

        Connection connection = DriverManager.getConnection(Constants.JDBC_MYSQL_URL, properties);

        return connection;
    }

}
