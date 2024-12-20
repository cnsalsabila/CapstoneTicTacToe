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
    public GameMain() {
        Timer timer = new Timer(100, e -> repaint()); // Refresh display every 100ms
        timer.start();

        // This JPanel fires MouseEvent
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentState == State.PLAYING) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();
                    int colSelected = mouseX / Cell.SIZE;
                    if (colSelected >= 0 && colSelected < board.COLS) {
                        for (int row = board.ROWS - 1; row >= 0; row--) {
                            if (board.cells[row][colSelected].content == Seed.NO_SEED) {
                                currentState = board.stepGame(currentPlayer, row, colSelected);
                                currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                                break;
                            }
                        }
                    }
                } else {
                    newGame();
                }
                repaint(); // Refresh display
            }
        });

        // Setup the status bar (JLabel) to display status message
        statusBar = new JLabel();
        statusBar.setFont(FONT_STATUS);
        statusBar.setBackground(COLOR_STATUS_BAR);
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 40));
        statusBar.setHorizontalAlignment(JLabel.CENTER);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        super.setLayout(new BorderLayout());
        super.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 40));

        // Set up Game
        initGame();
        newGame();
    }

    /** Initialize the game (run once) */
    public void initGame() {
        board = new Board();  // allocate the game-board
    }

    public void newGame() {
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                board.cells[row][col].content = Seed.NO_SEED; // all cells empty
            }
        }
        currentPlayer = Seed.CROSS;    // cross plays first
        currentState = State.PLAYING;  // ready to play
        repaint();
    }

    /** Custom painting codes on this JPanel */
    @Override
    public void paintComponent(Graphics g) {  // Callback via repaint()
        super.paintComponent(g);
        drawGradientBackground(g); // Draw gradient background

        board.paint(g);  // ask the game board to paint itself

        // Print status-bar message
        if (currentState == State.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            statusBar.setText((currentPlayer == Seed.CROSS) ? "X's Turn" : "O's Turn");
        } else if (currentState == State.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again.");
        } else if (currentState == State.CROSS_WON) {
            statusBar.setForeground(COLOR_CROSS);
            statusBar.setText("'X' Won! Click to play again.");
        } else if (currentState == State.NOUGHT_WON) {
            statusBar.setForeground(COLOR_NOUGHT);
            statusBar.setText("'O' Won! Click to play again.");
        }
    }

    /** Draw a gradient background */
    private void drawGradientBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, COLOR_BG_START, getWidth(), getHeight(), COLOR_BG_END);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    /** The entry "main" method */
    public static void main(String[] args) {
        // Run GUI construction codes in Event-Dispatching thread for thread safety
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(TicTacToe.Board.CANVAS_WIDTH, TicTacToe.Board.CANVAS_HEIGHT + 100);
            frame.setLocationRelativeTo(null); // Pusatkan jendela
            frame.setContentPane(new MainMenu(frame)); // Set menu utama sebagai konten awal
            frame.setVisible(true); // Tampilkan jendela    // show it
        });
    }
}
