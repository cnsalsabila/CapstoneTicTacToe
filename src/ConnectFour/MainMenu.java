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
}
