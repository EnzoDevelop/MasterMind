import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Joueur {
    private int id_joueur;
    private String nom;

    public Joueur(String nom) {
        this.nom = nom;
        this.id_joueur = id_joueur; // Insertion dans la base de données lors de la création de l'objet Joueur
    }

    public int getIdJoueur() {
        return id_joueur;
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
        String insertJoueurSQL = "INSERT INTO Joueurs (pseudo_joueur) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertJoueurSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, this.nom);
            pstmt.executeUpdate();

            // Récupérer l'id généré et le stocker dans id_joueur
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id_joueur = generatedKeys.getInt(1); // Affecte l'id généré à id_joueur
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
