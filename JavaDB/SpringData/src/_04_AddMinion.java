import java.sql.*;
import java.util.Scanner;

public class _04_AddMinion {

    private static final String SELECT_TOWN_ID_BY_TOWN_NAME = "SELECT id FROM towns WHERE name = ?;";
    private static final String SELECT_VILLAIN_ID_BY_VILLAIN_NAME = "SELECT id FROM villains WHERE name = ?;";
    private static final String SELECT_MINION_ID_BY_NAME = "SELECT id FROM minions WHERE name = ? ORDER BY id DESC LIMIT 1;";
    private static final String INSERT_INTO_TOWNS = "INSERT INTO towns (name) VALUES (?);";
    private static final String INSERT_INTO_VILLAINS = "INSERT INTO villains (name, evilness_factor) VALUES (?, ?);";
    private static final String INSERT_INTO_MINIONS = "INSERT INTO minions (name, age, town_id) VALUES (?, ?, ?);";
    private static final String INSERT_INTO_MINIONS_VILLAINS = "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?);";

    private static final String PRINT_FORMAT_TOWN_ADDED = "Town %s was added to the database.%n";
    private static final String PRINT_FORMAT_VILLAIN_ADDED = "Villain %S was added to the database.%n";
    private static final String PRINT_FORMAT_MINION_ADDED_FOR_VILLAIN = "Successfully added %s to be minion of %s.%n";

    private static final String EVILNESS_FACTOR_DEFAULT = "evil";


    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Minion name, age and town (separated by space, and Villain info on second line.");
        String[] minionNameAgeTown = scanner.nextLine().split("\\s+");
        String minionName = minionNameAgeTown[1];
        String minionAge = minionNameAgeTown[2];
        String minionTown = minionNameAgeTown[3];

        String[] villainNameInfo = scanner.nextLine().split("\\s+");
        String villainName = villainNameInfo[1];


        final Connection connection = Utils.connectionToMySQLServer(scanner);

        int townId = getID(connection, SELECT_TOWN_ID_BY_TOWN_NAME, INSERT_INTO_TOWNS, PRINT_FORMAT_TOWN_ADDED, minionTown);
        int villainId = getID(connection, SELECT_VILLAIN_ID_BY_VILLAIN_NAME, INSERT_INTO_VILLAINS, PRINT_FORMAT_VILLAIN_ADDED, villainName, EVILNESS_FACTOR_DEFAULT);

        insertIntoTable(connection, INSERT_INTO_MINIONS, minionName, minionAge, String.valueOf(townId));
        int minionID = getMinionID(connection, minionName);

        insertIntoTable(connection, INSERT_INTO_MINIONS_VILLAINS, String.valueOf(minionID), String.valueOf(villainId));

        System.out.printf(PRINT_FORMAT_MINION_ADDED_FOR_VILLAIN, minionName, villainName);

        connection.close();
    }

    private static int getMinionID(Connection connection, String minionName) throws SQLException {
        PreparedStatement getIDOfLastMinionsWithName = connection.prepareStatement(SELECT_MINION_ID_BY_NAME);

        getIDOfLastMinionsWithName.setString(1, minionName);

        ResultSet resultSetMinionID = getIDOfLastMinionsWithName.executeQuery();

        int minionID = -1;
        while (resultSetMinionID.next()) {
            minionID = resultSetMinionID.getInt("id");
        }
        return minionID;
    }

    private static int getID(Connection connection, String selectQuery, String insertQuery, String printFormat, String... arguments) throws SQLException {

        int objectID = -1;

        String name = arguments[0];

        PreparedStatement preparedStatementGetObjectIdByName = connection.prepareStatement(selectQuery);
        preparedStatementGetObjectIdByName.setString(1, name);

        ResultSet resultSetObjectIdByName = preparedStatementGetObjectIdByName.executeQuery();

        if (!resultSetObjectIdByName.next()) {
            insertIntoTable(connection, insertQuery, arguments);
            System.out.printf(printFormat, arguments[0]);
            objectID = getID(connection, selectQuery, insertQuery, printFormat, name);
        } else {
            objectID = resultSetObjectIdByName.getInt("id");
        }

        return objectID;
    }

    private static void insertIntoTable(Connection connection, String insertQuery, String... args) throws SQLException {

        PreparedStatement insertIntoTableStmt = connection.prepareStatement(insertQuery);

        for (int i = 0; i < args.length; i++) {
            insertIntoTableStmt.setString(i + 1, args[i]);
        }

        insertIntoTableStmt.executeUpdate();
    }


}
