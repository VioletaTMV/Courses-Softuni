import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class _08_IncreaseMinionsAge {

    private static final String SELECT_ALL_MINIONS =
            "SELECT * " +
                    "FROM minions";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Minion IDs separated by space: ");
        int[] minionIDsArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        String updateMinionsAgeAndNameLowerCaseWhereArrayOfIDs =
                        "UPDATE minions " +
                        "SET name = lower(name), age = age + 1 " +
                        "WHERE id IN (parametersPlaceholderHere);";

        int parametersNumberForWhereClause = minionIDsArr.length;
        StringBuilder ActualParamsForWhereClause = new StringBuilder();

        for (int i = 0; i < parametersNumberForWhereClause; i++) {
            ActualParamsForWhereClause.append("?, ");
        }
        ActualParamsForWhereClause.delete(ActualParamsForWhereClause.length() - 2, ActualParamsForWhereClause.length() - 1);

        final String finalUpdateQueryMinionsAgeAndLowerCaseName = updateMinionsAgeAndNameLowerCaseWhereArrayOfIDs.replace("parametersPlaceholderHere", ActualParamsForWhereClause);

        Connection connection = Utils.connectionToMySQLServer(scanner);

        PreparedStatement stmt = connection.prepareStatement(finalUpdateQueryMinionsAgeAndLowerCaseName);

        for (int i = 0; i < parametersNumberForWhereClause; i++) {
            stmt.setInt(i + 1, minionIDsArr[i]);
        }

        stmt.executeUpdate();

        PreparedStatement stmtSelectAllMinions = connection.prepareStatement(SELECT_ALL_MINIONS);
        ResultSet resultSet = stmtSelectAllMinions.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d%n",
                    resultSet.getString("name"),
                    resultSet.getInt("age"));
            ;

        }

        connection.close();

        scanner.close();

    }
}
