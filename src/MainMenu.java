import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Menu Principal - MasterMind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(45, 52, 54));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton createGameButton = createMenuButton("Créer une partie");
        JButton rankingButton = createMenuButton("Classement");
        JButton ongoingGamesButton = createMenuButton("Liste des parties");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.add(createGameButton, gbc);

        gbc.gridy = 1;
        mainPanel.add(rankingButton, gbc);

        gbc.gridy = 2;
        mainPanel.add(ongoingGamesButton, gbc);

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
                    dispose();
                    PageAccueil accueil = new PageAccueil();
                    accueil.setVisible(true);
                    break;
                case "Classement":
                    dispose();
                    RankingPage rankingPage = new RankingPage();
                    rankingPage.setVisible(true);
                    break;
                case "Liste des parties":
                    dispose();
                    OngoingGamesPage ongoingGamesPage = new OngoingGamesPage();
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
