import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Joueur {
    private int id_joueur;
    private String nom;

    // Constructeur
    public Joueur(String nom) {
        this.nom = nom;
        insererJoueurDansBDD();
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


    private void insererJoueurDansBDD() {
        String insertJoueurSQL = "INSERT INTO Joueurs (pseudo_joueur) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertJoueurSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, this.nom);
            pstmt.executeUpdate();


            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id_joueur = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
