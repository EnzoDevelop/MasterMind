public class Joueur {
    private int idJoueur;
    private String nom;

    public Joueur(String nom) {
        this.nom = nom;
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
}
