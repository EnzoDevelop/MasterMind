import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Joueur {
    private int id_joueur;
    private String nom;

    public Joueur(String nom) {
        this.nom = nom;
        if (!existeDeja(nom)) {
            insererJoueurDansBDD();
        } else {
            System.out.println("Le joueur avec le nom " + nom + " existe déjà dans la base de données. Insertion annulée.");
        }
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

    public static boolean existeDeja(String nom) {
        String verifierJoueurSQL = "SELECT 1 FROM Joueurs WHERE pseudo_joueur = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(verifierJoueurSQL)) {

            pstmt.setString(1, nom);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Renvoie vrai si un résultat existe, donc le nom est déjà dans la BDD
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
