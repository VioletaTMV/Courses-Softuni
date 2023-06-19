import java.sql.*;
import java.util.PrimitiveIterator;
import java.util.Scanner;

public class _06_RemoveVillain_javaAutocommitOff {

    private static final String SELECT_COUNT_OF_MINIONS_HELD_BY_VILLAIN_ID =
            "SELECT count(*) " +
                    "FROM minions_villains " +
                    "WHERE villain_id = ?;";

    private static final String SELECT_VILLAIN_BY_ID =
            "SELECT * " +
                    "FROM villains " +
                    "WHERE id = ?;";

    private static final String RELEASE_MINIONS_OF_VILLAIN_ID =
            "DELETE FROM minions_villains " +
                    "WHERE villain_id = ?;";

    private static final String DELETE_VILLAIN_BY_ID =
            "DELETE FROM villains " +
                    "WHERE id = ?;";

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
            return;
        }

        connection.setAutoCommit(false);

        try {

            final PreparedStatement preparedStatementReleaseMinions = connection.prepareStatement(RELEASE_MINIONS_OF_VILLAIN_ID);
            final PreparedStatement preparedStatementDeleteVillainByID = connection.prepareStatement(DELETE_VILLAIN_BY_ID);

            preparedStatementReleaseMinions.setInt(1, villainID);
            preparedStatementDeleteVillainByID.setInt(1, villainID);

            preparedStatementReleaseMinions.executeUpdate();
            preparedStatementDeleteVillainByID.executeUpdate();

            connection.commit();

            System.out.printf(PRINT_FORMAT_VILLAIN_DELETED, villainName);
            System.out.printf(PRINT_FORMAT_COUNT_MINIONS_RELEASED, countOfMinionsOfVillain);

        } catch (SQLException e) {
            e.printStackTrace();

            connection.rollback();
        }

        connection.close();
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
