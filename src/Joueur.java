import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Joueur {
    private int idJoueur;
    private String nom;

    public Joueur(String nom) {
        this.nom = nom;
        insererJoueurDansBDD(); // Insertion dans la base de données à la création du joueur
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void afficherInfos() {
        System.out.println("Nom Joueur: " + nom);
    }

    // Méthode pour insérer le joueur dans la base de données
    private void insererJoueurDansBDD() {
        String insertJoueurSQL = "INSERT INTO Joueur (nom) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertJoueurSQL)) {
            pstmt.setString(1, this.nom);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
// d