package modeles;

import utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private int id_joueur;
    private String nom;

    public Joueur(String nom) {
        this.nom = nom;
        if (!existeDeja(nom)) {
            insererJoueurDansBDD();
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

            int rowsAffected = pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id_joueur = generatedKeys.getInt(1);
                    System.out.println("Dernier ID inséré : " + this.id_joueur);
                } else {
                    System.out.println("Aucun ID généré.");
                }
            }
            System.out.println("Rows affected: " + rowsAffected);

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
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Integer getIdParNom(String nom) {
        String rechercherIdSQL = "SELECT id_joueur FROM Joueurs WHERE pseudo_joueur = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(rechercherIdSQL)) {

            pstmt.setString(1, nom);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_joueur");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getPlayers() {
        List<String> players = new ArrayList<>();
        String query = "SELECT pseudo_joueur FROM Joueurs";

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
