import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _05_ChangeTownNamesCasing {

    private static final String UPDATE_TOWNS_UPPERCASE_FOR_COUNTRY =
                                                        "UPDATE towns " +
                                                        "SET name = upper(name) " +
                                                        "WHERE country = ?;";

    private static final String SELECT_NUMBER_OF_TOWNS_IN_COUNTRY =
                                                        "SELECT COUNT(*) " +
                                                        "FROM towns " +
                                                        "WHERE country = ?;";

    private static final String SELECT_TOWNS_AFFECTED =
                                                        "SELECT name " +
                                                        "FROM towns " +
                                                        "WHERE country = ?;";

    private static final String PRINT_FORMAT_NUMBER_OF_TOWNS_AFFECTED = "%d town names were affected.%n";
    private static final String PRINT_FORMAT_NO_TOWNS_AFFECTED = "No town names were affected.";

    private static final String PRINT_FORMAT_TOWNS_AFFECTED = "[%s]%n";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter country name: ");
        String country = scanner.nextLine();

        final Connection connection = Utils.connectionToMySQLServer(scanner);

        int numberOfTownsInCountry = countTowns (connection, country);

        if (numberOfTownsInCountry != 0){
            updateTownsToUppercase (connection, country);
            List<String> townsAffectedList = getTownAffected(connection, country);
            System.out.printf(PRINT_FORMAT_NUMBER_OF_TOWNS_AFFECTED, numberOfTownsInCountry);
            printNamesOfAffectedTowns (townsAffectedList);
        } else {
            System.out.println(PRINT_FORMAT_NO_TOWNS_AFFECTED);
        }





        connection.close();

    }

    private static void printNamesOfAffectedTowns(List<String> townsAffectedList) {

        String towns = String.join(", ", townsAffectedList);

        System.out.printf(PRINT_FORMAT_TOWNS_AFFECTED, towns);

    }

    private static List<String> getTownAffected(Connection connection, String country) throws SQLException {
        List<String> townsList = new ArrayList<>();

        PreparedStatement stmtTownNames = connection.prepareStatement(SELECT_TOWNS_AFFECTED);

        stmtTownNames.setString(1, country);

        ResultSet resultSet = stmtTownNames.executeQuery();

        while (resultSet.next()){
            townsList.add(resultSet.getString("name"));
        }

        return townsList;
    }

    private static int countTowns(Connection connection, String country) throws SQLException {

        PreparedStatement stmtCountTowns = connection.prepareStatement(SELECT_NUMBER_OF_TOWNS_IN_COUNTRY);

        stmtCountTowns.setString(1, country);

        ResultSet resultSet = stmtCountTowns.executeQuery();

        if (resultSet.next()){
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    private static void updateTownsToUppercase (Connection connection, String country) throws SQLException {

        PreparedStatement stmtTownsAffected = connection.prepareStatement(UPDATE_TOWNS_UPPERCASE_FOR_COUNTRY);

        stmtTownsAffected.setString(1, country);

        stmtTownsAffected.executeUpdate();

    }

}
