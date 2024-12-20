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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import static ConnectFour.GameMain.TITLE;

public class MainMenu extends JFrame {
    private ImageIcon backgroundImageIcon;  // Use ImageIcon to handle GIF
    private Image multiplayerButtonImage;

    public MainMenu(JFrame parentFrame) {
        setSize(860, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background and button images
        backgroundImageIcon = loadImageback("src/menu.gif");
        multiplayerButtonImage = loadImage("src/start.png");

        // Create a main panel with a custom background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImageIcon != null) {
                    // Draw the animated background GIF, it will loop automatically
                    backgroundImageIcon.paintIcon(this, g, 0, 0);
                }
            }
        };
        mainPanel.setLayout(null); // Use absolute positioning for buttons

        // Create the "Multiplayer" button
        JButton multiplayerButton = createCustomButton(multiplayerButtonImage, 295, 500);
        multiplayerButton.addActionListener(e -> {
            JFrame gameFrame = new JFrame("Tic Tac Toe - Multiplayer");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GameMain multiplayerGame = new GameMain();
            gameFrame.setContentPane(multiplayerGame);
            gameFrame.pack();
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
            dispose(); // Close the main menu
        });

        // Add buttons to the main panel
        mainPanel.add(multiplayerButton);

        // Add the main panel to the JFrame
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Display the main menu
        setVisible(true);
        setLocationRelativeTo(null); // Center the window on the screen
    }
}
