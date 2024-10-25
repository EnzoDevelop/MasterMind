import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class MasterMindGame extends JFrame {
    private List<Joueur> joueurs; // Liste des joueurs
    private Partie partie; // Partie en cours
    private int nombreEssais; // Nombre maximum d'essais
    private JTextField essaiField; // Champ pour entrer l'essai
    private JTextArea resultatArea; // Zone de texte pour afficher les résultats
    private JButton validerButton; // Bouton pour valider l'essai
    private int essaiCourant; // Essai actuel

    public MasterMindGame(List<Joueur> joueurs, int positions, int essais) {
        this.joueurs = joueurs;
        this.nombreEssais = essais;
        this.essaiCourant = 0;
        positions = 8;

        Random random = new Random();
        StringBuilder solutionBuilder = new StringBuilder();

        // Générer une solution aléatoire de 8 chiffres
        for (int i = 0; i < positions; i++) {
            int digit = random.nextInt(9) + 1; // Génère un chiffre entre 1 et 9
            solutionBuilder.append(digit); // Ajoute le chiffre à la solution
        }

        // Convertir la solution en entier
        int solution = Integer.parseInt(solutionBuilder.toString());
        partie = PartieFactory.createPartie(1, solution, new Timestamp(System.currentTimeMillis()), null, 0, positions, "En cours");

        // Configurer la fenêtre
        setTitle("MasterMind - " + joueurs.size() + " Joueurs");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Configurer le layout principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(45, 52, 54));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        Font font = new Font("SansSerif", Font.PLAIN, 18);

        // Panneau pour l'entrée de l'essai
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(new Color(45, 52, 54));

        JLabel essaiLabel = new JLabel("Essai (8 chiffres) :");
        essaiLabel.setFont(font);
        essaiLabel.setForeground(Color.WHITE);

        essaiField = new JTextField(10);
        essaiField.setFont(font);
        essaiField.setBackground(new Color(99, 110, 114));
        essaiField.setForeground(Color.WHITE);
        essaiField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        validerButton = new JButton("Valider Essai");
        validerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        validerButton.setBackground(new Color(178, 190, 195));
        validerButton.setFocusPainted(false);
        validerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Ajout des composants d'entrée
        inputPanel.add(essaiLabel);
        inputPanel.add(essaiField);
        inputPanel.add(validerButton);

        // Panneau pour afficher les résultats
        resultatArea = new JTextArea(10, 30);
        resultatArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultatArea.setEditable(false);
        resultatArea.setBackground(new Color(99, 110, 114));
        resultatArea.setForeground(Color.WHITE);
        resultatArea.setBorder(BorderFactory.createLineBorder(new Color(45, 52, 54), 5));

        // Panneau de défilement pour les résultats
        JScrollPane scrollPane = new JScrollPane(resultatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Ajout des panneaux à la fenêtre
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // Action du bouton
        validerButton.addActionListener(e -> validerEssai());
    }

    private void validerEssai() {
        if (essaiCourant >= nombreEssais) {
            JOptionPane.showMessageDialog(this, "Nombre maximum d'essais atteint. La partie est terminée.", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE);

            // Afficher la solution si le joueur a perdu
            partie.setEtatPartie("Perdu");
            partie.setDateFin(new Timestamp(System.currentTimeMillis()));
            partie.miseAJourPartieDansBDD(); // Mettre à jour la partie dans la BDD
            resultatArea.append("Vous avez perdu. La solution était : " + partie.getSolution() + "\n");

            return;
        }

        try {
            int essaiValue = Integer.parseInt(essaiField.getText());

            // Vérification de la longueur de l'essai
            if (String.valueOf(essaiValue).length() != 8) {
                JOptionPane.showMessageDialog(this, "Vous devez entrer exactement 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            essaiCourant++;

            // Vérifier l'essai et obtenir le résultat
            String resultat = partie.verifierEssai(essaiValue);

            // On suppose que nous prenons le premier joueur pour l'essai
            int id_joueur = joueurs.get(0).getIdJoueur(); // Récupérer l'ID du joueur actuel (pour simplifier)

            // Créer un nouvel essai
            int id_partie = partie.getIdPartie();
            Essai essai = new Essai(essaiCourant, 0, 0, essaiCourant, false, String.valueOf(essaiValue), id_joueur, id_partie);

            essai.insererEssaiDansBDD();

            // Afficher les résultats
            resultatArea.append("Essai " + essaiCourant + " : " + essaiValue + " -> " + resultat + "\n");

            // Vérifier si le joueur a gagné
            if (resultat.equals("#".repeat(partie.getNbPosition()))) {
                partie.setEtatPartie("Finis"); // Mettre l'état à "Finis"
                partie.setDateFin(new Timestamp(System.currentTimeMillis()));
                partie.miseAJourPartieDansBDD(); // Mettre à jour la partie dans la BDD
                resultatArea.append("Vous avez gagné en " + essaiCourant + " essais!\n");
            } else {
                resultatArea.append("Essai incorrect. Réessayez ! (Essais restants : " + (nombreEssais - essaiCourant) + ")\n");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
