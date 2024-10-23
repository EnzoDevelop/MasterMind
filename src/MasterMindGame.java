import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class MasterMindGame extends JFrame {
    private List<Joueur> joueurs;
    private Partie partie;
    private int nombreEssais;
    private JTextField essaiField;
    private JTextArea resultatArea;
    private JButton validerButton;
    private int essaiCourant;

    public MasterMindGame(List<Joueur> joueurs, int positions, int essais) {
        this.joueurs = joueurs;
        this.nombreEssais = essais;
        this.essaiCourant = 0;
        Random random = new Random();
        StringBuilder solutionBuilder = new StringBuilder();

        // Générer une solution aléatoire
        for (int i = 0; i < positions; i++) {
            int digit = random.nextInt(9) + 1; // Génère un chiffre entre 1 et 9
            solutionBuilder.append(digit); // Ajoute le chiffre à la solution
        }

        // Convertir la solution en entier
        int solution = Integer.parseInt(solutionBuilder.toString());
        partie = PartieFactory.createPartie(1, solution, new Timestamp(System.currentTimeMillis()), null, 0, positions, "En cours");

        setTitle("MasterMind - " + joueurs.size() + " Joueurs");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        essaiField = new JTextField(10);
        resultatArea = new JTextArea(10, 30);
        resultatArea.setEditable(false);
        validerButton = new JButton("Valider Essai");

        add(new JLabel("Essai (0-99) :"));
        add(essaiField);
        add(validerButton);
        add(new JScrollPane(resultatArea));

        // Action du bouton
        validerButton.addActionListener(e -> validerEssai());
    }

    private void validerEssai() {
        if (essaiCourant >= nombreEssais) {
            JOptionPane.showMessageDialog(this, "Nombre maximum d'essais atteint. La partie est terminée.", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE);

            // Afficher la solution si le joueur a perdu
            partie.setEtatPartie("Perdu");
            partie.setDateFin(new Timestamp(System.currentTimeMillis()));
            resultatArea.append("Vous avez perdu. La solution était : " + partie.getSolution() + "\n");

            return;
        }

        try {
            int essaiValue = Integer.parseInt(essaiField.getText());
            essaiCourant++;

            // Vérifier l'essai et obtenir le résultat
            String resultat = partie.verifierEssai(essaiValue);

            // Créer un nouvel essai
            Essai essai = new Essai(essaiCourant, 0, 0, essaiCourant, false, String.valueOf(essaiValue)); // À ajuster si besoin


            // Afficher les résultats
            resultatArea.append("Résultat pour l'essai " + essaiValue + ": " + resultat + "\n");

            // Vérifier si gagné
            if (resultat.equals("#".repeat(partie.getNbPosition()))) {
                partie.setEtatPartie("Gagné");
                partie.setDateFin(new Timestamp(System.currentTimeMillis()));
                resultatArea.append("Vous avez gagné en " + essaiCourant + " essais!\n");
            } else {
                resultatArea.append("Essai incorrect. Réessayez ! (Essais restants : " + (nombreEssais - essaiCourant) + ")\n");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}
