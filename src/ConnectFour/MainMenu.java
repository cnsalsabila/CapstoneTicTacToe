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
    private Image loadImage(String path) {
        try {
            // Load image using file path relative to the src directory
            File file = new File(path);  // Just the relative path, no "src/" prefix needed
            Image img = ImageIO.read(file);
            if (img != null) {
                return img.getScaledInstance(250, 80, Image.SCALE_SMOOTH);
            } else {
                System.out.println("Image not found: " + path);
            }
        } catch (IOException e) {
            // Handle the case where the image can't be loaded
            System.out.println("Failed to load image: " + path + ". Error: " + e.getMessage());
        }
        return null;
    }

    // Helper method to load and return an ImageIcon for the background (supports GIF)
    private ImageIcon loadImageback(String path) {
        try {
            // Load image using ImageIcon, which supports animated GIFs
            File file = new File(path);
            ImageIcon imgIcon = new ImageIcon(file.getAbsolutePath());
            if (imgIcon != null) {
                return imgIcon; // Return ImageIcon for animated GIF
            } else {
                System.out.println("Image not found: " + path);
            }
        } catch (Exception e) {
            // Handle the case where the image can't be loaded
            System.out.println("Failed to load image: " + path + ". Error: " + e.getMessage());
        }
        return null;
    }
    // Helper method to create a custom button
    private JButton createCustomButton(Image buttonImage, int x, int y) {
        JButton button = new JButton();
        if (buttonImage != null) {
            button.setIcon(new ImageIcon(buttonImage));
        }
        button.setBounds(x, y, 250, 80); // Position and size
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 191, 255, 100)); // Light blue hover effect
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(null); // Reset background
            }
        });

        return button;
    }
    public static void main(String[] args) {
        // Run GUI construction codes in the Event-Dispatching thread for thread safety
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 100);
            frame.setLocationRelativeTo(null); // Center window on screen
            frame.setContentPane(new MainMenu(frame)); // Load MainMenu as the initial content
            frame.setVisible(true); // Show the window
        });
    }
}
