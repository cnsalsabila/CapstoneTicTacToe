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
import java.awt.event.*;
import javax.swing.*;

/**
 * Tic-Tac-Toe: Two-player Graphic version with better OO design.
 * The Board and Cell classes are separated in their own classes.
 */
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
    private JButton backToMenuButton;  // Tombol Main Menu


    // Define game objects
    private Board board;         // the game board
    private State currentState;  // the current state of the game
    private Seed currentPlayer;  // the current player
    private JLabel statusBar;    // for displaying status message
    private AIPlayer aiPlayer;   // AI player object for single-player mode
    private boolean isSinglePlayer = true; // Flag for single-player mode
    private boolean isGameOver = false; // Flag untuk mencatat apakah game selesai

    /** Constructor to setup the UI and game components */
    public GameMain() {
        Timer timer = new Timer(100, e -> repaint()); // Refresh display every 100ms
        timer.start();

        // This JPanel fires MouseEvent
        // This JPanel fires MouseEvent
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int row = mouseY / Cell.SIZE;
                int col = mouseX / Cell.SIZE;

                if (isGameOver) {
                    // Jika game selesai, klik mouse akan mereset game
                    newGame();
                    isGameOver = false; // Reset flag setelah memulai game baru
                    return;
                }

                if (currentState == State.PLAYING) {
                    if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                            && board.cells[row][col].content == Seed.NO_SEED) {
                        currentState = board.stepGame(currentPlayer, row, col);
                        repaint();

                        // Periksa apakah pemain manusia menang/seri setelah langkah
                        if (currentState == State.CROSS_WON) {
                            stopBackgroundMusic();
                            SoundEffect.GAME_WON.play(); // Mainkan suara kemenangan
                            isGameOver = true;
                            return;
                        } else if (currentState == State.DRAW) {
                            stopBackgroundMusic();
                            SoundEffect.GAME_DRAW.play(); // Mainkan suara seri
                            isGameOver = true;
                            return;
                        }

                        // Ganti giliran ke AI jika dalam mode single-player
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                        if (isSinglePlayer && currentPlayer == Seed.NOUGHT) {
                            makeAIMove();

                            // Periksa apakah AI menang/seri setelah langkahnya
                            if (currentState == State.NOUGHT_WON) {
                                stopBackgroundMusic();
                                SoundEffect.GAME_LOST.play(); // Mainkan suara kekalahan
                                isGameOver = true;
                            } else if (currentState == State.DRAW) {
                                stopBackgroundMusic();
                                SoundEffect.GAME_DRAW.play(); // Mainkan suara seri
                                isGameOver = true;
                            }
                        }
                    }
                }
                repaint();
            }
        });
}
