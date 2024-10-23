import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bienvenue dans MasterMind");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField positionsField = new JTextField(5);
        JTextField essaisField = new JTextField(5);
        JTextField joueursField = new JTextField(15);
        JButton commencerButton = new JButton("Commencer la Partie");

        frame.add(new JLabel("Nombre de Positions :"));
        frame.add(positionsField);
        frame.add(new JLabel("Nombre d'Essais :"));
        frame.add(essaisField);
        frame.add(new JLabel("Noms des Joueurs (séparés par des virgules) :"));
        frame.add(joueursField);
        frame.add(commencerButton);

        commencerButton.addActionListener(e -> {
            String positionsText = positionsField.getText();
            String essaisText = essaisField.getText();
            String joueursText = joueursField.getText();

            if (positionsText.isEmpty() || essaisText.isEmpty() || joueursText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int positions = Integer.parseInt(positionsText);
            int essais = Integer.parseInt(essaisText);
            String[] nomsJoueurs = joueursText.split(",");

            List<Joueur> joueurs = new ArrayList<>();
            for (String nom : nomsJoueurs) {
                joueurs.add(new Joueur(nom.trim()));
            }

            // Lancer le jeu MasterMind
            SwingUtilities.invokeLater(() -> {
                MasterMindGame game = new MasterMindGame(joueurs, positions, essais);
                game.setVisible(true);
            });

            frame.dispose(); // Fermer la fenêtre d'entrée
        });

        // Maximiser la fenêtre pour qu'elle prenne tout l'écran
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
