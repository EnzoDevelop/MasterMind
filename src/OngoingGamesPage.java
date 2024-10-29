import javax.swing.*;
import java.awt.*;

public class OngoingGamesPage extends JFrame {
    public OngoingGamesPage() {
        setTitle("Liste des Parties - MasterMind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(45, 52, 54));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea ongoingGamesArea = new JTextArea(20, 50);
        ongoingGamesArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ongoingGamesArea.setEditable(false);
        ongoingGamesArea.setBackground(new Color(99, 110, 114));
        ongoingGamesArea.setForeground(Color.WHITE);

        ongoingGamesArea.setText("Liste des Parties en Cours\n\n");
        ongoingGamesArea.append("Partie 1: Joueur1 vs Joueur2\n");
        ongoingGamesArea.append("Partie 2: Joueur3 vs Joueur4\n");

        JScrollPane scrollPane = new JScrollPane(ongoingGamesArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Retour au Menu Principal");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setBackground(new Color(178, 190, 195));
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> {
            dispose();
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
        });

        mainPanel.add(backButton, BorderLayout.SOUTH);
        add(mainPanel);
    }
}
