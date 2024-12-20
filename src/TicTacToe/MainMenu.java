/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #11
 * 1 - 5026231069 - Muhammad Zaky Al Khair
 * 2 - 5026231068 - Nailah Adlina
 * 3 - 5026231173 - Naura Salsabila
 */

package TicTacToe;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu extends JPanel {
    private ImageIcon backgroundImageIcon;  // Use ImageIcon to handle GIF
    private Image singlePlayerButtonImage;
    private Image multiplayerButtonImage;
    private JFrame parentFrame;

    public MainMenu(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null); // Use absolute positioning for buttons

        // Load the background and button images
        backgroundImageIcon = loadImageback("src/mainmenu.gif");
        singlePlayerButtonImage = loadImage("src/aibutton.png");
        multiplayerButtonImage = loadImage("src/multibutton.png");

        // Create the "Single Player" button
        JButton singlePlayerButton = createCustomButton(singlePlayerButtonImage, 0, 0);
        singlePlayerButton.addActionListener(e -> {
            GameMain singlePlayerGame = new GameMain();
            singlePlayerGame.setSinglePlayerMode(true); // Set mode to single player
            parentFrame.setContentPane(singlePlayerGame);
            parentFrame.revalidate();
        });

        // Create the "Multiplayer" button
        JButton multiplayerButton = createCustomButton(multiplayerButtonImage, 0, 0);
        multiplayerButton.addActionListener(e -> {
            GameMain multiplayerGame = new GameMain();
            multiplayerGame.setSinglePlayerMode(false); // Set mode to multiplayer
            parentFrame.setContentPane(multiplayerGame);
            parentFrame.revalidate();
        });

        // Add buttons to the panel
        add(singlePlayerButton);
        add(multiplayerButton);

        // Adjust positions and sizes dynamically
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Dynamically position buttons
                singlePlayerButton.setBounds((panelWidth - 250) / 2, 290, 250, 80);
                multiplayerButton.setBounds((panelWidth - 250) / 2, 380, 250, 80);

                repaint();
            }
        });
    }
}
