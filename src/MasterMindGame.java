import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class MasterMindGame extends JFrame {
    private Partie partie;
    private Joueur joueur;
    private JTextField solutionField;
    private JTextField essaiField;
    private JTextArea resultatArea;
    private JButton validerButton;

    public MasterMindGame(Joueur joueur) {
        this.joueur = joueur;
        setTitle("MasterMind - " + joueur.getNom());
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Champs de saisie
        solutionField = new JTextField(10);
        essaiField = new JTextField(10);
        resultatArea = new JTextArea(10, 30);
        resultatArea.setEditable(false);
        validerButton = new JButton("Valider Essai");

        add(new JLabel("Solution (0-99) :"));
        add(solutionField);
        add(new JLabel("Essai (0-99) :"));
        add(essaiField);
        add(validerButton);
        add(new JScrollPane(resultatArea));

        // Action du bouton
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validerEssai();
            }
        });
    }

    private void validerEssai() {
        try {
            if (partie == null) {
                int solution = Integer.parseInt(solutionField.getText());
                partie = PartieFactory.createPartie(1, solution, new Timestamp(System.currentTimeMillis()), null, 0, 0, "En cours");
                resultatArea.append("Partie démarrée. Essayez de deviner la solution !\n");
            }
            int essaiValue = Integer.parseInt(essaiField.getText());
            partie.setNbEssai(partie.getNbEssaie() + 1);

            // Logique de validation simple
            int bienPlaces = (essaiValue == partie.getSolution()) ? 1 : 0; // Exemple simplifié
            int malPlaces = 0; // Calcul à ajuster selon la logique de jeu
            boolean verifEssai = (bienPlaces > 0);

            // Créer un nouvel essai
            Essai essai = new Essai(partie.getNbEssaie(), bienPlaces, malPlaces, partie.getNbEssaie(), verifEssai, String.valueOf(essaiValue));
            essai.afficherInfos(); // Affiche les infos de l'essai

            if (verifEssai) {
                partie.setEtatPartie("Gagné");
                partie.setDateFin(new Timestamp(System.currentTimeMillis()));
                resultatArea.append("Vous avez gagné en " + partie.getNbEssaie() + " essais!\n");
            } else {
                resultatArea.append("Essai incorrect. Réessayez !\n");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }



}
