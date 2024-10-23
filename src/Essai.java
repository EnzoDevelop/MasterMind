public class Essai {
    private int id_essai;
    private int bien_places;
    private int mal_places;
    private int num_tour;
    private boolean verif_essai;
    private String couleurs_essai;

    public Essai(int id_essai, int bien_places, int mal_places, int num_tour, boolean verif_essai, String couleurs_essai) {
        this.id_essai = id_essai;
        this.bien_places = bien_places;
        this.mal_places = mal_places;
        this.num_tour = num_tour;
        this.verif_essai = verif_essai;
        this.couleurs_essai = couleurs_essai;
    }

    public int getIdEssai() {
        return id_essai;
    }

    public int getBienPlaces() {
        return bien_places;
    }

    public int getMalPlaces() {
        return mal_places;
    }

    public int getNumTour() {
        return num_tour;
    }

    public boolean getVerifEssai() {
        return verif_essai;
    }

    public String getCouleursEssai() {
        return couleurs_essai;
    }

    public void setBienPlaces(int bien_places) {
        this.bien_places = bien_places;
    }

    public void setMalPlaces(int mal_places) {
        this.mal_places = mal_places;
    }

    public void setNumTour(int num_tour) {
        this.num_tour = num_tour;
    }

    public void setVerifEssai(boolean verif_essai) {
        this.verif_essai = verif_essai;
    }

    public void setCouleursEssai(String couleurs_essai) {
        this.couleurs_essai = couleurs_essai;
    }
}
