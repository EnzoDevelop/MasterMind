import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class PlayerDAO {
    public List<String> getPlayers() {
        List<String> players = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT pseudo_joueur FROM joueurs";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                players.add(resultSet.getString("pseudo_joueur"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}
// d