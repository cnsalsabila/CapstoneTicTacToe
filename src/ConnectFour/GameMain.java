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
import javax.swing.*;
public class GameMain extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serializable warning

    // Define named constants for the drawing graphics
    public static final String TITLE = "Tic Tac Toe";
    public static final Color COLOR_BG_START = new Color(240, 248, 255); // Alice Blue
    public static final Color COLOR_BG_END = new Color(135, 206, 250);   // Light Sky Blue
    public static final Color COLOR_STATUS_BAR = new Color(255, 255, 255); // Lavender
    public static final Color COLOR_CROSS = new Color(255, 69, 0);  // Orange Red
    public static final Color COLOR_NOUGHT = new Color(0, 191, 255); // Deep Sky Blue
    public static final Font FONT_STATUS = new Font("Arial", Font.BOLD, 16);

    // Define game objects
    private Board board;         // the game board
    private State currentState;  // the current state of the game
    private Seed currentPlayer;  // the current player
    private JLabel statusBar;    // for displaying status message

    /** Constructor to setup the UI and game components */
