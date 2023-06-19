import java.sql.*;
import java.util.Scanner;

public class _09_IncreaseAgeStoredProcedure {

    private static final String CALL_PROCEDURE_MINION_AGE_INCREASE_BY_ID = "CALL usp_get_older(?);";
    private static final String SELECT_MINION_NAME_AND_AGE_BY_ID =
            "SELECT name, age " +
            "FROM minions " +
            "WHERE id = ?;";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Minion ID: ");
        int minionID = Integer.parseInt(scanner.nextLine());

        Connection connection = Utils.connectionToMySQLServer(scanner);

        CallableStatement callableStatement = connection.prepareCall(CALL_PROCEDURE_MINION_AGE_INCREASE_BY_ID);
        callableStatement.setInt(1, minionID);

        callableStatement.execute();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MINION_NAME_AND_AGE_BY_ID);
        preparedStatement.setInt(1, minionID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        System.out.printf("%s %d%n",
                resultSet.getString("name"),
                resultSet.getInt("age"));

        connection.close();
    }
}
