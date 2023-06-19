import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_GetMinionNamesForVillain {

    private static final String minionNamesAndAgeForVillainID =
            "SELECT " +
                    "    m.name AS minion_name, " +
                    "    m.age AS minion_age " +
                    "FROM minions AS m " +
                    " JOIN minions_villains AS mv ON m.id = mv.minion_id " +
                    "WHERE mv.villain_id = ?;";

    private static final String villainNameByID =
            "SELECT name " +
                    "FROM villains " +
                    "WHERE villains.id = ?;";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Connection conn = getConnectionToMySQLserver(scanner);

        System.out.print("Enter villain ID: ");
        int villainID = Integer.parseInt(scanner.nextLine());

        PreparedStatement pstmtVillain = conn.prepareStatement(villainNameByID);
        pstmtVillain.setString(1, String.valueOf(villainID));
        ResultSet resultSetVillainName = pstmtVillain.executeQuery();

        PreparedStatement pstmtMinionNamesAge = conn.prepareStatement(minionNamesAndAgeForVillainID);
        pstmtMinionNamesAge.setString(1, String.valueOf(villainID));
        ResultSet resultSetMinionNameAge = pstmtMinionNamesAge.executeQuery();

        if (resultSetVillainName.next()) {
            System.out.println("Villain: " + resultSetVillainName.getString("name"));

            int count = 1;

            while (resultSetMinionNameAge.next()) {
                System.out.printf("%d. %s %d%n", count,
                        resultSetMinionNameAge.getString("minion_name"),
                        resultSetMinionNameAge.getInt("minion_age"));
                count++;
            }

        } else {
            System.out.println("No villain with ID " + villainID + " exists in the database.");
        }

    }

    private static Connection getConnectionToMySQLserver(Scanner scanner) throws SQLException {

        System.out.print("Enter username (default root): ");
        String userName = scanner.nextLine();
        userName = userName.equals("") ? "root" : userName;

        System.out.print("Enter password: ");
        String pass = scanner.nextLine().trim();

        Properties properties = new Properties();
        properties.setProperty("user", userName);
        properties.setProperty("password", pass);

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        return conn;
    }
}
