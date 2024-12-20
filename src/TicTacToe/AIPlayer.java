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

public abstract class AIPlayer {
    protected int ROWS = Board.ROWS;  // number of rows
    protected int COLS = Board.COLS;  // number of columns

    protected Cell[][] cells; // the board's ROWS-by-COLS array of Cells
    protected Seed mySeed;    // computer's seed
    protected Seed oppSeed;   // opponent's seed

    /** Constructor with reference to game board */
    public AIPlayer(Board board) {
        cells = board.cells;
    }

    /** Set/change the seed used by computer and opponent */
    public void setSeed(Seed seed) {
        this.mySeed = seed;
        oppSeed = (mySeed == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
    }

    /** Abstract method to get next move. Return int[2] of {row, col} */
    abstract int[] move();  // to be implemented by subclasses
}
