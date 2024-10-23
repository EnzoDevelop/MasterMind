import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur("Kylian"));
        joueurs.add(new Joueur("Enzo"));

        for (Joueur joueur : joueurs) {
            joueur.afficherInfos();
            insertJoueur(joueur);
        }
    }

    private static void insertJoueur(Joueur joueur) {
        String sql = "INSERT INTO joueurs (pseudo_joueur) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, joueur.getNom());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Le joueur " + joueur.getNom() + " a été inséré avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
