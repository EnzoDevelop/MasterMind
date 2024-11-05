package vues;

import javax.swing.*;
import java.awt.*;

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