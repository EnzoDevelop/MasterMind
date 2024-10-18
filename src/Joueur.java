public class Joueur {
    private int idJoueur;
    private String nom;

    public Joueur(int idJoueur, String nom) {
        this.idJoueur = idJoueur;
        this.nom = nom;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void afficherInfos() {
        System.out.println("ID Joueur: " + idJoueur);
        System.out.println("Nom Joueur: " + nom);
    }
}
