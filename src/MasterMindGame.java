import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MasterMindGame extends JFrame {
    private List<Joueur> joueurs;
    private Partie partie;
    private int nombreEssais;
    private JTextArea resultatArea;
    private JButton validerButton;
    private int essaiCourant;
    private List<String> currentSelection;
    private JPanel displaySelectionPanel;
    private int positions;

    private static final Map<Integer, String> colorMap = Map.of(
            1, "Rouge", 2, "Vert", 3, "Bleu", 4, "Blanc", 5, "Noir", 6, "Jaune");

    private static final Map<String, Color> colorDisplayMap = Map.of(
            "Rouge", Color.RED, "Vert", Color.GREEN, "Bleu", Color.BLUE,
            "Blanc", Color.WHITE, "Noir", Color.BLACK, "Jaune", Color.YELLOW);

    public MasterMindGame(List<Joueur> joueurs, int positions, int essais) {
        this.joueurs = joueurs;
        this.positions = positions;
        this.nombreEssais = essais;
        this.essaiCourant = 0;
        this.currentSelection = new ArrayList<>();

        Random random = new Random();
        StringBuilder solutionBuilder = new StringBuilder();
        for (int i = 0; i < positions; i++) {
            int digit = random.nextInt(6) + 1;
            solutionBuilder.append(digit);
        }
        int solution = Integer.parseInt(solutionBuilder.toString());
        partie = PartieFactory.createPartie(1, solution, new Timestamp(System.currentTimeMillis()), null, 0, positions, "En cours");

        setTitle("MasterMind - " + joueurs.size() + " Joueurs");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(45, 52, 54));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Panneau de sélection des couleurs avec boutons circulaires et bordures épaisses
        JPanel colorPanel = new JPanel(new FlowLayout());
        colorPanel.setBackground(new Color(45, 52, 54));
        JLabel colorLabel = new JLabel("Sélectionnez les couleurs : ");
        colorLabel.setForeground(Color.WHITE);
        colorPanel.add(colorLabel);

        for (String color : colorMap.values()) {
            JButton colorButton = new JButton();
            colorButton.setPreferredSize(new Dimension(60, 60)); // Taille des boutons
            colorButton.setBackground(colorDisplayMap.get(color));
            colorButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3)); // Bordure épaisse
            colorButton.setContentAreaFilled(false);
            colorButton.setOpaque(true);
            colorButton.setBorderPainted(true);
            colorButton.setFocusPainted(false);
            colorButton.addActionListener(e -> addColorToSelection(color));
            colorPanel.add(colorButton);
        }

        JPanel selectionPanel = new JPanel(new FlowLayout());
        selectionPanel.setBackground(new Color(45, 52, 54));
        JLabel selectionLabel = new JLabel("Votre sélection : ");
        selectionLabel.setForeground(Color.WHITE);
        selectionPanel.add(selectionLabel);

        displaySelectionPanel = new JPanel();
        displaySelectionPanel.setPreferredSize(new Dimension(400, 50));
        displaySelectionPanel.setBackground(Color.LIGHT_GRAY);
        selectionPanel.add(displaySelectionPanel);

        validerButton = new JButton("Valider Essai");
        validerButton.setBackground(new Color(178, 190, 195));
        validerButton.setFocusPainted(false);
        validerButton.addActionListener(e -> validerEssai());

        resultatArea = new JTextArea(10, 30);
        resultatArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultatArea.setEditable(false);
        resultatArea.setBackground(new Color(99, 110, 114));
        resultatArea.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(resultatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(colorPanel, BorderLayout.NORTH);
        mainPanel.add(selectionPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        mainPanel.add(validerButton, BorderLayout.EAST);

        add(mainPanel);
    }

    private void addColorToSelection(String color) {
        if (currentSelection.size() < positions) {
            currentSelection.add(color);
            updateSelectionDisplay();
        } else {
            JOptionPane.showMessageDialog(this, "Vous avez atteint la limite de " + positions + " couleurs.");
        }
    }

    private void updateSelectionDisplay() {
        displaySelectionPanel.removeAll();
        for (String color : currentSelection) {
            JPanel colorSquare = new JPanel();
            colorSquare.setBackground(colorDisplayMap.get(color));
            colorSquare.setPreferredSize(new Dimension(40, 40));
            displaySelectionPanel.add(colorSquare);
        }
        displaySelectionPanel.revalidate();
        displaySelectionPanel.repaint();
    }

    private void validerEssai() {
        if (currentSelection.size() != positions) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner " + positions + " couleurs.");
            return;
        }

        StringBuilder essaiCode = new StringBuilder();
        for (String color : currentSelection) {
            essaiCode.append(getKeyFromColor(color));
        }

        String solutionCode = String.valueOf(partie.getSolution());
        int bienPlaces = 0;
        int malPlaces = 0;

        boolean[] solutionUsed = new boolean[positions];
        boolean[] essaiUsed = new boolean[positions];

        for (int i = 0; i < positions; i++) {
            if (essaiCode.charAt(i) == solutionCode.charAt(i)) {
                bienPlaces++;
                solutionUsed[i] = true;
                essaiUsed[i] = true;
            }
        }

        for (int i = 0; i < positions; i++) {
            if (!essaiUsed[i]) {
                for (int j = 0; j < positions; j++) {
                    if (!solutionUsed[j] && essaiCode.charAt(i) == solutionCode.charAt(j)) {
                        malPlaces++;
                        solutionUsed[j] = true;
                        break;
                    }
                }
            }
        }

        List<String> essaiCouleurs = new ArrayList<>();
        for (char code : essaiCode.toString().toCharArray()) {
            int key = Character.getNumericValue(code);
            essaiCouleurs.add(colorMap.get(key));
        }

        if (bienPlaces == positions) {
            // Afficher le pop-up de victoire
            JPanel messagePanel = new JPanel();
            messagePanel.add(new JLabel("C'est gagné !"));

            JButton accueilButton = new JButton("Retour à l'accueil");
            accueilButton.addActionListener(e -> retourAccueil());
            messagePanel.add(accueilButton);

            JOptionPane.showMessageDialog(this, messagePanel, "Victoire", JOptionPane.INFORMATION_MESSAGE);
        } else {
            resultatArea.append("Essai : " + String.join(", ", essaiCouleurs) + " -> Bien placés: " + bienPlaces + ", Mal placés: " + malPlaces + "\n");
        }

        currentSelection.clear();
        updateSelectionDisplay();
    }



    private int getKeyFromColor(String color) {
        for (Map.Entry<Integer, String> entry : colorMap.entrySet()) {
            if (entry.getValue().equals(color)) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("La couleur " + color + " n'existe pas dans colorMap.");
    }

    private void retourAccueil() {
        this.dispose(); // Ferme la fenêtre actuelle
        PageAccueil accueil = new PageAccueil(); // Créer une nouvelle instance de la page de création de partie
        accueil.setVisible(true); // Afficher la page d'accueil
    }


}
