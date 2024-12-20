/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #11
 * 1 - 5026231069 - Muhammad Zaky Al Khair
 * 2 - 5026231068 - Nailah Adlina
 * 3 - 5026231173 - Naura Salsabila
 */
package ConnectFour;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu extends JPanel {
    private ImageIcon backgroundImageIcon;  // Use ImageIcon to handle GIF
    private Image multiplayerButtonImage;
    private JFrame parentFrame;


    public MainMenu(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);
        setSize(860, 800);

        // Load the background and button images
        backgroundImageIcon = loadImageback("src/menu.gif");
        multiplayerButtonImage = loadImage("src/start.png");

        // Create the "Multiplayer" button
        JButton multiplayerButton = createCustomButton(multiplayerButtonImage, 295, 500);
        multiplayerButton.addActionListener(e -> {
            GameMain singlePlayerGame = new GameMain();
            parentFrame.setContentPane(singlePlayerGame);
            parentFrame.revalidate();
        });
        add(multiplayerButton);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Dynamically position buttons
                multiplayerButton.setBounds((panelWidth - 250) / 2, 500, 250, 80);

                repaint();
            }
        });
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImageIcon != null) {
            // Scale and draw the background image
            g.drawImage(backgroundImageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Helper method to load images with null handling
    private Image loadImage(String path) {
        try {
            File file = new File(path);
            Image img = ImageIO.read(file);
            if (img != null) {
                return img.getScaledInstance(250, 80, Image.SCALE_SMOOTH);
            } else {
                System.out.println("Image not found: " + path);
            }
        } catch (IOException e) {
            System.out.println("Failed to load image: " + path + ". Error: " + e.getMessage());
        }
        return null;
    }

    // Helper method to load and return an ImageIcon for the background (supports GIF)
    private ImageIcon loadImageback(String path) {
        try {
            File file = new File(path);
            ImageIcon imgIcon = new ImageIcon(file.getAbsolutePath());
            if (imgIcon != null) {
                return imgIcon;
            } else {
                System.out.println("Image not found: " + path);
            }
        } catch (Exception e) {
            System.out.println("Failed to load image: " + path + ". Error: " + e.getMessage());
        }
        return null;
    }
}
