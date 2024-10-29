import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Menu Principal - MasterMind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout for centering
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(45, 52, 54));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create buttons
        JButton createGameButton = createMenuButton("Créer une partie");
        JButton rankingButton = createMenuButton("Classement");
        JButton ongoingGamesButton = createMenuButton("Liste des parties");

        // Add buttons to the panel with GridBagConstraints for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // column
        gbc.gridy = 0; // row
        gbc.insets = new Insets(20, 0, 20, 0); // space around buttons
        gbc.anchor = GridBagConstraints.CENTER; // center the button

        mainPanel.add(createGameButton, gbc);

        gbc.gridy = 1; // move to the next row
        mainPanel.add(rankingButton, gbc);

        gbc.gridy = 2; // move to the next row
        mainPanel.add(ongoingGamesButton, gbc);

        // Add main panel to the frame
        add(mainPanel);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        button.setBackground(new Color(178, 190, 195));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.addActionListener(e -> {
            switch (text) {
                case "Créer une partie":
                    dispose(); // Close the main menu
                    PageAccueil accueil = new PageAccueil(); // Navigate to the game creation page
                    accueil.setVisible(true);
                    break;
                case "Classement":
                    dispose(); // Close the main menu
                    RankingPage rankingPage = new RankingPage(); // Navigate to the ranking page
                    rankingPage.setVisible(true);
                    break;
                case "Liste des parties":
                    dispose(); // Close the main menu
                    OngoingGamesPage ongoingGamesPage = new OngoingGamesPage(); // Navigate to the ongoing games page
                    ongoingGamesPage.setVisible(true);
                    break;
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
        });
    }
}
