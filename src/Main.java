import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Personnalisation des composants Swing pour une meilleure apparence
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crée une fenêtre avec un design moderne
        JFrame frame = new JFrame("Bienvenue dans MasterMind");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définir la taille maximale à l'écran
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximise la fenêtre
        frame.setLocationRelativeTo(null);

        // Créer un panneau principal avec un fond et un layout en Box pour une bonne structuration
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(45, 52, 54)); // Couleur de fond sombre moderne
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        // Police moderne
        Font font = new Font("SansSerif", Font.PLAIN, 18);

        // Label pour "Nombre de Positions"
        JLabel positionsLabel = new JLabel("Nombre de Positions :");
        positionsLabel.setFont(font);
        positionsLabel.setForeground(Color.WHITE); // Couleur de texte blanche pour un bon contraste

        // Champ de texte pour les positions
        JTextField positionsField = new JTextField(5);
        positionsField.setFont(font);
        positionsField.setBackground(new Color(99, 110, 114)); // Champ d'entrée avec couleur moderne
        positionsField.setForeground(Color.WHITE);
        positionsField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Bordure interne

        // Label pour "Nombre d'Essais"
        JLabel essaisLabel = new JLabel("Nombre d'Essais :");
        essaisLabel.setFont(font);
        essaisLabel.setForeground(Color.WHITE);

        // Champ de texte pour les essais
        JTextField essaisField = new JTextField(5);
        essaisField.setFont(font);
        essaisField.setBackground(new Color(99, 110, 114));
        essaisField.setForeground(Color.WHITE);
        essaisField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Label pour "Noms des Joueurs"
        JLabel joueursLabel = new JLabel("Noms des Joueurs (séparés par des virgules) :");
        joueursLabel.setFont(font);
        joueursLabel.setForeground(Color.WHITE);

        // Champ de texte pour les noms des joueurs
        JTextField joueursField = new JTextField(15);
        joueursField.setFont(font);
        joueursField.setBackground(new Color(99, 110, 114));
        joueursField.setForeground(Color.WHITE);
        joueursField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Bouton pour commencer le jeu avec un style moderne
        JButton commencerButton = new JButton("Commencer la Partie");
        commencerButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        commencerButton.setBackground(new Color(178, 190, 195)); // Bouton gris clair moderne
        commencerButton.setForeground(Color.BLACK);
        commencerButton.setFocusPainted(false);
        commencerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding pour un bouton plus grand

        // Ajout des composants au panneau principal avec de l'espace entre eux
        mainPanel.add(positionsLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement vertical
        mainPanel.add(positionsField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        mainPanel.add(essaisLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(essaisField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        mainPanel.add(joueursLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(joueursField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        mainPanel.add(commencerButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Ajouter le panneau à la fenêtre
        frame.add(mainPanel);

        // Ajout d'une action pour le bouton "Commencer la Partie"
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

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}
// dz