import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Inscrit {

    public static void ajouterJoueursDansPartie(int idPartie, List<Integer> idsJoueurs) {
        String insertJoueurDansPartieSQL = "INSERT INTO inscrit (id_partie, id_joueur, score) VALUES (?, ?, 'en cours')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertJoueurDansPartieSQL)) {

            for (int idJoueur : idsJoueurs) {
                pstmt.setInt(1, idPartie);
                pstmt.setInt(2, idJoueur);
                pstmt.addBatch(); // Ajout de chaque insertion au lot
            }

            pstmt.executeBatch(); // Exécute toutes les insertions en une seule fois
            System.out.println("Tous les joueurs ont été ajoutés à la partie.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
