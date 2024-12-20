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
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The Cell class models each individual cell of the game board.
 */
public class Cell {
    // Define named constants for drawing
    public static final int SIZE = 200; // cell width/height (square)
    // Symbols (cross/nought) are displayed inside a cell, with padding from border
    public static final int PADDING = SIZE / 10;
    public static final int SEED_SIZE = SIZE - PADDING * 2;

    // Define properties (package-visible)
    /** Content of this cell (Seed.EMPTY, Seed.CROSS, or Seed.NOUGHT) */
    Seed content;
    /** Row and column of this cell */
    int row, col;
    private Image backgroundImage;  // To store the background image

    /** Constructor to initialize this cell with the specified row and col */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        content = Seed.NO_SEED;
        loadBackgroundImage();  // Load background image
    }
    private void loadBackgroundImage() {
        try {
            // Load a custom background image (modify path as needed)
            backgroundImage = ImageIO.read(new File("src/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Reset this cell's content to EMPTY, ready for new game */
    public void newGame() {
        content = Seed.NO_SEED;
    }
    /** Paint itself on the graphics canvas, given the Graphics context */
    public void paint(Graphics g) {
        // Draw the background image if it's not null
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, col * SIZE, row * SIZE, SIZE, SIZE, null);
        }

        // Draw the Seed (cross or nought) if the content is not empty
        int x1 = col * SIZE + PADDING;
        int y1 = row * SIZE + PADDING;
        if (content == Seed.CROSS || content == Seed.NOUGHT) {
            g.drawImage(content.getImage(), x1, y1, SEED_SIZE, SEED_SIZE, null);
        }
    }
}
