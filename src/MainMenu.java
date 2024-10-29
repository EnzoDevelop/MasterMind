import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Menu Principal - MasterMind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        BackgroundPanel mainPanel = new BackgroundPanel("images/background.jpg");
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Boutons
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
                    PageCreationPartie accueil = new PageCreationPartie();
                    accueil.setVisible(true);
                    break;
                case "Classement":
                    dispose();
                    PageClassement rankingPage = new PageClassement();
                    rankingPage.setVisible(true);
                    break;
                case "Liste des parties":
                    dispose();
                    PageListeParties ongoingGamesPage = new PageListeParties();
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

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/" + fileName)).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Image non trouvée : " + fileName);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

