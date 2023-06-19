import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DiabloUsersAndGames {

    private static final String QUERY_USERNAME_GAMECOUNTS = "SELECT " +
                                                             "    first_name, " +
                                                             "    last_name, " +
                                                             "    count(ug.game_id) " +
                                                             "FROM diablo.users AS u " +
                                                             "    JOIN users_games AS ug ON u.id = ug.user_id " +
                                                             "WHERE user_name = ? " +
                                                             "GROUP BY first_name, last_name;";


    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Connection connection = getConnectionToMySQLserver(scanner);

        System.out.print("Enter a player's username: ");
        String playerUsername = scanner.nextLine();

        PreparedStatement ps = connection.prepareStatement(QUERY_USERNAME_GAMECOUNTS);

        ps.setString(1, playerUsername);

        ResultSet rs = ps.executeQuery();

        boolean nextRowExists = rs.next();

        if (nextRowExists){
            System.out.println("User: " +  playerUsername);
            System.out.printf("%s %s has played %d games",
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("count(ug.game_id)")
                    );
        } else {
            System.out.println("No such user exists");
        }

    }

    private static Connection getConnectionToMySQLserver(Scanner scanner) throws SQLException {
        System.out.print("Enter username (default Root): ");
        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        Properties userAndPass = new Properties();
        userAndPass.setProperty("user", user);
        userAndPass.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", userAndPass);

        return connection;
    }
}
