import java.sql.Timestamp;

public class Partie {

    private int idPartie;
    private int solution;
    private Timestamp date_debut;
    private Timestamp date_fin;
    private String etat_partie;

    public Partie( int idPartie, int solution, Timestamp date_debut, Timestamp date_fin, String etat_partie) {
        this.idPartie = idPartie;
        this.solution = solution;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat_partie = etat_partie;

    }

    public int getIdPartie() {
        return idPartie;
    }

    public int getSolution() {
        return solution;
    }

    public void setSolution(int solution) {
        this.solution = solution;
    }

    public Timestamp getDateDebut() {
        return date_debut;
    }

    public void setDateDebut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public Timestamp getDateFin() {
        return date_fin;
    }

    public void setDateFin(Timestamp date_fin) {
        this.date_fin = date_fin;
    }

    public String getEtatPartie() {
        return etat_partie;
    }

    public void setEtatPartie(String etat_partie) {
        this.etat_partie = etat_partie;
    }

    public void afficherInfos() {
        System.out.println("ID Partie : " + idPartie);
        System.out.println("Solution : " + solution);
        System.out.println("Date de debut de partie : " + date_debut);
        System.out.println("Date de fin de partie : " + date_fin);
        System.out.println("Etat de la partie : " + etat_partie);
    }
}
