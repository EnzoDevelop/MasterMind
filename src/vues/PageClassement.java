package vues;

import modeles.Joueur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class PageClassement extends JFrame {
    public PageClassement() {
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

        List<String> players = Joueur.getPlayers();

        String[] columnNames = {"Pseudo Joueur", "Parties Gagnées", "Parties Perdues"};

        String[][] tableData = new String[players.size()][3];
        for (int i = 0; i < players.size(); i++) {
            tableData[i][0] = players.get(i);
            tableData[i][1] = "0";
            tableData[i][2] = "0";
        }

        JTable table = new JTable(tableData, columnNames);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setBackground(new Color(99, 110, 114));
        table.setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(178, 190, 195));
        table.getTableHeader().setForeground(Color.BLACK);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PageClassement rankingPage = new PageClassement();
            rankingPage.setVisible(true);
        });
    }
}
