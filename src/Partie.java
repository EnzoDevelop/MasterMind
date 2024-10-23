import java.sql.Timestamp;

public class Partie {

    private int idPartie;
    private int solution;
    private Timestamp date_debut;
    private Timestamp date_fin;
    private int nb_essai;
    private int nb_position;
    private String etat_partie;

    public Partie( int idPartie, int solution, Timestamp date_debut, Timestamp date_fin, int nb_essai, int nb_position, String etat_partie) {
        this.idPartie = idPartie;
        this.solution = solution;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nb_essai = nb_essai;
        this.nb_position =nb_position;
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

    public int getNbEssaie() {
        return nb_essai;
    }

    public void setNbEssai(int nb_essai) {
        this.nb_essai = nb_essai;
    }

    public int getNbPosition() {
        return nb_position;
    }

    public void setNbPosition(int nb_position) {
        this.nb_position = nb_position;
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
        System.out.println("Nombre d'essai de la partie : " + nb_essai);
        System.out.println("Nombre de position de la solution : " + nb_position);
        System.out.println("Etat de la partie : " + etat_partie);
    }

    public String verifierEssai(int essai) {
        StringBuilder resultat = new StringBuilder();
        String solutionStr = String.valueOf(solution);
        String essaiStr = String.valueOf(essai);

        // Tableau pour marquer les positions déjà vérifiées
        boolean[] verifieeSolution = new boolean[solutionStr.length()];
        boolean[] verifieeEssai = new boolean[essaiStr.length()];

        // Vérification des bien placés
        for (int i = 0; i < solutionStr.length(); i++) {
            if (solutionStr.charAt(i) == essaiStr.charAt(i)) {
                resultat.append("#");
                verifieeSolution[i] = true;
                verifieeEssai[i] = true;
            }
        }

        // Vérification des mal placés
        for (int i = 0; i < essaiStr.length(); i++) {
            if (!verifieeEssai[i]) {
                for (int j = 0; j < solutionStr.length(); j++) {
                    if (!verifieeSolution[j] && essaiStr.charAt(i) == solutionStr.charAt(j)) {
                        resultat.append("o");
                        verifieeSolution[j] = true; // Marquer comme vérifié
                        break; // Ne pas compter plusieurs fois
                    }
                }
            }
        }

        return resultat.toString();
    }

}
