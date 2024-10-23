import java.sql.Timestamp;

public class PartieFactory {
    public static Partie createPartie(int idPartie, int solution, Timestamp date_debut, Timestamp date_fin, int nb_essai, int nb_position, String etat_partie) {
        return new Partie(idPartie, solution, date_debut, date_fin, nb_essai, nb_position, etat_partie);
    }
}
