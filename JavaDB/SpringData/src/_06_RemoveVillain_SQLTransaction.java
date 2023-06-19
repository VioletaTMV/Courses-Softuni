import java.sql.*;
import java.util.Scanner;

public class _06_RemoveVillain_SQLTransaction {

    private static final String SELECT_COUNT_OF_MINIONS_HELD_BY_VILLAIN_ID =
            "SELECT count(*) " +
                    "FROM minions_villains " +
                    "WHERE villain_id = ?;";

    private static final String CREATE_TRANSACTION_DELETE_VILLAIN_AND_RELEASE_HIS_MINIONS =
            //    "DELIMITER $$ " +
            "CREATE PROCEDURE delete_villain_and_release_minions (v_id INT) " +
                    "BEGIN " +
                    "START TRANSACTION; " +
                    "IF(SELECT count(*) FROM villains WHERE id = v_id <> 1) " +
                    "THEN " +
                    "DELETE FROM minions_villains " +
                    "WHERE villain_id = v_id; " +
                    "DELETE FROM villains " +
                    "WHERE id = v_id; " +
                    "COMMIT; " +
                    "ELSE ROLLBACK; " +
                    "END IF; " +
                    "END ";
//                            "$$ " +
//                    "DELIMITER ;";

    private static final String CALL_PROCEDURE = "CALL delete_villain_and_release_minions(?);";

    private static final String SELECT_VILLAIN_BY_ID =
                     "SELECT * " +
                    "FROM villains " +
                    "WHERE id = ?;";

    private static final String DROP_TRANSACTION_DELETE_AND_RELEASE_IF_EXISTS = "DROP PROCEDURE IF EXISTS delete_villain_and_release_minions";

    private static final String PRINT_FORMAT_VILLAIN_DELETED = "%s was deleted%n";
    private static final String PRINT_FORMAT_COUNT_MINIONS_RELEASED = "%d minions released%n";
    private static final String PRINT_FORMAT_NO_VILLAIN = "No such villain was found";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter villain ID: ");
        int villainID = Integer.parseInt(scanner.nextLine());

        Connection connection = Utils.connectionToMySQLServer(scanner);

        int countOfMinionsOfVillain = getCountMinions(connection, villainID);
        String villainName = getVillainName(connection, villainID);

        if (villainName == null) {
            System.out.println(PRINT_FORMAT_NO_VILLAIN);
        } else {
            deleteVillainAndReleaseMinions(connection, villainID);

            System.out.printf(PRINT_FORMAT_VILLAIN_DELETED, villainName);
            System.out.printf(PRINT_FORMAT_COUNT_MINIONS_RELEASED, countOfMinionsOfVillain);
        }

        connection.close();

    }

    private static void deleteVillainAndReleaseMinions(Connection connection, int villainID) throws SQLException {

        Statement stmt = connection.createStatement();
        CallableStatement callableStatement = connection.prepareCall(CALL_PROCEDURE);

        stmt.execute(DROP_TRANSACTION_DELETE_AND_RELEASE_IF_EXISTS);
        stmt.execute(CREATE_TRANSACTION_DELETE_VILLAIN_AND_RELEASE_HIS_MINIONS);
        stmt.close();

        callableStatement.setInt(1, villainID);
        callableStatement.execute();
    }

    private static int getCountMinions(Connection connection, int villainID) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(SELECT_COUNT_OF_MINIONS_HELD_BY_VILLAIN_ID);

        stmt.setInt(1, villainID);

        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return 0;
    }

    private static String getVillainName(Connection connection, int villainID) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(SELECT_VILLAIN_BY_ID);

        statement.setInt(1, villainID);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("name");
        } else {
            return null;
        }

    }
}
