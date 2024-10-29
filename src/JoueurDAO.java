import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class JoueurDAO {
    public List<String> getPlayers() {
        List<String> players = new ArrayList<>();
        String query = "SELECT pseudo_joueur FROM joueurs";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String pseudo = resultSet.getString("pseudo_joueur");
                players.add(pseudo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}
