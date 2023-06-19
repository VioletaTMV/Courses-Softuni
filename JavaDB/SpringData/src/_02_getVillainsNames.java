import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DBappsIntro {

    private static final String SELECT_VILLAINS_AND_NUMBER_OF_MINIONS =
            "SELECT " +
            " v.name, " +
            " count(DISTINCT(mv.minion_id)) AS number_of_minions " +
            "FROM villains AS v " +
            "JOIN minions_villains AS mv ON v.id = mv.villain_id " +
            "GROUP BY name " +
            "HAVING number_of_minions > 15 " +
            "ORDER BY number_of_minions DESC;";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Connection connection = getConnectionToMySQLserver(scanner);

        PreparedStatement prepStmt = connection.prepareStatement(SELECT_VILLAINS_AND_NUMBER_OF_MINIONS);

        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()){
            System.out.printf("%s %d", rs.getString("v.name"),
                    rs.getInt("number_of_minions"));
        }


    }

    private static Connection getConnectionToMySQLserver(Scanner scanner) throws SQLException {
        System.out.print("Enter username (default root): ");
        String userName = scanner.nextLine();
        userName = userName.equals("") ? "root" : userName;

        System.out.print("Enter password: ");
        String pass = scanner.nextLine().trim();

        Properties props = new Properties();
        props.setProperty("user", userName);
        props.setProperty("password", pass);

        Connection conn =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/minions_db", props
                );

        return conn;
    }
}
