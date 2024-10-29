import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class PartieDAO {
    public List<Partie> getParties() {
        List<Partie> parties = new ArrayList<>();

        String query = "SELECT * FROM Partie";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idPartie = resultSet.getInt("id_partie");
                int solution = resultSet.getInt("solution");
                Timestamp dateDebut = resultSet.getTimestamp("date_debut");
                Timestamp dateFin = resultSet.getTimestamp("date_fin");
                int nbEssai = resultSet.getInt("nb_essai");
                int nbPosition = resultSet.getInt("nb_position");
                String etatPartie = resultSet.getString("etat_partie");

                Partie partie = new Partie(idPartie, solution, dateDebut, dateFin, nbEssai, nbPosition, etatPartie);
                parties.add(partie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parties;
    }
}
