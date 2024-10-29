import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class RankingPage extends JFrame {
    public RankingPage() {
        setTitle("Classement des Joueurs - MasterMind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(45, 52, 54));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Classement des Joueurs");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        PlayerDAO playerDAO = new PlayerDAO();
        List<String> players = playerDAO.getPlayers();

        JTextArea playersArea = new JTextArea();
        playersArea.setEditable(false);
        playersArea.setBackground(new Color(99, 110, 114));
        playersArea.setForeground(Color.WHITE);
        playersArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        if (players.isEmpty()) {
            playersArea.setText("Aucun joueur trouvé.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String player : players) {
                sb.append(player).append("\n");
            }
            playersArea.setText(sb.toString());
        }

        JScrollPane scrollPane = new JScrollPane(playersArea);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RankingPage rankingPage = new RankingPage();
            rankingPage.setVisible(true);
        });
    }
}
