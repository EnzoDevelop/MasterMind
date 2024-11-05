package vues;

import modeles.Joueur;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PageCreationPartie extends JFrame {

    public PageCreationPartie() {
        setTitle("Bienvenue dans MasterMind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(45, 52, 54));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        Font font = new Font("SansSerif", Font.PLAIN, 18);

        JLabel positionsLabel = new JLabel("Nombre de Positions :");
        positionsLabel.setFont(font);
        positionsLabel.setForeground(Color.WHITE);

        JTextField positionsField = new JTextField(5);
        positionsField.setFont(font);
        positionsField.setBackground(new Color(99, 110, 114));
        positionsField.setForeground(Color.WHITE);
        positionsField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel essaisLabel = new JLabel("Nombre d'Essais :");
        essaisLabel.setFont(font);
        essaisLabel.setForeground(Color.WHITE);

        JTextField essaisField = new JTextField(5);
        essaisField.setFont(font);
        essaisField.setBackground(new Color(99, 110, 114));
        essaisField.setForeground(Color.WHITE);
        essaisField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel joueursLabel = new JLabel("Noms des Joueurs (séparés par des virgules) :");
        joueursLabel.setFont(font);
        joueursLabel.setForeground(Color.WHITE);

        JTextField joueursField = new JTextField(15);
        joueursField.setFont(font);
        joueursField.setBackground(new Color(99, 110, 114));
        joueursField.setForeground(Color.WHITE);
        joueursField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton commencerButton = new JButton("Commencer Partie");
        commencerButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        commencerButton.setBackground(new Color(178, 190, 195));
        commencerButton.setForeground(Color.BLACK);
        commencerButton.setFocusPainted(false);
        commencerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton retourButton = new JButton("Retour au Menu Principal");
        retourButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        retourButton.setBackground(new Color(178, 190, 195));
        retourButton.setForeground(Color.BLACK);
        retourButton.setFocusPainted(false);
        retourButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        retourButton.addActionListener(e -> {
            dispose();
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(45, 52, 54));

        buttonPanel.add(commencerButton);
        buttonPanel.add(retourButton);

        mainPanel.add(positionsLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
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
        mainPanel.add(buttonPanel);
        add(mainPanel);

        commencerButton.addActionListener(e -> {
            String positionsText = positionsField.getText();
            String essaisText = essaisField.getText();
            String joueursText = joueursField.getText();

            if (positionsText.isEmpty() || essaisText.isEmpty() || joueursText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int positions = Integer.parseInt(positionsText);
            int essais = Integer.parseInt(essaisText);
            String[] nomsJoueurs = joueursText.split(",");

            List<Joueur> joueurs = new ArrayList<>();
            for (String nom : nomsJoueurs) {
                joueurs.add(new Joueur(nom.trim()));
            }

            SwingUtilities.invokeLater(() -> {
                JeuMasterMind game = new JeuMasterMind(joueurs, positions, essais);
                game.setVisible(true);
            });

            dispose();
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            PageCreationPartie accueil = new PageCreationPartie();
            accueil.setVisible(true);
        });
    }
}
