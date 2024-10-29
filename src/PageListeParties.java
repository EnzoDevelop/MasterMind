import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PageListeParties extends JFrame {
    public PageListeParties() {
        setTitle("Liste des Parties - MasterMind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(45, 52, 54));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {"ID Partie", "Nb Essai", "Nb Position", "Etat Partie"};
        PartieDAO partieDAO = new PartieDAO();
        List<Partie> parties = partieDAO.getParties();

        Object[][] data = new Object[parties.size()][4];
        for (int i = 0; i < parties.size(); i++) {
            Partie partie = parties.get(i);
            data[i][0] = partie.getIdPartie();
            data[i][1] = partie.getNbEssaie();
            data[i][2] = partie.getNbPosition();
            data[i][3] = partie.getEtatPartie();
        }

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setBackground(new Color(99, 110, 114));
        table.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
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
