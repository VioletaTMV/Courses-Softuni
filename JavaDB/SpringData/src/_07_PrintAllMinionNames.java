import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _07_PrintAllMinionNames {

    private static final String SELECT_MINION_NAMES =
            "SELECT name " +
                    "FROM minions;";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        List<String> minionNamesList = new ArrayList<>();

        final Connection connection = Utils.connectionToMySQLServer(scanner);

        final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MINION_NAMES);
        final ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            minionNamesList.add(resultSet.getString(1));
        }
        connection.close();


        for (int i = 0; i < minionNamesList.size() / 2; i++) {
            System.out.println(minionNamesList.get(i));
            System.out.println(minionNamesList.get(minionNamesList.size() - 1 - i));
        }

        if (minionNamesList.size() % 2 != 0) {
            System.out.println(minionNamesList.get(minionNamesList.size() / 2 + 1));
        }
    }
}
